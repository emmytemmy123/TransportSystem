package fcmb.com.good.services.transaction;

import fcmb.com.good.exception.BadRequestException;
import fcmb.com.good.exception.GlobalErrorHandler;
import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.enums.MessageHelpers;
import fcmb.com.good.model.dto.enums.Status;
import fcmb.com.good.model.dto.request.transactionRequest.OrderItemRequest;
import fcmb.com.good.model.dto.request.transactionRequest.OrdersRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.OrdersResponse;
import fcmb.com.good.model.entity.products.Product;
import fcmb.com.good.model.entity.transaction.OrderItems;
import fcmb.com.good.model.entity.transaction.Orders;
import fcmb.com.good.model.entity.user.AppUser;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.products.ProductRepository;
import fcmb.com.good.repo.transaction.OrderItemsRepository;
import fcmb.com.good.repo.transaction.OrdersRepository;
import fcmb.com.good.repo.user.CustomerRepository;
import fcmb.com.good.repo.user.UserRepository;
import fcmb.com.good.utills.MessageUtil;
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
                orderItems.setPurchasePrice(existingProduct.getPurchasePrice());
                orderItems.setPurchaseAmount((existingProduct.getPurchasePrice())*(orderItem.getQuantity()));
                orderItems.setProfit((orderItems.getAmount())-(orderItems.getPurchaseAmount()));
                orderItems.setServiceName(orderItem.getServiceName());
                orderItems.setDescription(orderItem.getDescription());
                orderItems.setTransactionDate(LocalDateTime.now());
                orderItems.setStatus("pending");
                orderItems.setCreatedBy(existingUser);
                orderItems.setProduct(existingProduct);
                orderItems.setOrders(orders);
                existingProduct.setQuantity(existingProduct.getQuantity() - orderItems.getQuantity());

                totalAmount += orderItem.getQuantity() * existingProduct.getSalesPrice();
                orderItemsList.add(orderItems);

            }

            orders.setAmount(totalAmount);
            orders.setAmountDue(totalAmount);
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
            orderItems.setPurchasePrice(orderItems.getPurchasePrice());
            orderItems.setPurchaseAmount((orderItems.getPurchasePrice())*(orderItems.getQuantity()));
            orderItems.setProfit((orderItems.getAmount())-(orderItems.getPurchaseAmount()));
            orderItems.setServiceName(request.getServiceName());
            orderItems.setDescription(request.getDescription());
            orderItems.setTransactionDate(LocalDateTime.now());
            orderItems.setStatus("pending");
            orderItems.setProduct(orderItems.getProduct());
            orderItems.setCreatedBy(orderItems.getCreatedBy());

        Integer existingProduct = product.getQuantity();
        if(existingProduct <= -1)
            throw new BadRequestException(MessageUtil.OUT_OF_STOCK);

            orderItemsRepository.save(orderItems);

        Orders orders = orderItems.getOrders();

        orders.setAmount((orders.getAmount() - orderItems.getAmount()));
        orders.setAmountDue(orders.getAmount());

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
