package transport.management.model.listener;

import org.springframework.stereotype.Component;
import transport.management.model.entity.others.Document;
import transport.management.model.entity.products.Product;
import transport.management.model.entity.products.ProductCategory;
import transport.management.model.entity.services.Delivery;
import transport.management.model.entity.transaction.OrderItems;
import transport.management.model.entity.transaction.Orders;
import transport.management.model.entity.transaction.Payment;
import transport.management.model.entity.user.AppUser;
import transport.management.model.entity.user.Customer;
import transport.management.model.entity.user.Sellers;

import javax.persistence.PrePersist;
import java.util.UUID;

@Component
public class BaseListener {

    @PrePersist
    private void beforeCreate(Object data) {

        if(data instanceof Orders){
            Orders orders = (Orders) data;
            orders.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Document){
            Document document = (Document) data;
            document.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Product){
            Product product = (Product) data;
            product.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Payment){
            Payment payment = (Payment) data;
            payment.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Customer){
            Customer customer = (Customer) data;
            customer.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Sellers){
            Sellers sellers = (Sellers) data;
            sellers.setUuid(UUID.randomUUID());
        }

        else if(data instanceof AppUser){
            AppUser user = (AppUser) data;
            user.setUuid(UUID.randomUUID());
        }

        else if(data instanceof ProductCategory){
            ProductCategory productCategory = (ProductCategory) data;
            productCategory.setUuid(UUID.randomUUID());
        }

        else if(data instanceof Delivery){
            Delivery delivery = (Delivery) data;
            delivery.setUuid(UUID.randomUUID());
        }

        else if(data instanceof OrderItems){
            OrderItems orderItems = (OrderItems) data;
            orderItems.setUuid(UUID.randomUUID());
        }



    }








}
