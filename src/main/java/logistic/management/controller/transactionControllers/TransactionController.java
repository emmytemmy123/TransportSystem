package logistic.management.controller.transactionControllers;


import io.swagger.annotations.ApiOperation;
import logistic.management.model.dto.request.transactionRequest.OrderItemRequest;
import logistic.management.model.dto.request.transactionRequest.OrdersRequest;
import logistic.management.model.dto.request.transactionRequest.PaymentRequest;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.dto.response.transactionResponse.OrdersResponse;
import logistic.management.model.dto.response.transactionResponse.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import logistic.management.services.transaction.OrderService;
import logistic.management.services.transaction.PaymentService;
import logistic.management.utills.EndPoints.TransactionEndPoints;
import logistic.management.utills.EndpointParam;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(TransactionEndPoints.transaction)
@RequiredArgsConstructor
public class TransactionController {

    private final PaymentService paymentService;
    private final OrderService ordersService;



                                //FIND_LISTS_OF_TRANSACTIONS

    @GetMapping(TransactionEndPoints.FIND_PAYMENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for retrieving lists of payment", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getListOfPayment(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                               @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return paymentService.getListOfPayment(page, size);
    }

    @GetMapping(TransactionEndPoints.FIND_ORDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of orders", response = OrdersResponse.class, responseContainer = "List")
    public ApiResponse<List<OrdersResponse>> getListOfOrders(@RequestParam(value = EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                               @RequestParam(value = EndpointParam.SIZE, defaultValue = EndpointParam.SIZE_DEFAULT) int size) {
        return ordersService.getListOfOrder(page, size);
    }



                                             //ADD_TRANSACTIONS

    @PostMapping(TransactionEndPoints.ADD_PAYMENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new payment to database", response = String.class)
    public ApiResponse<String> addPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.addPayment(request);
    }


    @PostMapping(TransactionEndPoints.ADD_ORDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for adding new order to database", response = String.class)
    public ApiResponse<String> addOrderItem(@Valid @RequestBody OrdersRequest request) {
        return ordersService.addOrder(request);
    }


                                            //FIND_TRANSACTIONS_BY_ID

    @GetMapping(TransactionEndPoints.FIND_ORDER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching Orders by id from database", response = OrdersResponse.class)
    public ApiResponse<OrdersResponse> getPaymentOrderById(@RequestParam UUID orderId) {
        return ordersService.getOrderById(orderId);
    }


                                            //UPDATE_TRANSACTION


    @PutMapping(TransactionEndPoints.UPDATE_ORDER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating ORDER by id from database", response = String.class)
    public ApiResponse<String> updateOrder(@PathVariable(value = "id")  UUID orderItemUuid,
                                             @RequestBody OrderItemRequest request) {
        return ordersService.updateOrder(orderItemUuid, request);
    }



                                //FIND_LISTS_OF_PAYMENT_BY_DATE

    @GetMapping(TransactionEndPoints.FIND_LISTS_OF_PAYMENT_BY_DATE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for retrieving lists of payment by date", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getListOfPayment(@RequestParam String dateCreated) {
        return paymentService.findPaymentByDate(dateCreated);
    }

                                    //FIND_LISTS_OF_ORDER_BY_DATE

    @GetMapping(TransactionEndPoints.FIND_LISTS_OF_ORDER_BY_DATE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of order by date", response = OrdersResponse.class, responseContainer = "List")
    public ApiResponse<List<OrdersResponse>> getListOfOrderByDate(@RequestParam String dateCreated) {
        return ordersService.findOrderByDate(dateCreated);
    }


                                    //FIND_PAYMENT_BY_SALES_PERSON

    @GetMapping(TransactionEndPoints.FIND_PAYMENT_BY_SALES_PERSON)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching payment by salesPerson from database", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getPaymentBySalesPerson(@RequestParam UUID userId) {
        return paymentService.findPaymentBySalesPerson(userId);
    }

                                    //FIND_PAYMENT_BY_CUSTOMER

    @GetMapping(TransactionEndPoints.FIND_PAYMENT_BY_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching payment by customer from database", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getPaymentByCustomer(@RequestParam UUID customerId) {
        return paymentService.findPaymentByCustomer(customerId);
    }

                                        //FIND_ORDER_BY_CUSTOMER

    @GetMapping(TransactionEndPoints.FIND_ORDER_BY_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching order by customer from database", response = OrdersResponse.class, responseContainer = "List")
    public ApiResponse<List<OrdersResponse>> getOrdersByCustomer(@RequestParam UUID customerUuid) {
        return ordersService.getOrdersByCustomer(customerUuid);
    }


                                    //FIND_LIST_OF_PAYMENT_BY_DATE_RANGE

    @GetMapping(TransactionEndPoints.FIND_LIST_OF_PAYMENT_BY_DATE_RANGE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for retrieving lists of payment by dateRange", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getListOfPaymentByDateRange(@RequestParam String from, @RequestParam String to) {
        return paymentService.findListOfPaymentByDateRange(from, to);
    }


                                //FIND_LIST_OF_PAYMENT_BY_ORDER_ID

    @GetMapping(TransactionEndPoints.FIND_PAYMENT_BY_ORDER_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching payment by orderId from database", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getPaymentByOrderId(@RequestParam UUID orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }




}