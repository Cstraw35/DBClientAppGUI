package DAO;


import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import static utilities.TimeConv.stringToCalendar;

/** class for accessing user data. This is read only data per specifications **/
public class UserDAOImp {
    public static User getUser(String userName) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "select * FROM user WHERE User_Name = '" + userName + "'";
        Query.makeQuery(sqlStatement);
        User userResult;
        ResultSet result=Query.getResults();
        while(result.next()){
            int userId = result.getInt("User_ID");
            String UserName = result.getString("User_Name");
            String password = result.getString("Password");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("Last_Update");
            String lastUpdatedBy = result.getString("Last_Update_By");
            Calendar createDateCalendar = stringToCalendar(createDate);
            Calendar lastUpdateCalendar = stringToCalendar(lastUpdate);
            userResult = new User(userId, userName,password,createDateCalendar,createdBy,lastUpdateCalendar,lastUpdatedBy);
            return userResult;
        }
        DBConnection.closeConnection();
        return null;
    }

}
