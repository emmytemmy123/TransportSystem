package logistic.management.services.user;


import logistic.management.model.dto.request.othersRequest.AuthRequest;
import logistic.management.model.dto.request.userRequest.UserRequest;
import logistic.management.model.dto.request.userRequest.changeUserPasswordRequest;
import logistic.management.model.dto.request.userRequest.loginUserRequest;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.dto.response.userResponse.UserResponse;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;


public interface UserService {

    ApiResponse<List<UserResponse>> getListOfUsers(int page, int size);

    ApiResponse<String> addUsers(UserRequest request);

    ApiResponse<UserResponse> getUsersById(UUID userId);

    ApiResponse<String> updateUser( UUID userId, UserRequest request);

    ApiResponse<String> deleteUser(UUID userId);

    ApiResponse<String> resetPassword(String email, changeUserPasswordRequest request);

    ApiResponse<String> forgotUserPassword(String email) throws MessagingException;

    String authenticate(AuthRequest authRequest);

    ApiResponse<String> loginUser(String email, loginUserRequest request);





}
