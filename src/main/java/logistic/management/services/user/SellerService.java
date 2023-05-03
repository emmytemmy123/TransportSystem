package logistic.management.services.user;

import logistic.management.model.dto.request.userRequest.SellerRequest;
import logistic.management.model.dto.request.userRequest.changeSellerPasswordRequest;
import logistic.management.model.dto.request.userRequest.loginSellerRequest;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.dto.response.userResponse.SellerResponse;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

public interface SellerService {


   ApiResponse<List<SellerResponse>> getListOfSeller(int page, int size);

    ApiResponse<String> addSeller(SellerRequest request);

    ApiResponse<SellerResponse> getSellerById(UUID employeeId);

    ApiResponse<String> updateSeller(UUID employeeId, SellerRequest employee);

    ApiResponse<String> deleteSeller(UUID employeeId);

    ApiResponse<String> resetPassword(String email, changeSellerPasswordRequest request);

    ApiResponse<String> forgotSellerPassword(String email) throws MessagingException;

    ApiResponse<String> loginSeller(String email, loginSellerRequest request);




}
