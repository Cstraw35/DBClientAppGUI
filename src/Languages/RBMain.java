package Languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class RBMain {
    public static void main(String[] args){
        ResourceBundle rb = ResourceBundle.getBundle("Languages/ResourceBundle_RB", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr")){
            System.out.println(rb.getString("Login"));
        }
    }
}
