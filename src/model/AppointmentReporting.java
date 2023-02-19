package model;

import DAO.AppointmentsDAOImp;
import javafx.collections.ObservableList;

import java.time.Month;
import java.time.ZonedDateTime;

/**
 * Class for creating appointment object
 */
public class AppointmentReporting extends Appointment {
    private String contactName;
    private int typeCount;
    private Month month;
    private int monthCount;

    /**
     * Default constructor
     */
    public AppointmentReporting() {
        super();
    }

    /**
     * Constructor for month and type counts.
     *
     * @param type
     */
    public AppointmentReporting(String type) {
        this.type = type;
    }

    /**
     * Constructor from superclass plus add contact name.
     *
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param contactName
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerId
     * @param userId
     * @param contactId
     */

    public AppointmentReporting(int appointmentId, String title, String description, String location, String contactName, String type, ZonedDateTime start, ZonedDateTime end, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        super(appointmentId, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId);
        this.contactName = contactName;
    }


    /**
     * Gets the count for type from all appointments.
     *
     * @return
     * @throws Exception
     */
    public int getTypeCount() throws Exception {
        typeCount = 0;
        ObservableList<AppointmentReporting> allAppointments = AppointmentsDAOImp.getAllAppointmentsWithContact();
        for (int i = 0; i < allAppointments.size(); i++) {
            if (super.type.equals(allAppointments.get(i).getType())) {
                typeCount += 1;
            }
        }
        return typeCount;
    }

    /**
     * Gets the current appointments month.
     *
     * @return
     */
    public Month getMonth() {
        this.month = getStart().getMonth();
        return month;

    }

    /**
     * Gets the number of appointments for each month.
     *
     * @return number of appointments for each month.
     */
    public int getMonthCount() throws Exception {

        monthCount = 0;
        ObservableList<AppointmentReporting> allAppointments = AppointmentsDAOImp.getAllAppointmentsWithContact();
        for (int i = 0; i < allAppointments.size(); i++) {
            if (this.month.equals(allAppointments.get(i).getStart().getMonth())) {
                monthCount += 1;
            }
        }
        return monthCount;
    }

    /**
     * gets default local time.
     *
     * @return system default start local time.
     */

    public String getContactName() {
        return contactName;
    }

    /**
     * Set contactName
     *
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}

