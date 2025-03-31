package model;

import java.util.regex.Pattern;

/**
 * Represents a Customer with first name, last name, and email ID.
 */
public class customer {

    private static final String email_id_REGEX_PATTERN = "^(.+)@(.+).(.+)$";

    private final String fName;
    private final String lName;
    private final String email_id;

     /**
     * Constructs a Customer object with the provided details.
     *
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param emailId The email ID of the customer.
     * @throws IllegalArgumentException if the email format is invalid.
     */
    public customer(final String fName, final String lName, final String email_id) {
        this.isValidemail_id(email_id);

        this.fName = fName;
        this.lName = lName;
        this.email_id = email_id;
    }

    /**
     * Validates the provided email ID.
     *
     * @param emailId The email ID to validate.
     * @throws IllegalArgumentException if the email format is invalid.
     */
    private void isValidemail_id(final String email_id) {
        Pattern pattern = Pattern.compile(email_id_REGEX_PATTERN);

        if(!pattern.matcher(email_id).matches()) {
            throw new IllegalArgumentException("Invalid email_id");
        }
    }


    /**
     * Gets the email ID of the customer.
     *
     * @return The email ID.
     */
    public String getemail_id() {
        return this.email_id;
    }

     /**
     * Returns a string representation of the customer.
     *
     * @return A formatted string containing customer details.
     */
    @Override
    public String toString() {
        return "First Name: " + this.fName
                + " Last Name: " + this.lName
                + " email_id: " + this.email_id;
    }
}
