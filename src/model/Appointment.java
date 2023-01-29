package model;

import java.util.Date;
import java.util.Date;

/**
 * Class for creating appointment object
 */
public class Appointment {
    private int appointmentId ;
    private String title;
    private String location;
    private String type;
    private Date start;
    private Date end;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;

    /**
     * Constructor with no variables for appointment object.
     */
    public Appointment() {
    }

    /**
     * Constructor with all variables for appointment object.
     * @param appointmentId
     * @param title
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerId
     * @param userId
     * @param contactId
     */
    public Appointment(int appointmentId, String title, String location, String type, Date start, Date end, String createdBy, Date lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * @return id.
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Set appointment id.
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return start date.
     */
    public Date getStart() {
        return start;
    }

    /**
     * Sets start date.
     * @param start
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return end date.
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Set end date.
     * @param end
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * @return created by.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets created by.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return last update date.
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets last update date.
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
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return customer id.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets customer id.
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * gets user id.
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * sets user id.
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets contact id.
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets contact id.
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
