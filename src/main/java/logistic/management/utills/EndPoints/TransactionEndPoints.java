package logistic.management.utills.EndPoints;

public class TransactionEndPoints {

    public static final String transaction="transaction";


    public static final String PAYMENT="/payment";
    public static final String FIND_PAYMENT=PAYMENT+"/list";
    public static final String ADD_PAYMENT= PAYMENT+"/add";
//  public static final String FIND_PAYMENT_BY_ID= PAYMENT+"/{id}";
    public static final String FIND_PAYMENT_BY_ORDER_ID = PAYMENT+"/findPaymentByOrderId";
    public static final String UPDATE_PAYMENT= PAYMENT+"/update/{id}";
    public static final String DELETE_PAYMENT= PAYMENT+"/delete/{id}";
    public static final String FIND_LIST_OF_PAYMENT_BY_DATE_RANGE= PAYMENT+"/findPaymentByDateRange";
    public static final String FIND_LISTS_OF_PAYMENT_BY_DATE= PAYMENT+"/findPaymentByDate";
    public static final String FIND_PAYMENT_BY_SALES_PERSON= PAYMENT+"/findPaymentBySalesPerson";
    public static final String FIND_PAYMENT_BY_CUSTOMER= PAYMENT+"/findPaymentByCustomer";


    public static final String ORDER_ITEMS="/orderItems";
    public static final String FIND_ORDER_ITEMS=ORDER_ITEMS+"/list";
//    public static final String ADD_ORDER_ITEMS= ORDER_ITEMS+"/add";
    public static final String FIND_ORDER_ITEMS_BY_ID= ORDER_ITEMS+"/{id}";
//    public static final String UPDATE_ORDER_ITEMS= ORDER_ITEMS+"/update/{id}";
//    public static final String DELETE_ORDER_ITEMS= ORDER_ITEMS+"/delete/{id}";

    public static final String ORDER="/order";
    public static final String FIND_ORDER=ORDER+"/list";
    public static final String ADD_ORDER= ORDER+"/add";
    public static final String FIND_ORDER_BY_ID= ORDER+"/{id}";
    public static final String UPDATE_ORDER= ORDER+"/update/{id}";
    public static final String DELETE_ORDER= ORDER+"/delete/{id}";
    public static final String FIND_ORDER_BY_CUSTOMER= ORDER+"/findOrderByCustomer";
    public static final String FIND_LISTS_OF_ORDER_BY_DATE= ORDER+"/findOrderByDate";




}
