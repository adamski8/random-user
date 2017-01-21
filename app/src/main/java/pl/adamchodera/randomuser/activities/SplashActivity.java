package pl.adamchodera.randomuser.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.database.DatabaseHelper;
import pl.adamchodera.randomuser.network.pojo.User;
import pl.adamchodera.randomuser.network.pojo.UsersList;
import pl.adamchodera.randomuser.network.DownloadDataUtil;
import pl.adamchodera.randomuser.network.NetworkAvailabilityUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (DatabaseHelper.containUsers()) {
            gotToMainActivity();
        } else {
            if (NetworkAvailabilityUtil.isInternetConnectionAvailable(this)) {
                fetchAndSaveLocallyDataFromServer();
            } else {
                internetConnectionNotAvailable();
            }
        }
    }

    private void internetConnectionNotAvailable() {
        // TODO implement
    }

    private void fetchAndSaveLocallyDataFromServer() {
        DownloadDataUtil.getRandomUsers(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                List<User> users = response.body().getUsers();
                DatabaseHelper.saveUsers(users);
                gotToMainActivity();
            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                t.printStackTrace();
                // TODO implement
            }
        });
    }

    private void gotToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}