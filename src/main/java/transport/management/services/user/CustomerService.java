package transport.management.services.user;

import transport.management.model.dto.request.userRequest.CustomerRequest;
import transport.management.model.dto.request.userRequest.changeCustomerPasswordRequest;
import transport.management.model.dto.request.userRequest.loginCustomerRequest;
import transport.management.model.dto.response.othersResponse.ApiResponse;
import transport.management.model.dto.response.userResponse.CustomerResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface CustomerService {

    ApiResponse<List<CustomerResponse>> getListOfCustomer(int page, int size);

    ApiResponse<String> addCustomer(CustomerRequest request) throws IOException;

    ApiResponse<CustomerResponse> getCustomerById(UUID customerId);

    ApiResponse<String> updateCustomer( UUID customerId, CustomerRequest cst);

    ApiResponse<String> deleteCustomer(UUID customerId);

    ApiResponse<String> resetPassword(String email, changeCustomerPasswordRequest request);

    ApiResponse<String> forgotCustomerPassword(String email) throws MessagingException;

    ApiResponse<String> loginCustomer(String email, loginCustomerRequest request);



}
