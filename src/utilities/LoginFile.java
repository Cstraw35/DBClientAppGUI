package utilities;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * Class for setting up the login file.
 */
public class LoginFile {

    /**
     * Writes to the login activity file for each login attempt.
     * @param userName
     * @param time
     * @param successful
     * @throws IOException
     */
    public static void userLoginAttempt(String userName, ZonedDateTime time, String successful) throws IOException {

        System.out.println("Writing to file");
        String fileName = "login_activity.Txt";
        FileWriter userFile = new FileWriter(fileName, true);
        PrintWriter writeFile = new PrintWriter(userFile);
        String userStore = userName;
        ZonedDateTime currentTime = time;
        String check = successful;
        writeFile.print("User: " + userStore + "  Date and Time: " + time + "  Login attempt result: " + check);
        writeFile.println();
        writeFile.close();

    }
}
