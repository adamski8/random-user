package pl.adamchodera.randomuser.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.utils.Commons;
import pl.adamchodera.randomuser.utils.NetworkUtil;
import pl.adamchodera.randomuser.views.CustomRingProgressBar;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.id_activity_splash_progress_bar)
    CustomRingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        setupProgressBarListener();


        if (checkIfAppsDataIsCached()) {
            progressBar.smoothlyFillProgressBar(
                    Commons.PROGRESS_BAR_ANIMATION_MIN_DURATION_IN_MILLIS,
                    Commons.PROGRESS_BAR_ANIMATION_UPDATE_INTERVAL_IN_MILLIS);
        } else {
            if (NetworkUtil.isInternetConnectionAvailable(this)) {
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
        // TODO implement
    }

    private boolean checkIfAppsDataIsCached() {
        // TODO implement
        return true;
    }

    private void setupProgressBarListener() {
        progressBar.setOnProgressListener(this::gotToMainActivity);
    }

    private void gotToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}