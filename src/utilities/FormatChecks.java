package utilities;

import javafx.collections.ObservableList;

/**
 * Class to check address input field.
 */
public class FormatChecks {
    /**
     * check if address meets format requirements.
     *
     * @param inpAddress
     * @param inpcountry
     * @return boolean.
     */
    public static boolean formatAddress(String inpAddress, String inpcountry) {
        System.out.println(inpcountry);
        String[] stringArray = (inpAddress.split(","));
        System.out.println(stringArray.length);
        if (inpcountry.equals("UK")) {

            if (stringArray.length == 3) {
                return true;
            } else {
                Alerts.errorAlert("Wrong address format", "Please make sure to include street, city, post town" +
                        " all separated by commas.(2 commas total) ");
                return false;

            }
        } else {
            if (stringArray.length == 2) {
                return true;
            } else {
                Alerts.errorAlert("Wrong address format", "Please make sure to include street and city" +
                        " separated by a comma.");

                return false;


            }

        }
    }

    /**
     * Check phone number is in the correct format for country.
     *
     * @param phone
     * @param inpcountry
     * @return
     */
    public static boolean checkPhone(String phone, String inpcountry) {
        String northPattern = "\\d{3}-\\d{3}-\\d{4}";
        String ukPattern = "\\d{2}-\\d{3}-\\d{3}-\\d{4}";
        if (inpcountry.equals("UK")) {
            if (phone.matches(ukPattern)) {
                return true;
            } else {
                Alerts.errorAlert("Wrong number format", "For Uk country selected your phone number should " +
                        " be in the following format. xx-xxx-xxx-xxxx");
                return false;
            }
        } else {
            if (phone.matches(northPattern)) {
                return true;
            } else {
                Alerts.errorAlert("Wrong number format", "For US and Canada selected your phone number should" +
                        "be in the following format. xxx-xxx-xxxx");
                return false;
            }
        }
    }


}
