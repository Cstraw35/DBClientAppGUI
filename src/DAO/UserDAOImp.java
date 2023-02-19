package DAO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static utilities.TimeConv.stringToDate;

/**
 * class for accessing user data. This is read only data per specifications
 **/
public class UserDAOImp {
    /**
     * Gets user from DB.
     *
     * @param userName
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static User getUser(String userName) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM users WHERE User_Name = '" + userName + "'";
        Query.makeQuery(sqlStatement);
        User userResult;
        ResultSet result = Query.getResults();
        while (result.next()) {
            int userId = result.getInt("User_ID");
            String UserName = result.getString("User_Name");
            String password = result.getString("Password");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            Date createDateFormatted = stringToDate(createDate);
            Date lastUpdateFormatted = stringToDate(lastUpdate);
            userResult = new User(userId, UserName, password, createDateFormatted, createdBy, lastUpdateFormatted, lastUpdatedBy);
            return userResult;
        }
        DBConnection.closeConnection();
        return null;
    }

    public static ObservableList<User> getAllUsers() throws Exception {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from users";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResults();
        while (result.next()) {
            int userId = result.getInt("User_ID");
            String userName = result.getString("User_Name");
            String password = result.getString("Password");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            Date createDateFormatted = stringToDate(createDate);
            Date lastUpdateFormatted = stringToDate(lastUpdate);
            User userResult = new User(userId, userName, password, createDateFormatted, createdBy, lastUpdateFormatted, lastUpdatedBy);
            allUsers.add(userResult);
        }
        DBConnection.closeConnection();
        return allUsers;
    }

    /**
     * Add a new user. (Only going to be allowed if admin is logged in.)
     *
     * @param userName
     * @param password
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @throws SQLException
     * @throws Exception
     */
    public static void addUser(String userName, String password,
                               String createDate, String createdBy, String lastUpdate,
                               String lastUpdatedBy) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "INSERT INTO users (User_Name, Password, " +
                "Create_Date, Created_By, Last_Update, Last_Updated_By) Values('" + userName + "', '" + password + "' , '" + createDate + "'" +
                ", '" + createdBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "')";
        Query.makeQuery(sqlStatement);

        DBConnection.closeConnection();
    }

    /**
     * Delete user by user ID.(Only allowed by Admin.)
     *
     * @param userName
     */
    public static void deleteUser(String userName) {

        DBConnection.openConnection();
        String sqlStatement = "DELETE FROM users WHERE User_Name = '" + userName + "'";
        Query.makeQuery(sqlStatement);
        DBConnection.closeConnection();


    }

}

