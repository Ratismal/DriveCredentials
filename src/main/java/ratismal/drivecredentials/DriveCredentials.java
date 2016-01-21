package ratismal.drivecredentials;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;

/**
 * Created by Ratismal on 2016-01-21.
 */

public class DriveCredentials {

    //Variables
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.dir"));
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static HttpTransport httpTransport = new NetHttpTransport();


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
        System.out.println("OK! Let's begin.");
        try {
            authorize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("And that should be it! Hopefully everything worked ok!");
        System.out.println("Type anything to exit.");
        c.readLine();
    }

    public static Credential authorize() throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(DriveCredentials.class.getResourceAsStream("/client_secrets.json")));

        DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        httpTransport, JSON_FACTORY, clientSecrets, Arrays.asList(DriveScopes.DRIVE))
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
}