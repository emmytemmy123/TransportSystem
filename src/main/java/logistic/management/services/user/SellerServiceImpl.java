package logistic.management.services.user;

import logistic.management.model.dto.enums.AppStatus;
import logistic.management.model.dto.request.userRequest.SellerRequest;
import logistic.management.model.dto.request.userRequest.changeSellerPasswordRequest;
import logistic.management.model.dto.request.userRequest.loginSellerRequest;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.dto.response.userResponse.SellerResponse;
import logistic.management.model.entity.user.Sellers;
import logistic.management.repo.user.SellerRepository;
import logistic.management.utills.MessageUtil;
import logistic.management.exception.RecordNotFoundException;
import logistic.management.mapper.Mapper;
import logistic.management.model.entity.user.AppUser;
import logistic.management.repo.user.UserRepository;
import logistic.management.utills.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private  final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final EmailUtils emailUtils;


    @Override
    /**
     * @Finding the list of Seller
     * @Validate if the List of Seller is empty otherwise return record not found
     * @return the list of Seller and a Success Message
     * * */
    public ApiResponse<List<SellerResponse>> getListOfSeller(int page, int size) {

            List<Sellers> sellersList = sellerRepository.findAll(PageRequest.of(page,size)).toList();
            if(sellersList.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertList(sellersList, SellerResponse.class));
        }



    @Override
    /**
     * @Validate that no duplicate Seller is allowed
     * @Validate that Seller exists otherwise return record not found*
     * Create Seller definition and save
     * @return success message
     * * */
    public ApiResponse<String> addSeller(SellerRequest request) {
            Optional<Sellers> sellersOptional  = validateSellerByEmailId(request.getEmail());

            if(!sellersOptional.isEmpty()){
                return new ApiResponse(AppStatus.FAILED.label, HttpStatus.EXPECTATION_FAILED.value(),
                        "Email Already Exist");
            }
            sellerRepository.save(getSellerFromRequest(request));
            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record added Successfully");
        }


    @Override
    /**
     * @Finding the list of SellerOptional by uuid
     * @Validate if the List of SellerOptional is empty otherwise return record not found
     * Create the Seller definition and get the employee
     * @return the list of Seller and a Success Message* *
     * * */
    public ApiResponse<SellerResponse> getSellerById(@RequestParam("id") UUID sellerId) {
        Optional<Sellers> sellersOptional = sellerRepository.findByUuid(sellerId);

        if(sellersOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Sellers sellers = sellersOptional.get();

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(sellers, SellerResponse.class));

    }

    /**
     * @Validating existingSellerOptional by Email
     * @Validate if existingSellerOptional is empty otherwise return Duplicate Record
     * return existingSellerOptional
     * * */
    private Optional<Sellers> validateSellerByEmailId(String email) {
        Optional<Sellers> existingSellerOptional = sellerRepository.findByEmailId(email);
        return existingSellerOptional;
    }

    /**
     * @validating SellerOptional by uuid*
     * @Validate if the List of Seller is empty otherwise return record not found
     * @return SellerOptional
     * * */
    private Sellers validateSeller(UUID uuid) {
        Optional<Sellers> sellersOptional = sellerRepository.findByUuid(uuid);
        if (sellersOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return sellersOptional.get();
    }


    /**
     * Set and get the Seller parameters
     */
    private Sellers getSellerFromRequest(SellerRequest request){

        AppUser existingUser  = userRepository.findByUuid(request.getCreatedById())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Sellers sellers = new Sellers();

        sellers.setName(request.getName());
        sellers.setEmail(request.getEmail());
        sellers.setAddress(request.getAddress());
        sellers.setCountry(request.getCountry());
        sellers.setCity(request.getCity());
        sellers.setGender(request.getGender());
        sellers.setPassword(request.getPassword());
        sellers.setPhone(request.getPhone());
        sellers.setUsername(request.getUsername());
        sellers.setPhoto(request.getPhoto());
        sellers.setCreatedBy(existingUser);

        return sellers;
    }


    @Override
    /**
     * @validating SellerOptional by uuid
     * @Validate if the List of Seller is empty otherwise return record not found
     * Create the Seller definition and update
     * @return a Success Message
     * * */
    public ApiResponse<String> updateSeller(UUID sellerId, SellerRequest request) {
            Sellers sellers = validateSeller(sellerId);
            sellers.setName(request.getName());
            sellers.setEmail(request.getEmail());
            sellers.setGender(request.getGender());
            sellers.setCountry(request.getCountry());
            sellers.setCity(request.getCity());
            sellers.setAddress(request.getAddress());
            sellers.setPhone(request.getPhone());
            sellers.setUsername(request.getUsername());

            sellerRepository.save(sellers);
            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Updated Successfully");
        }


    @Override
    /**
     * @validating Seller by uuid
     * @Validate if Seller is empty otherwise return record not found
     * @Delete Seller
     * @return a Success Message
     * * */
    public ApiResponse<String> deleteSeller(UUID sellerId) {
            Sellers sellers = validateSeller(sellerId);
            sellerRepository.delete(sellers);
            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Deleted successfully");
        }


    @Override
    /**
     * @Validating existingEmployee by Email
     * @Validate employee password and change password
     * @Save the new password
     * @Return a Success Message if oldPassword is correct
     * @Return a Failed Message if oldPassword is Incorrect
     */
    public ApiResponse<String> resetPassword(String email, changeSellerPasswordRequest request) {
        Sellers sellers = sellerRepository.findByEmail(email);

        if(sellers.getPassword().equals(request.getOldPassword())){
            sellers.setPassword(request.getNewPassword());
            sellerRepository.save(sellers);

            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Password Changed successfully");
        }
        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.BAD_REQUEST.value(),
                "Incorrect Old Password");
    }


    @Override
    /**
     * @Finding Seller by Email
     * @Getting the value of email and password of Seller and sending it to Seller`s Email
     * @Return a Success Message
     */
    public ApiResponse<String> forgotSellerPassword(String email) throws MessagingException {
        Sellers sellers = sellerRepository.findByEmail(email);

        emailUtils.forgotMail(sellers.getEmail(), "Credentials by Transaction Management System", sellers.getPassword() );

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Check your email for credentials");
    }


    @Override
    /**
     * @Finding existingSeller by Email
     * @Validate Seller email and password
     * @Return a Success Message if email and password is correct
     * @Return a Failed Message if email and password is Incorrect
     */
    public ApiResponse<String> loginSeller(String email, loginSellerRequest request) {
        Sellers sellers = sellerRepository.findByEmail(email);

        if (sellers.getEmail().equals(request.getEmail())
                && sellers.getPassword().equals(request.getPassword())) {

            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Employee login successfully");
        }

        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.BAD_REQUEST.value(),
                "Incorrect Email or Password");
    }


}
