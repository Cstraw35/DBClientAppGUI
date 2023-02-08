package model;

public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private int countryId;

    /**
     * Constructor for first level division with no params.
     */
    public FirstLevelDivision() {
    }

    /**
     * Constructor for first level division with all params.
     *
     * @param divisionId
     * @param division
     * @param countryId
     */
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * Gets division id.
     * @return id.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Gets division name.
     * @return division.
     */
    public String getDivision() {
        return division;
    }

    /**
     * Gets country id.
     * @return country id.
     */
    public int getCountryId() {
        return countryId;
    }
}
