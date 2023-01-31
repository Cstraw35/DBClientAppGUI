package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static utilities.TimeConv.stringToDate;

public class AppointmentsDAOImp {
    /**
     * Gets appointment by id.
     * @param appointmentID
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static Appointment getAppointment(int appointmentID) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "select * FROM appointments WHERE Appointment_ID = '" + appointmentID + "'";
        Query.makeQuery(sqlStatement);
        Appointment appointmentResult;
        ResultSet result=Query.getResults();
        while(result.next()){
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            String start = result.getString("Start");
            String end = result.getString("End");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("LastUpdate");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            Date startFormatted = stringToDate(start);
            Date endFormatted = stringToDate(end);
            Date createDateFormatted = stringToDate(createDate);
            Date lastUpdateFormatted = stringToDate(lastUpdate);
            appointmentResult = new Appointment (appointmentId, title, description, location, type, startFormatted, endFormatted,
                createDateFormatted, createdBy, lastUpdateFormatted, lastUpdatedBy, customerID, userID, contactID);
            return appointmentResult;
        }
        DBConnection.closeConnection();
        return null;
    }

    /**
     * Get all appointments to fill table.
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResults();
        while (result.next()) {
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            String start = result.getString("Start");
            String end = result.getString("End");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("LastUpdate");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            Date startFormatted = stringToDate(start);
            Date endFormatted = stringToDate(end);
            Date createDateFormatted = stringToDate(createDate);
            Date lastUpdateFormatted = stringToDate(lastUpdate);
            Appointment appointmentResult = new Appointment (appointmentId, title, description, location, type, startFormatted, endFormatted,
                    createDateFormatted, createdBy, lastUpdateFormatted, lastUpdatedBy, customerID, userID, contactID);
            allAppointments.add(appointmentResult);
        }
        DBConnection.closeConnection();
        return allAppointments;
    }

    public static ObservableList<Appointment> getCustomerAppointments(int customerId) throws SQLException, Exception {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from appointments Where Customer_ID = '" + customerId + "'";

        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResults();
        while (result.next()) {
            int appointmentId = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            String start = result.getString("Start");
            String end = result.getString("End");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            Date startFormatted = stringToDate(start);
            Date endFormatted = stringToDate(end);
            Date createDateFormatted = stringToDate(createDate);
            Date lastUpdateFormatted = stringToDate(lastUpdate);
            Appointment appointmentResult = new Appointment (appointmentId, title, description, location, type, startFormatted, endFormatted,
                    createDateFormatted, createdBy, lastUpdateFormatted, lastUpdatedBy, customerID, userID, contactID);
            allAppointments.add(appointmentResult);
        }
        DBConnection.closeConnection();
        return allAppointments;
    }

    /**
     * Update appointment.
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     */
    public static void updateAppointment(int appointmentId, String title, String description, String location,
                                         String type, String start, String end, String createDate, String createdBy,
                                         String lastUpdate,String lastUpdatedBy, int customerID,int userID, int contactID){
            DBConnection.openConnection();
        String sqlStatement = "UPDATE appointments SET Title = '" + title + "'" +
                ", Description = '" + description + "', Location = '" + location + "', Type '" +type+ "' "+
                ", Start  = '" + start + "', end = '" + end + "'" +
                ", Create_Date = '" + createDate + "', Created_By = '" + createdBy + "'" +
                ", Last_Update = '" + lastUpdate + "' " +
                " Last_Updated_By = '" +lastUpdatedBy + ", Customer_ID = '" +customerID+"', User_ID = '" +userID+"'" +
                ", Contact_ID '" + contactID+ "'";

        Query.makeQuery(sqlStatement);

        DBConnection.closeConnection();
    }

    /**
     * Delete appointment from DB.
     * @param appointmentID
     */
    public static void deleteAppointment(int appointmentID){

        DBConnection.openConnection();
        String sqlStatement = "DELETE FROM appointments WHERE Customer_ID = '" +appointmentID+"'";
        Query.makeQuery(sqlStatement);
        DBConnection.closeConnection();

    }

    /**
     * Add appointment to DB.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     * @throws SQLException
     * @throws Exception
     */
    public static void addAppointment(String title, String description, String location,
                                   String type, String start, String end, String createDate, String createdBy,
                                   String lastUpdate,String lastUpdatedBy, int customerID,int userID, int contactID) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "INSERT INTO appointments (Title, Description, Location, Type, "+
                "Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) Values" +
                "('" + title + "', '" + description + "' , '" + location + "', '" +type+ "', '" +start+ "'" +
                ", '" + end + "', '" + createDate + "', '" + createdBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "'," +
                "'" + customerID + "', '" + userID+ "', '" + contactID + "')";

        Query.makeQuery(sqlStatement);

        DBConnection.closeConnection();
    }

}


