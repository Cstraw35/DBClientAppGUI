package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static utilities.TimeConv.stringToDate;

/**Class for accessing and editing customer data**/
public class CustomerDAOImp {
    /**
     * Returns customers to fill input fields.
     * @param customerName
     * @return customer.
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
            Date createDateFormatted = stringToDate(createDate);
            Date lastUpdateFormatted = stringToDate(lastUpdate);
            customerResult = new Customer(customerId, CustomerName, address, postalCode, phone, createDateFormatted, createdBy,lastUpdateFormatted,lastUpdatedBy,divisionId);
            return customerResult;
        }
        DBConnection.closeConnection();
        return null;
    }

    /**
     * Overload for getCustomer using ID instead of name.
     * @param customerID
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static Customer getCustomer(int customerID) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "select * FROM customers WHERE Customer_ID = '" + customerID + "'";
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
            Date createDateFormatted = stringToDate(createDate);
            Date lastUpdateFormatted = stringToDate(lastUpdate);
            customerResult = new Customer(customerId, CustomerName, address, postalCode, phone, createDateFormatted, createdBy,lastUpdateFormatted,lastUpdatedBy,divisionId);
            return customerResult;
        }
        DBConnection.closeConnection();
        return null;
    }

    /**
     * Method to get all customers from customers SQL table.
     * @return all customers
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from customers";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResults();
        while (result.next()) {
            int customerId = result.getInt("Customer_ID");
            String CustomerName = result.getString("Customer_Name");
            String address = result.getString("Address");
            String postalCode = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int divisionId = result.getInt("Division_ID");
            Date createDateFormatted = stringToDate(createDate);
            Date lastUpdateFormatted = stringToDate(lastUpdate);
            Customer customerResult = new Customer(customerId, CustomerName, address, postalCode, phone, createDateFormatted, createdBy, lastUpdateFormatted, lastUpdatedBy, divisionId);
            allCustomers.add(customerResult);
        }
        DBConnection.closeConnection();
        return allCustomers;
    }

    /**
     * Update statement to update customer in customers table.
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId
     * @throws SQLException
     * @throws Exception
     */
    public static void updateCustomer(int customerId, String customerName, String address, String postalCode, String phone,
                                      String createDate, String createdBy, String lastUpdate,
                                      String lastUpdatedBy, int divisionId) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "UPDATE customers SET Customer_Name = '" + customerName + "'" +
                ", Address = '" + address + "', Postal_Code = '" + postalCode + "', '" +phone+ "' "+
                ", Create_Date  = '" + createDate + "', Created_By = '" + createdBy + "'" +
                ", Last_Update = '" + lastUpdate + "', Last_Updated_By = '" + lastUpdatedBy + "'" +
                ", Division_ID = '" + divisionId + "' " +
                " WHERE Customer_ID = '" + customerId + "'";
        Query.makeQuery(sqlStatement);

        DBConnection.closeConnection();
    }

    /**
     * Delete customer from customers table.
     * @param customerId
     */
    public static void deleteCustomer(int customerId){

        DBConnection.openConnection();
        String sqlStatement = "DELETE FROM customers WHERE Customer_ID = '" +customerId+"'";
        Query.makeQuery(sqlStatement);
        DBConnection.closeConnection();

    }

    /**
     * Add customer to customer table.
     * @param customerName
     * @param address
     * @param postalCode
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId
     * @throws SQLException
     * @throws Exception
     */
    public static void addCustomer(String customerName, String address, String postalCode, String phone,
                                      String createDate, String createdBy, String lastUpdate,
                                      String lastUpdatedBy, int divisionId) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, "+
                "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) Values" +
                "('" + customerName + "', '" + address + "' , '" + postalCode+ "', '" +phone+ "', '" +createDate+ "'" +
                ", '" + createdBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "', '" + divisionId + "')";
        Query.makeQuery(sqlStatement);

        DBConnection.closeConnection();
    }

}

