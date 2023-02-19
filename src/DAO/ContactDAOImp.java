package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO implementation for contact table
 */
public class ContactDAOImp {
    /**
     * Get contact based on ID.
     *
     * @param inpContactID
     * @return contact based on Id.
     * @throws SQLException
     * @throws Exception
     */
    public static Contact getContact(int inpContactID) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM contacts WHERE Contact_ID = '" + inpContactID + "'";
        Query.makeQuery(sqlStatement);
        Contact contactResult;
        ResultSet result = Query.getResults();
        while (result.next()) {
            int contactId = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String contactEmail = result.getString("Email");

            contactResult = new Contact(contactId, contactName, contactEmail);
            return contactResult;
        }
        DBConnection.closeConnection();
        return null;
    }

    /**
     * Overload for getting contact by name.
     *
     * @param inpContactName
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static Contact getContact(String inpContactName) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM contacts WHERE Contact_Name = '" + inpContactName + "'";
        Query.makeQuery(sqlStatement);
        Contact contactResult;
        ResultSet result = Query.getResults();
        while (result.next()) {
            int contactId = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String contactEmail = result.getString("Email");

            contactResult = new Contact(contactId, contactName, contactEmail);
            return contactResult;
        }
        DBConnection.closeConnection();
        return null;
    }

    /**
     * Get all contacts.
     *
     * @return list of contacts.
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException, Exception {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from Contacts";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResults();
        while (result.next()) {
            int contactId = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String contactEmail = result.getString("Email");

            Contact contactResult = new Contact(contactId, contactName, contactEmail);
            allContacts.add(contactResult);
        }
        DBConnection.closeConnection();
        return allContacts;
    }
}
