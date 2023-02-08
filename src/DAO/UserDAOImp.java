package DAO;


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

}
