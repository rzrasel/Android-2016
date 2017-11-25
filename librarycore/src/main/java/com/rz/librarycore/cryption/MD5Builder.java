package com.rz.librarycore.cryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Rz Rasel 2017-11-21.
 */

public class MD5Builder {
    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(s.getBytes());
            byte messageByte[] = messageDigest.digest();

            // Create Hex String
            StringBuffer bufHexString = new StringBuffer();
            for (int i = 0; i < messageByte.length; i++) {
                String hexString = Integer.toHexString(0xFF & messageByte[i]);
                while (hexString.length() < 2)
                    hexString = "0" + hexString;
                bufHexString.append(hexString);
            }
            return bufHexString.toString();

        } catch (NoSuchAlgorithmException e) {
            //Logger.logStackTrace(TAG, e);
        }
        return "";
    }

    /*public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }*/
}
