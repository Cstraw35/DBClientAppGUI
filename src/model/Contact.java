package model;

/**
 * Model for conacts
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * Constructor with no parameters.
     */
    public Contact() {
    }

    /**
     * Constructor with all parameters
     *
     * @param contactId
     * @param contactName
     * @param contactEmail
     */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * Gets contact id.
     *
     * @return contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Gets contact name.
     *
     * @return contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Gets contact email.
     *
     * @return contact email
     */
    public String getContactEmail() {
        return contactEmail;
    }
}
