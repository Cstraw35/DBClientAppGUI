package model;

import DAO.AppointmentsDAOImp;

import javax.annotation.processing.Generated;
import java.util.Date;

/**
 * Object Class for customers
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private int appointmentCount;

    /**
     * Constructor for Customer object with no variables.
     */
    public Customer() {
    }

    /**
     * Constructor for Customer object with all variables.
     *
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionID
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy, int divisionID) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    public int getAppointmentCount() throws Exception {
        appointmentCount = AppointmentsDAOImp.getCustomerAppointments(customerId).size();
        return appointmentCount;
    }

    /**
     * Gets customer ID.
     *
     * @return customer id.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets customer Id.
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets customer name.
     *
     * @return customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets customer name.
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets customer address.
     *
     * @return address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets customer address.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets postal code.
     *
     * @return postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets customer postal code.
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets customer phone.
     *
     * @return phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets customer phone.
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets create date.
     *
     * @return create date.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets Create date.
     *
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets which user created.
     *
     * @return created by.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets created by.
     *
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets last time updated.
     *
     * @return last update date.
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets last update.
     *
     * @param lastUpdate
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return last updated by.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets last updated by.
     *
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Returns customer division id.
     *
     * @return division id.
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets division id.
     *
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
