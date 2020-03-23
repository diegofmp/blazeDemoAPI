package blazeDemo.util;

import blazeDemo.api.Customer;
import org.bson.Document;

public class CustomerMapper {
    
    //Map db document to customer object's proper form
    public static Customer map(final Document customerDocument) {
        final Customer customer = new Customer();
        customer.setId(customerDocument.getObjectId("_id"));
        customer.setFirstName(customerDocument.getString("firstName"));
        customer.setLastName(customerDocument.getString("lastName"));
        customer.setEmail(customerDocument.getString("email"));
        customer.setPhoneNumber(customerDocument.getString("phoneNumber"));
        return customer;
    }
}
