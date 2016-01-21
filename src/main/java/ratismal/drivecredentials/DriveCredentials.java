package ratismal.drivecredentials;

import java.awt.*;
import java.io.Console;
import java.net.URI;

/**
 * Created by Ratismal on 2016-01-21.
 */

public class DriveCredentials {

    public static void main(String[] arguments) {
        Console c = System.console();
        System.out.println("Hello! This is a simple program to generate a StoredCredential file.");
        System.out.println("You'll see a webpage pop up shortly. All you need to do is click 'allow'.");
        System.out.println("Naturally, you need to have a UI OS with a web browser to do this. ");
        System.out.println("This program is completely open source, so you can make sure that I'm not doing");
        System.out.println("anything naughty with it!");
        System.out.println("To open the repo, type 'y' now. Type anything else to continue.");
        String line = c.readLine();
        if (line.equalsIgnoreCase("y")) {
            System.out.println("Opening github repo. Enter anything to continue.");
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI("https://github.com/Ratismal/DriveCredentials"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            c.readLine();
        }
    }
}