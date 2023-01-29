package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.FirstLevelDivision;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO implementation for first level divisions.
 */
public class FirstLevelDivisionDAOimp {
    public static ObservableList<FirstLevelDivision> getCorrelatedFLD(int inpCountryId) throws SQLException, Exception {
        ObservableList<FirstLevelDivision> correlatedFLD = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from first_level_divisions WHERE Country_ID = '" + inpCountryId + "'";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResults();
        while (result.next()) {
            int divisionId = result.getInt("Division_ID");
            String division = result.getString("Division");
            int countryId = result.getInt("Country_ID");
            FirstLevelDivision fldResult = new FirstLevelDivision(divisionId,division, countryId);
            correlatedFLD.add(fldResult);
        }
        DBConnection.closeConnection();
        return correlatedFLD;
    }
}
