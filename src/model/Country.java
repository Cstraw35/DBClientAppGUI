package model;

public class Country {
    private int id;
    private String Country;

    /**
     * Constructor with no variables for Country object.
     */
    public Country() {
    }

    /**
     * Constructor with all variables for Country object.
     *
     * @param id
     * @param country
     */
    public Country(int id, String country) {
        this.id = id;
        Country = country;
    }

    /**
     * Getter for country id.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for country.
     *
     * @return
     */
    public String getCountry() {
        return Country;
    }
}
