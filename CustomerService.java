package service;

import model.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Service class to manage customer-related operations.
 */
public class CustomerService {

    private static final CustomerService SINGLETON = new CustomerService();

    private final Map<String, customer> customers = new HashMap<>();

    private CustomerService() {}

     /**
     * Retrieves the singleton instance of CustomerService.
     */
    public static CustomerService getSingleton() {
        return SINGLETON;
    }

     /**
     * Adds a new customer to the system.
     * @param email Customer's email ID.
     * @param firstName Customer's first name.
     * @param lastName Customer's last name.
     */
    public void addCustomer(final String email_id, final String fName, final String lName) {
        customers.put(email_id, new customer(fName, lName, email_id));
    }

    /**
     * Retrieves all customers in the system.
     * @return A collection of all customers.
     */
    public Collection<customer> getAllCustomers() {
        return customers.values();
    }

    /**
     * Retrieves a customer based on their email ID.
     * @param email Customer's email ID.
     * @return The customer object if found, otherwise null.
     */
    public customer getCustomer(final String customeremail_id) {
        return customers.get(customeremail_id);
    }

    
}
