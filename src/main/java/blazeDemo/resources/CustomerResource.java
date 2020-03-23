package blazeDemo.resources;

import blazeDemo.api.Customer;
import blazeDemo.api.Paginated;
import blazeDemo.db.daos.CustomerDAO;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;

import org.bson.types.ObjectId;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.IntParam;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private final CustomerDAO customerDAO;

    public CustomerResource(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    
    @GET
    @Path("allCustomers")
    public Response all() {
        final List<Customer> customersList = customerDAO.getAll();
        if (customersList.isEmpty()) {
            return Response.accepted(new blazeDemo.api.Response("Customers not found."))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response.ok(customersList).build();
    }
    
    @GET
    public Response query(
            @Context javax.ws.rs.core.UriInfo info,
            @QueryParam("offset") @DefaultValue("-1") IntParam _offset, @QueryParam("limit") @DefaultValue("-1") IntParam _limit) {

        int offset = _offset.get();
        int limit = _limit.get();

        if (offset == -1 || limit == -1) {
            offset = offset == -1 ? 0 : offset;
            limit = limit == -1 ? 10 : limit;
        }
        
        final List<Customer> customers  = customerDAO.getAllv2(offset,limit);
            
        if (customers.isEmpty()) {
            return Response.accepted(new blazeDemo.api.Response("Customers not found."))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        
        long total = customerDAO.count();
        long pages = customerDAO.getPages(limit);
        
        return Response.ok(new Paginated(customers, total, offset,limit,pages)).build();        
    }
    
    //find by id 
    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") @NotNull final ObjectId id) {
        final Customer customer = customerDAO.find(id);
        if (customer != null) {
            return Response.ok(customer).build();
        }
        return Response.accepted(new blazeDemo.api.Response("Customer not found.")).build();
    }
    
    //store
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@NotNull final Customer customer) {
        customerDAO.save(customer);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") @NotNull final ObjectId id,
                           @NotNull final Customer customer) {
        customerDAO.update(id, customer);
        return Response.ok().build();
    }
    
    
    //delete
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @NotNull final ObjectId id) {
        customerDAO.delete(id);
        return Response.ok().build();
    }
}
