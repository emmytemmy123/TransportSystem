package logistic.management.services.transaction;

import logistic.management.model.dto.enums.AppStatus;
import logistic.management.model.dto.response.othersResponse.ApiResponse;
import logistic.management.model.dto.response.transactionResponse.PaymentResponse;
import logistic.management.repo.products.ProductRepository;
import logistic.management.repo.user.CustomerRepository;
import logistic.management.utills.MessageUtil;
import logistic.management.exception.RecordNotFoundException;
import logistic.management.mapper.Mapper;
import logistic.management.model.dto.enums.Status;
import logistic.management.model.dto.request.transactionRequest.PaymentRequest;
import logistic.management.model.entity.transaction.Orders;
import logistic.management.model.entity.transaction.Payment;
import logistic.management.repo.transaction.OrdersRepository;
import logistic.management.repo.transaction.PaymentRepository;
import logistic.management.repo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private  final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;



    @Override
    /**
     * @Validate and Find the list of  Payment
     * @Validate if the List of Payment is empty otherwise return record not found*
     * @return the list of Payment and a Success Message
     * * */
    public ApiResponse<List<PaymentResponse>> getListOfPayment(int page, int size) {
        List<Payment> paymentList = paymentRepository.findAll(PageRequest.of(page,size)).toList();
        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList, PaymentResponse.class));
    }


    private void generatePaymentNumber(Payment  payment){
        Integer serial;
        String paymentNumber;

        LocalDate localDate = LocalDate.now();
        paymentNumber ="Paid"+localDate.getYear()+""+localDate.getMonthValue()+""+localDate.getDayOfMonth();

        List<Payment> listPaymentForToday = paymentRepository.findPaymentForCurrentDate(localDate.getMonthValue(), localDate.getYear());
        if(listPaymentForToday.isEmpty()){
            payment.setTranReference(paymentNumber+"-1");
            payment.setSerialNo(1);
        }else{
            Payment payments = listPaymentForToday.get(0);
            serial = payments.getSerialNo()+1;
            payment.setSerialNo(serial);
            payment.setTranReference(paymentNumber+"-"+serial);
        }
    }



    @Override
    /**
     * @Validate that no duplicate Payment is allowed
     * @Validate that PaymentCategory exists otherwise return record not found
     * Create the Payment definition and save
     * @return success message
     * * */
    public ApiResponse<String> addPayment(PaymentRequest request) {

        Orders existingOrders  = ordersRepository.findByUuid(request.getOrdersId())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Payment payment = new Payment();

        payment.setAmount(request.getAmount());
        payment.setPaymentMode(request.getPaymentMode());
        generatePaymentNumber(payment);

        int valueToBalance = (int) (existingOrders.getTotalAmountDue() - (request.getAmount()));
//        payment.setPaymentStatus(valueToBalance==0? "paid": "balance your payment");
        payment.setPaymentStatus(valueToBalance==0? Status.PAID.label: Status.TO_BALANCE.label);

        payment.setBalance(existingOrders.getTotalAmountDue() - request.getAmount());
        payment.setPaidBy(existingOrders.getOrderBy());
//        payment.setTranReference(request.getTranReference());
        payment.setOrder(existingOrders);

        paymentRepository.save(payment);

        Orders orders = payment.getOrder();
        orders.setTotalAmountDue(orders.getTotalAmountDue() - (request.getAmount()));

        ordersRepository.save(orders);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record created successfully");

    }



    /**
     * @validating PaymentOptional by uuid
     * @Validate if the List of Payment is empty otherwise return record not found
     * @return PaymentOptional
     * * */
    private Payment validatePayment(UUID uuid){
        Optional<Payment> paymentOptional = paymentRepository.findByUuid(uuid);
        if(paymentOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return paymentOptional.get();
    }


    @Override
    public ApiResponse<List<PaymentResponse>> findPaymentByDate(String dateCreated) {

        List<Payment> paymentList = paymentRepository.findByDateCreated(dateCreated);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList, PaymentResponse.class));

    }

    @Override
    public ApiResponse<List<PaymentResponse>> findPaymentBySalesPerson(UUID userUuid) {

        List<Payment> paymentList = paymentRepository.findPaymentBySalesPerson(userUuid);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList,PaymentResponse.class));

    }

    @Override
    public ApiResponse<List<PaymentResponse>> findPaymentByCustomer(UUID uuid) {

        List<Payment> paymentList = paymentRepository.findPaymentByCustomer(uuid);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);


        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList,PaymentResponse.class));

    }


    @Override
    public ApiResponse<List<PaymentResponse>> getPaymentByOrderId(UUID orderId) {

        List<Payment> paymentList = paymentRepository.findByOrderId(orderId);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList,PaymentResponse.class));

    }


    @Override
    public ApiResponse<List<PaymentResponse>> findListOfPaymentByDateRange(String from, String to) {
        List<Payment> paymentList = paymentRepository.findByDateCreatedBetween(from,to);

        if(paymentList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(paymentList, PaymentResponse.class));
    }



}
