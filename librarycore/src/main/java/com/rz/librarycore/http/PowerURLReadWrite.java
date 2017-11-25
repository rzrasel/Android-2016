package com.rz.librarycore.http;

import com.rz.librarycore.log.LogWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Rz Rasel on 2016-11-25.
 */

class PowerURLReadWrite {
    protected static void onURLWriter(HttpURLConnection argHttpURLConnection, String argRequestParameters) {
        String urlParams = argRequestParameters;
        try {
            OutputStream outputStream = argHttpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(urlParams);
            writer.flush();
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            LogWriter.Log("PRINT_ERROR_IOException:- " + e.getMessage().toString());
        }
    }

    protected static String onURLReader(HttpURLConnection argHttpURLConnection) {
        String retVal = null;
        try {
            InputStream inputStream = null;
            StringBuilder stringBuilder = null;
            if (argHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = argHttpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    //sb.append(line + "\n");
                    stringBuilder.append(line);
                }
                inputStream.close();
                //argHttpURLConnection.disconnect();
                retVal = stringBuilder.toString();
            } else {
                inputStream = argHttpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    //sb.append(line + "\n");
                    stringBuilder.append(line);
                }
                inputStream.close();
                //argHttpURLConnection.disconnect();
                LogWriter.Log("PRINT_ERROR_STRING: " + stringBuilder.toString());
            }
        } catch (IOException e) {
            //e.printStackTrace();
            LogWriter.Log("PRINT_ERROR_IOException:- " + e.getMessage().toString());
        }
        return retVal;
    }
}
