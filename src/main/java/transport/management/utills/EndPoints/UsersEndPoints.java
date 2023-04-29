    package transport.management.utills.EndPoints;



public class UsersEndPoints {

    public static final String users="users";

    public static final String CUSTOMER="/customer";
    public static final String FIND_CUSTOMER=CUSTOMER+"/list";
    public static final String ADD_CUSTOMER=CUSTOMER+"/add";
    public static final String FIND_CUSTOMER_BY_ID=CUSTOMER+"/{id}";
    public static final String UPDATE_CUSTOMER= CUSTOMER+"/update/{id}";
    public static final String DELETE_CUSTOMER= CUSTOMER+"/customerDelete/{id}";
    public static final String LOGIN_CUSTOMER= CUSTOMER+"loginCustomer";
    public static final String RESET_PASSWORD= CUSTOMER+"/resetPassword";
    public static final String FORGOT_CUSTOMER_PASSWORD= CUSTOMER+"/forgotCustomerPassword";


    public static final String SELLER="/seller";
    public static final String FIND_SELLER=SELLER+"/list";
    public static final String ADD_SELLER= SELLER+"/add";
    public static final String FIND_SELLER_BY_ID= SELLER+"/{id}";
    public static final String UPDATE_SELLER= SELLER+"/update/{id}";
    public static final String DELETE_SELLER= SELLER+"/delete/{id}";
    public static final String LOGIN_SELLER= SELLER+"loginEmployee";
    public static final String RESET_SELLER_PASSWORD= SELLER+"/resetPassword";
    public static final String FORGOT_SELLER_PASSWORD= SELLER+"/forgotEmployeePassword";


    public static final String USER="";
    public static final String FIND_USER=USER+"/list";
    public static final String ADD_USER= USER+"/add";
    public static final String FIND_USER_BY_ID= USER+"/{id}";
    public static final String UPDATE_USER= USER+"/update/{id}";
    public static final String DELETE_USER= USER+"/delete/{id}";
    public static final String RESET_USER_PASSWORD= USER+"/resetPassword";
    public static final String FORGOT_USER_PASSWORD= USER+"/forgotUserPassword";
    public static final String LOGIN_USER= USER+"/loginUser";
    public static final String AUTHENTICATE_USER= USER+"authenticate";
    public static final String GIVE_ACCESS_TO_USER= USER+"/access/{uuid}/{userRole}";


    public static final String IMAGE="/image";
    public static final String FIND_IMAGE=IMAGE+"/list";
    public static final String ADD_IMAGE= IMAGE+"/add";
    public static final String FIND_IMAGE_BY_ID= IMAGE+"/{id}";
    public static final String UPDATE_IMAGE= IMAGE+"/update/{id}";
    public static final String DELETE_IMAGE= IMAGE+"/delete/{id}";






}
