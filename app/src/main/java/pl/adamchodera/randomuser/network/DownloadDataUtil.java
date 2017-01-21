package pl.adamchodera.randomuser.network;

import pl.adamchodera.randomuser.models.UsersList;
import pl.adamchodera.randomuser.utils.Commons;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadDataUtil {

    public static void getRandomUsers(final Callback<UsersList> callback) {
        getRetrofitService().getRandomUsers().enqueue(callback);
    }

    private static RestClientService getRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Commons.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RestClientService.class);
    }
}
