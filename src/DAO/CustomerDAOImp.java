package DAO;

import model.Customer;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import static utilities.TimeConv.stringToCalendar;

/**Class for accessing and editing customer data**/
public class CustomerDAOImp {
    /**
     * Returns customers to fill input fields.
     * @param customerName
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static Customer getCustomer(String customerName) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "select * FROM customers WHERE Customer_Name = '" + customerName + "'";
        Query.makeQuery(sqlStatement);
        Customer customerResult;
        ResultSet result=Query.getResults();
        while(result.next()){
            int customerId = result.getInt("Customer_ID");
            String CustomerName = result.getString("Customer_Name");
            String address= result.getString("Address");
            String postalCode = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int divisionId = result.getInt("Division_ID");
            Calendar createDateCalendar = stringToCalendar(createDate);
            Calendar lastUpdateCalendar = stringToCalendar(lastUpdate);
            customerResult = new Customer(customerId, CustomerName, address, postalCode, phone, createDateCalendar, createdBy,lastUpdateCalendar,lastUpdatedBy,divisionId);
            return customerResult;
        }
        DBConnection.closeConnection();
        return null;
    }

    public static void updateCustomer(int customerId, String customerName, String address, String postalCode,
                                      String createDate, String createdBy, String lastUpdate,
                                      String lastUpdatedBy, int divisionId) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "UPDATE customers SET Customer_Name = '" + customerName + "'" +
                ", Address = '" + address + "', Postal_Code = '" + postalCode + "'" +
                ", Create_Date  = '" + createDate + "', Created_By = '" + createdBy + "'" +
                ", Last_Update = '" + lastUpdate + "', Last_Updated_By = '" + lastUpdatedBy + "'" +
                ", Division_ID = '" + divisionId + "' " +
                " WHERE Customer_ID = '" + customerId + "'";
        Query.makeQuery(sqlStatement);

        DBConnection.closeConnection();
    }

    public static void deleteCustomer(int customerId){
        DBConnection.openConnection();
        String sqlStatement = "DELETE FROM customers WHERE Customer_ID = '" +customerId+"'";
        Query.makeQuery(sqlStatement);
        DBConnection.closeConnection();

    }

    public static void addCustomer(String customerName, String address, String postalCode,
                                      String createDate, String createdBy, String lastUpdate,
                                      String lastUpdatedBy, int divisionId) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "INSERT INTO customers (Customer_Name, Address, Postal_Code, " +
                "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) Values" +
                "('" + customerName + "', '" + address + "' , '" + postalCode+ "', '" +createDate+ "'" +
                ", '" + createdBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "', '" + divisionId + "')";
        Query.makeQuery(sqlStatement);

        DBConnection.closeConnection();
    }

}

