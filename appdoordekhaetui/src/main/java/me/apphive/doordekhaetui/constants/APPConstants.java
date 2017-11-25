package me.apphive.doordekhaetui.constants;

/**
 * Created by Rz Rasel on 2017-08-22.
 */

public class APPConstants {
    //|――――|―――――――――――――――――――――――――――――――|
    public interface READONLY {
        //|――――|―――――――――――――――――――――――――――――――|
        public interface RECEIVER_INTENT_FILTER {
            static final String ADVERT = "com.rz.receive.action.RECEIVE_MESSAGE_ADVERT";
            static final String URL_RESPONSE_HOST_INDEXING = "com.rz.receive.action.urlresponse.RECEIVE_MESSAGE_ADAPT_HOST_URL_INDEXING";
            static final String URL_RESPONSE_CHANNEL_INDEXING = "com.rz.receive.action.urlresponse.RECEIVE_MESSAGE_ADAPT_CHANNEL_URL_INDEXING";
            static final String READ_START_URL = "com.rz.receive.action.RECEIVE_MESSAGE_ADAPT_HOST_URL";
        }
        //|――――|―――――――――――――――――――――――――――――――|
    }

    //|――――|―――――――――――――――――――――――――――――――|
    public static class BROADCAST {
        public static boolean IS_RECEIVED_RESPONSE_HOST_INDEXING = false;
        public static boolean IS_RECEIVED_RESPONSE_CHANNEL_INDEXING = false;
    }

    //|――――|―――――――――――――――――――――――――――――――|
    public static class URL_READ_COMPLETE
    {
        public static boolean IS_RESPONSE_READ_HOST_INDEXING = false;
    }
    //|――――|―――――――――――――――――――――――――――――――|
    public static class DEFAULT {
        //
        public static final String KEY = "user_session";
    }
    //|――――|―――――――――――――――――――――――――――――――|
}