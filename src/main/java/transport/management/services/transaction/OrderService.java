package transport.management.services.transaction;


import transport.management.model.dto.request.transactionRequest.OrderItemRequest;
import transport.management.model.dto.request.transactionRequest.OrdersRequest;
import transport.management.model.dto.response.othersResponse.ApiResponse;
import transport.management.model.dto.response.transactionResponse.OrdersResponse;

import java.util.List;
import java.util.UUID;


public interface OrderService {

    ApiResponse<List<OrdersResponse>> getListOfOrder(int page, int size);

    ApiResponse<OrdersResponse> getOrderById(UUID orderId);

    ApiResponse<String> addOrder(OrdersRequest request);

    ApiResponse<List<OrdersResponse>> getOrdersByCustomer(UUID customerUuid);

    ApiResponse<List<OrdersResponse>> findOrderByDate(String dateCreated);

    ApiResponse<String> updateOrder (UUID orderItemUuid, OrderItemRequest request);


}
