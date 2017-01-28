package pl.adamchodera.randomuser.common;

public interface Commons {

    int PROGRESS_BAR_ANIMATION_MIN_DURATION_IN_MILLIS = 1200;

    int PROGRESS_BAR_ANIMATION_UPDATE_INTERVAL_IN_MILLIS = 30;

    String BASE_URL = "https://randomuser.me/api/";

    int NUMBER_OF_USERS_TO_FETCH = 25;

    interface IntentKeys {

        String USER_EMAIL = "key_USER_ID";
    }

}
