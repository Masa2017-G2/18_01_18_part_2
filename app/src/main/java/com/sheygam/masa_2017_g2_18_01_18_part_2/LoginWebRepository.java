package com.sheygam.masa_2017_g2_18_01_18_part_2;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gregorysheygam on 18/01/2018.
 */

public class LoginWebRepository implements ILoginWebRepository {
    private Gson gson;
    private static final String BASE_URL = "https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";

    public LoginWebRepository(Gson gson) {
        this.gson = gson;
    }

    @Override
    public AuthToken login(Auth auth) {
        return null;
    }

    @Override
    public AuthToken registration(Auth auth) throws Exception {
        String jsonBodyRequest = gson.toJson(auth);
        URL url = new URL(BASE_URL + "/registration");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("Content-Type","application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);

//        connection.connect();
        OutputStream out = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(jsonBodyRequest);
        bw.flush();
        bw.close();

        String line;
        String result = "";

        if(connection.getResponseCode() < 400){
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = br.readLine())!= null){
                result += line;
            }

            AuthToken authToken = gson.fromJson(result,AuthToken.class);
            return authToken;
        }else if(connection.getResponseCode() == 409){
            throw new Exception("User already exist!");
        }else{
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while((line = br.readLine())!= null){
                result += line;
            }
            Log.d("MY_TAG", "registration: error: " + result);
            throw new Exception("Server error! Call to support");
        }
    }
}
