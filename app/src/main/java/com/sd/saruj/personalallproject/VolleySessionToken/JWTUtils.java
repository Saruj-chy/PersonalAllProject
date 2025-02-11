package com.sd.saruj.personalallproject.VolleySessionToken;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

public class JWTUtils {

    String json ;

    public static void decoded(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            Log.d("JWT_DECODED", "header: " +  getJson(split[1])  );


        } catch (UnsupportedEncodingException e) {
            //Error
        }
    }

    public static String getJsonObject(String JWTEncoded) {
        try {
            String[] split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            Log.d("JWT_DECODED", "header: " +  getJson(split[1])  );

            return getJson(split[1]) ;

        } catch (UnsupportedEncodingException e) {
            //Error
        }
        return null;
    }


    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        Log.d("JWT_DECODED", "decodedBytes: " + decodedBytes );



        String decodeData = new String(decodedBytes, "UTF-8") ;

        return decodeData ;
    }



}
