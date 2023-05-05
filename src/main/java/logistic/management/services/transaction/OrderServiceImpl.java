package logistic.management.services.transaction;

import logistic.management.model.dto.enums.AppStatus;
import logistic.management.model.dto.request.transactionRequest.OrderItemRequest;
import logistic.management.model.dto.request.transactionRequest.OrdersRequest;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.entity.user.Customer;
import logistic.management.repo.products.ProductRepository;
import logistic.management.repo.user.CustomerRepository;
import logistic.management.utills.MessageUtil;
import logistic.management.exception.BadRequestException;
import logistic.management.exception.RecordNotFoundException;
import logistic.management.mapper.Mapper;
import logistic.management.model.dto.enums.MessageHelpers;
import logistic.management.model.dto.response.transactionResponse.OrdersResponse;
import logistic.management.model.entity.products.Product;
import logistic.management.model.entity.transaction.OrderItems;
import logistic.management.model.entity.transaction.Orders;
import logistic.management.model.entity.user.AppUser;
import logistic.management.repo.transaction.OrderItemsRepository;
import logistic.management.repo.transaction.OrdersRepository;
import logistic.management.repo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrdersRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    @Override
    /**
     * @Validate and Find the list of  order
     * @Validate if the List of order is empty otherwise return record not found*
     * @return the list of order and a Success Message
     * * */
    public ApiResponse<List<OrdersResponse>> getListOfOrder(int page, int size) {

        List<Orders> ordersList = orderRepository.findAll(PageRequest.of(page,size)).toList();
        if(ordersList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(ordersList, OrdersResponse.class));

    }

    @Override
    /**
     * @Validating and Finding the list of OrderOptional by uuid
     * @Validate if the List of OrderOptional is empty otherwise return record not found
     * Create the Order definition and get the OrderOptional by uuid
     * @return the list of OrderOptional and a Success Message
     * * */
    public ApiResponse<OrdersResponse> getOrderById(UUID orderId) {

        Optional<Orders> ordersOptional = orderRepository.findByUuid(orderId);

        if(ordersOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Orders orders = ordersOptional.get();

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(orders,OrdersResponse.class));

    }


    private void generateOrderNumber(Orders  orders){
        Integer serial;
        String orderNumber;

        LocalDate localDate = LocalDate.now();
        orderNumber ="Tr"+localDate.getYear()+""+localDate.getMonthValue()+""+localDate.getDayOfMonth();

        List<Orders> listOrderForToday = orderRepository.findOrderForCurrentDate(localDate.getMonthValue(), localDate.getYear());
        if(listOrderForToday.isEmpty()){
            orders.setOrderNo(orderNumber+"-1");
            orders.setSerialNo(1);
        }else{
            Orders order = listOrderForToday.get(0);
            serial = order.getSerialNo()+1;
            orders.setSerialNo(serial);
            orders.setOrderNo(orderNumber+"-"+serial);
        }
    }



    @Override
    public ApiResponse<String> addOrder(OrdersRequest request) {

        Customer existingCustomer  = customerRepository.findByUuid(request.getCustomerId())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        AppUser existingUser  = userRepository.findByUuid(request.getCreatedById())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Orders orders = new Orders();

        orders.setOrderBy(existingCustomer.getName());
        orders.setOrderStatus("pending");
        orders.setStartTime(LocalDateTime.now());
        orders.setCustomer(existingCustomer);


        Double totalAmount = 0.0;
        Integer totalWeight = 0;
        Integer totalQuantity =0;

        List<OrderItems> orderItemsList = new ArrayList<>();
        generateOrderNumber(orders);

        for (OrderItemRequest orderItem : request.getItems()) {

            Product existingProduct  = productRepository.findByUuid(orderItem.getProductId())
                    .orElse(null);

            if(existingProduct != null){
                OrderItems orderItems = new OrderItems();

                orderItems.setItemName(existingProduct.getName());
                orderItems.setSalesPrice(existingProduct.getSalesPrice());
                orderItems.setQuantity(orderItem.getQuantity());
                orderItems.setAmount((existingProduct.getSalesPrice() * (orderItem.getQuantity())));
                orderItems.setTransactionDate(LocalDateTime.now());
                orderItems.setStatus("pending");
                orderItems.setCreatedBy(existingUser);
                orderItems.setProduct(existingProduct);
                orderItems.setOrders(orders);
                existingProduct.setQuantity(existingProduct.getQuantity() - orderItems.getQuantity());

                totalAmount += (orderItem.getQuantity() * existingProduct.getSalesPrice());
                totalWeight += (existingProduct.getWeight() * orderItem.getQuantity());
                totalQuantity += orderItem.getQuantity();
                orderItemsList.add(orderItems);

            }

            orders.setDeliveryType(request.getDeliveryType());
            orders.setDeliveryCost(totalWeight * existingProduct.getDeliveryCost());

//            String delivery = "door delivery";
//            double deliveryCostPerKg = 500;
//            if(delivery == request.getDeliveryType())
//            orders.setDeliveryCost(totalWeight * existingProduct.getDeliveryCost());
//            else
//            orders.setDeliveryCost((double) 0);

            orders.setTotalQuantity(totalQuantity);
            orders.setTotalAmount(totalAmount + orders.getDeliveryCost());
            orders.setTotalAmountDue(totalAmount + orders.getDeliveryCost());

            orders.setOrderItemsList(orderItemsList);

            Integer msg = existingProduct.getQuantity();
            if(msg <= -1)
            throw new BadRequestException(MessageUtil.OUT_OF_STOCK);

            orderRepository.save(orders);

        }
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                MessageHelpers.CREATE_SUCCESSFUL.message);
    }


    /**
     * @validating orderItem by uuid
     * @Validate if the List of orderItem is empty otherwise return record not found
     * @return orderItem
     * * */
    private OrderItems validateOrderItems(UUID uuid){
        Optional<OrderItems> orderItemsOptional = orderItemsRepository.findByUuid(uuid);
        if(orderItemsOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return orderItemsOptional.get();
    }


    @Override
    public ApiResponse<String> updateOrder(UUID orderItemUuid, OrderItemRequest request) {

        OrderItems orderItems = validateOrderItems(orderItemUuid);

        Product product = orderItems.getProduct();
        product.setQuantity( ((Integer.sum(product.getQuantity(), orderItems.getQuantity()))) - (request.getQuantity()));

            orderItems.setItemName(orderItems.getItemName());
            orderItems.setSalesPrice(orderItems.getSalesPrice());
            orderItems.setQuantity(request.getQuantity());
            orderItems.setAmount((orderItems.getSalesPrice() * (request.getQuantity())));
            orderItems.setTransactionDate(LocalDateTime.now());
            orderItems.setStatus("pending");
            orderItems.setProduct(orderItems.getProduct());
            orderItems.setCreatedBy(orderItems.getCreatedBy());

        Integer existingProduct = product.getQuantity();
        if(existingProduct <= -1)
            throw new BadRequestException(MessageUtil.OUT_OF_STOCK);

            orderItemsRepository.save(orderItems);

        Orders orders = orderItems.getOrders();

        orders.setTotalAmount((orders.getTotalAmount() - orderItems.getAmount()));
        orders.setTotalAmountDue(orders.getTotalAmount());

        orderRepository.save(orders);

        return new ApiResponse<String>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                MessageHelpers.UPDATE_SUCCESSFUL.message);

        }



    @Override
    public ApiResponse<List<OrdersResponse>> getOrdersByCustomer(UUID customerUuid) {

        List<Orders> ordersList = orderRepository.findOrdersByCustomer(customerUuid);

        if(ordersList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(ordersList, OrdersResponse.class));
    }


    @Override
    public ApiResponse<List<OrdersResponse>> findOrderByDate(String dateCreated) {

        List<Orders> ordersList = orderRepository.searchOrderByDateCreated(dateCreated);

        if(ordersList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(ordersList, OrdersResponse.class));

    }




}
