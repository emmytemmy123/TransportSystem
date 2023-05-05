package logistic.management.controller.usersControllers;

import io.swagger.annotations.ApiOperation;
import logistic.management.model.dto.request.userRequest.*;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.dto.response.userResponse.CustomerResponse;
import logistic.management.model.dto.response.userResponse.SellerResponse;
import logistic.management.model.dto.response.userResponse.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import logistic.management.services.user.CustomerService;
import logistic.management.services.user.SellerService;
import logistic.management.services.user.UserService;
import logistic.management.utills.EndpointParam;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static logistic.management.utills.EndPoints.UsersEndPoints.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(users)
@RestController
@RequiredArgsConstructor
public class UsersController  {

    private final CustomerService customerService;
    private final SellerService sellerService;
    private final UserService userService;
 


                                        //FIND_LISTS_OF_USERS
    @GetMapping(FIND_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of customer", response = CustomerResponse.class, responseContainer = "List")
    public ApiResponse<List<CustomerResponse>> getListOfCustomer(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                                 @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return customerService.getListOfCustomer(page,size);
    }

    @GetMapping(FIND_SELLER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of seller", response = SellerResponse.class, responseContainer = "List")
    public ApiResponse<List<SellerResponse>> getListOfSeller(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                               @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return sellerService.getListOfSeller(page,size);
    }


    @GetMapping(FIND_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    @ApiOperation(value = "Endpoint for retrieving lists of user", response = UserResponse.class, responseContainer = "List")
    public ApiResponse<List<UserResponse>> getListOfUsers(@RequestParam(value= EndpointParam.PAGE, defaultValue = EndpointParam.PAGE_DEFAULT) int page,
                                                          @RequestParam(value= EndpointParam.SIZE,defaultValue= EndpointParam.SIZE_DEFAULT) int size) {
        return userService.getListOfUsers(page,size);
    }


                                    //ADD_USERS
    @PostMapping(ADD_CUSTOMER)
    @ApiOperation(value = "Endpoint for adding new customer to database", response = String.class)
    public ApiResponse<String> addCustomer(@Valid @RequestBody CustomerRequest request) throws IOException {
        return customerService.addCustomer(request);
    }

    @PostMapping(ADD_SELLER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for adding new seller to database", response = String.class)
    public ApiResponse<String> addSeller(@Valid @RequestBody SellerRequest request) {
        return sellerService.addSeller(request);
    }


    @PostMapping(ADD_USER)
    @ApiOperation(value = "Endpoint for adding new user to database", response = String.class)
    public ApiResponse<String> addUsers(@Valid @RequestBody UserRequest request) {
        return userService.addUsers(request);
    }


                                         //FIND_USERS_BY_ID
    @GetMapping(FIND_CUSTOMER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching customer by id from database", response = CustomerResponse.class)
    public ApiResponse<CustomerResponse> getCustomerById(@PathVariable(value = "id") UUID customer_id) {
        return customerService.getCustomerById(customer_id);
    }

    @GetMapping(FIND_SELLER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching seller by id from database", response = SellerResponse.class)
    public ApiResponse<SellerResponse> getSellerById(@PathVariable(value = "id") UUID sellerId) {
        return sellerService.getSellerById(sellerId);
    }

    @GetMapping(FIND_USER_BY_ID)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for fetching user by id from database", response = UserResponse.class)
    public ApiResponse<UserResponse> getUsersById(@PathVariable(value = "id") UUID user_id) {
        return userService.getUsersById(user_id);
    }


                                        //UPDATE_USERS
    @PutMapping(UPDATE_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating customer by id from database", response = String.class)
    public ApiResponse<String> updateCustomer(@PathVariable(value = "id") UUID customer_id,
                                              @RequestBody CustomerRequest cus) {
        return customerService.updateCustomer(customer_id, cus);
    }


    @PutMapping(UPDATE_SELLER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') ")
    @ApiOperation(value = "Endpoint for updating Seller by id from database", response = String.class)
    public ApiResponse<String> updateSeller(@PathVariable(value = "id") UUID sellerId,
                                              @RequestBody SellerRequest request) {
        return sellerService.updateSeller(sellerId, request);
    }


    @PutMapping(UPDATE_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating user by id from database", response = String.class)
    public ApiResponse<String> updateUsers(@PathVariable(value = "id") UUID user_id,
                                           @RequestBody UserRequest request) {
        return userService.updateUser(user_id, request);
    }



                                            //DELETE_USERS

    @DeleteMapping(DELETE_SELLER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for deleting seller by id from database", response = String.class)
    public ApiResponse<String> deleteSeller(@PathVariable(value = "id") UUID sellerId) {
        return sellerService.deleteSeller(sellerId);
    }


    @DeleteMapping(DELETE_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    @ApiOperation(value = "Endpoint for deleting user by id from database", response = String.class)
    public ApiResponse<String> deleteUser(@PathVariable(value = "id") UUID user_id) {
        return userService.deleteUser(user_id);
    }



                                //Change Password

    @PutMapping(RESET_USER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting users password from database", response = String.class)
    public ApiResponse<String> resetPassword(@RequestBody changeUserPasswordRequest request, String email) {
        return userService.resetPassword(email, request);
    }

    @PutMapping(RESET_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for updating customer password from database", response = String.class)
    public ApiResponse<String> changeCustomerPassword(@RequestBody changeCustomerPasswordRequest request, String email) {
        return customerService.resetPassword(email, request);
    }

    @PutMapping(RESET_SELLER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting Seller password from database", response = String.class)
    public ApiResponse<String> resetPassword(@RequestBody changeSellerPasswordRequest request, String email) {
        return sellerService.resetPassword(email, request);
    }



                                        //Forgot Password
    @GetMapping(FORGOT_USER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for getting forgotten users password from database", response = String.class)
    public ApiResponse<String> forgotUserPassword(String email) throws MessagingException {
        return userService.forgotUserPassword(email);
    }

    @GetMapping(FORGOT_CUSTOMER_PASSWORD)
    @PreAuthorize(" hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for resetting customer password from database", response = String.class)
    public ApiResponse<String> forgotCustomerPassword(String email) throws MessagingException {
        return customerService.forgotCustomerPassword(email);
    }

    @GetMapping(FORGOT_SELLER_PASSWORD)
    @PreAuthorize("hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint for getting forgotten Seller password from database", response = String.class)
    public ApiResponse<String> forgotSellerPassword(String email) throws MessagingException {
        return sellerService.forgotSellerPassword(email);
    }


                                        //login Users
    @PostMapping(LOGIN_USER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login users to database", response = String.class)
    public ApiResponse<String> loginUser(@RequestBody loginUserRequest request, String email) {
        return userService.loginUser(email, request);
    }

    @PostMapping(LOGIN_CUSTOMER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login customer to database", response = String.class)
    public ApiResponse<String> loginCustomer(@RequestBody loginCustomerRequest request, String email) {
        return customerService.loginCustomer(email, request);
    }

    @PostMapping(LOGIN_SELLER)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') ")
    @ApiOperation(value = "Endpoint to login Seller to database", response = String.class)
    public ApiResponse<String> loginSeller(@RequestBody loginSellerRequest request, String email) {
        return sellerService.loginSeller(email, request);
    }



}
