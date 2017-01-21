package pl.adamchodera.randomuser.network;

import pl.adamchodera.randomuser.models.UsersList;
import pl.adamchodera.randomuser.utils.Commons;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestClientService {

    @GET("?results=" + Commons.NUMBER_OF_USERS_TO_FETCH)
    Call<UsersList> getRandomUsers();

}