package pl.adamchodera.randomuser.network.util;

import pl.adamchodera.randomuser.network.RestClientService;
import pl.adamchodera.randomuser.network.pojo.UsersList;
import pl.adamchodera.randomuser.common.Commons;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class DownloadDataUtil {

    private DownloadDataUtil() {
        throw new AssertionError("No instances.");
    }

    public static void getRandomUsers(final Callback<UsersList> callback) {
        getRetrofitService().getRandomUsers().enqueue(callback);
    }

    public static void cancelFetchingUsers() {
        getRetrofitService().getRandomUsers().cancel();
    }

    private static RestClientService getRetrofitService() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Commons.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RestClientService.class);
    }
}
