package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static utilities.TimeConv.stringToDate;

/**
 * Class for country DAO imp.
 */
public class CountryDAOImp {
    /**
     * class to get selected country
     * @param inpcountry
     * @return country object.
     * @throws SQLException
     * @throws Exception
     */
    public static Country getCountry(String inpcountry) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "select * FROM countries WHERE Country = '" + inpcountry + "'";
        Query.makeQuery(sqlStatement);
        Country countryResult;
        ResultSet result=Query.getResults();
        while(result.next()){
            int countryId = result.getInt("Country_ID");
            String country = result.getString("Country");

            countryResult = new Country(countryId, country);
            return countryResult;
        }
        DBConnection.closeConnection();
        return null;
    }
    public static ObservableList<Country> getAllCountries() throws SQLException, Exception {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from Countries";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResults();
        while (result.next()) {
            int countryId = result.getInt("Country_ID");
            String country = result.getString("Country");
            Country countryResult = new Country(countryId, country);
            allCountries.add(countryResult);
        }
        DBConnection.closeConnection();
        return allCountries;
    }

}
