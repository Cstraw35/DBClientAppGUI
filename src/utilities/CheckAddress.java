package utilities;

/**
 * Class to check address input field.
 */
public class CheckAddress {
    /**
     * check if address meets format requirements.
     * @param inpAddress
     * @param inpcountry
     * @return boolean.
     */
    public static boolean formatAddress(String inpAddress, String inpcountry) {
        if (inpcountry == "UK") {
            if (inpAddress.split(",").length == 3) {
                return true;
            } else {
                Alerts.errorAlert("Wrong address format", "Please make sure to include street, city, post town" +
                        " all separated by commas.(2 commas total) ");
                return false;

            }
        } else {
            if (inpAddress.split(",").length == 2) {
                return true;
            } else {
                Alerts.errorAlert("Wrong address format", "Please make sure to include street and city" +
                        " separated by a comma.");

                return false;


            }

        }
    }
}
