package com.troncoguide.androidapp.troncoguide.TestUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Alejandro on 12/3/2015.
 */
public class NetworkUtils {
    public static String readDiscoveryResponse() {
        String response = "";
        //reading from the file
        File file = new File("src/test/assets/DiscoverResponse");
        try {

            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = buffer.readLine()) != null) {
                response += line;
            }
            buffer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
