package DAO;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import static DAO.DBConnection.connection;

public class Query {
    private static String query;
    private static Statement statement;
    private static ResultSet results;

    public static void makeQuery(String q) {
        query = q;
        try {
            statement = connection.createStatement();
            if (query.toLowerCase().startsWith("select"))
                results = statement.executeQuery(q);
            if (query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") || query.startsWith("update"))
                statement.executeUpdate(q);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ResultSet getResults() {
        return results;
    }
}
