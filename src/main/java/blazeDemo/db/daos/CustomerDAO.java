package blazeDemo.db.daos;

import blazeDemo.api.Customer;
import blazeDemo.util.CustomerMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.Document;
import org.bson.types.ObjectId;

public class CustomerDAO {
    final MongoCollection<Document> customerCollection;

    //constructor
    public CustomerDAO(MongoCollection<Document> customerCollection) {
        this.customerCollection = customerCollection;
    }
    
    //CRUD FUNCTIONS
    
    //index
    public List<Customer> getAll() {
        final MongoCursor<Document> customers = customerCollection.find().iterator();
        final List<Customer> customersList = new ArrayList<>();
        try {
            while (customers.hasNext()) {
                final Document customer = customers.next();
                customersList.add(CustomerMapper.map(customer));
            }
        } finally {
            customers.close();
        }
        return customersList;
    }
    
    //find by id
    public Customer find(ObjectId id) {
            Optional<Document> customerFind = Optional.ofNullable(customerCollection.find(new Document("_id", id)).first());
            return customerFind.isPresent() ? CustomerMapper.map(customerFind.get()) : null;
    }

    public void save(Customer customer) {
            Document saveCustomer = new Document("firstName", customer.getFirstName())
                .append("lastName", customer.getLastName())
                .append("email", customer.getEmail())
                .append("phoneNumber", customer.getPhoneNumber());
            customerCollection.insertOne(saveCustomer);
    }

    public void update(ObjectId id, Customer customer) {
            customerCollection.updateOne(new Document("_id",id), 
                new Document("$set", new Document("firstName", customer.getFirstName())
                    .append("lastName", customer.getLastName())
                    .append("email", customer.getEmail())
                    .append("phoneNumber", customer.getPhoneNumber()))
            );
    }

    public void delete(ObjectId id) {
            customerCollection.deleteOne(new Document("_id",id));
    }
}