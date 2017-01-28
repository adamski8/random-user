package pl.adamchodera.randomuser.network;

import pl.adamchodera.randomuser.network.pojo.UsersList;
import pl.adamchodera.randomuser.common.Commons;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface RestClientService {

    @Streaming
    @GET("?results=" + Commons.NUMBER_OF_USERS_TO_FETCH)
    Call<UsersList> getRandomUsers();

}