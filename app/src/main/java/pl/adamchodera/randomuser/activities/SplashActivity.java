package pl.adamchodera.randomuser.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.database.DatabaseHelper;
import pl.adamchodera.randomuser.models.User;
import pl.adamchodera.randomuser.models.UsersList;
import pl.adamchodera.randomuser.network.DownloadDataUtil;
import pl.adamchodera.randomuser.utils.Commons;
import pl.adamchodera.randomuser.utils.NetworkAvailabilityUtil;
import pl.adamchodera.randomuser.views.CustomRingProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.id_activity_splash_progress_bar)
    CustomRingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        setupProgressBarListener();

        if (DatabaseHelper.containUsers()) {
            progressBar.smoothlyFillProgressBar(
                    Commons.PROGRESS_BAR_ANIMATION_MIN_DURATION_IN_MILLIS,
                    Commons.PROGRESS_BAR_ANIMATION_UPDATE_INTERVAL_IN_MILLIS);
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
            }
        });
    }

    private void setupProgressBarListener() {
        progressBar.setOnProgressListener(this::gotToMainActivity);
    }

    private void gotToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}