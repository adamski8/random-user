package pl.adamchodera.randomuser.common;

public interface Commons {

    String BASE_URL = "https://randomuser.me/api/";

    int NUMBER_OF_USERS_TO_FETCH = 25;

    interface IntentKeys {

        String USER_EMAIL = "key_USER_ID";
    }

    interface Chars {

        char SPACE = ' ';
        String NEW_LINE = System.getProperty("line.separator");

    }
}
