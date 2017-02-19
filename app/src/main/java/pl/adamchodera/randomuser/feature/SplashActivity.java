package pl.adamchodera.randomuser.feature;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.common.activity.BaseActivity;
import pl.adamchodera.randomuser.database.DatabaseHelper;
import pl.adamchodera.randomuser.database.mapper.UsersMapper;
import pl.adamchodera.randomuser.database.model.User;
import pl.adamchodera.randomuser.feature.userslist.UsersListActivity;
import pl.adamchodera.randomuser.network.pojo.RemoteUser;
import pl.adamchodera.randomuser.network.pojo.UsersList;
import pl.adamchodera.randomuser.network.util.DownloadDataUtil;
import pl.adamchodera.randomuser.network.util.NetworkAvailabilityUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

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

    @Override
    protected void onPause() {
        super.onPause();

        DownloadDataUtil.cancelFetchingUsers();
    }

    private void internetConnectionNotAvailable() {
        displayToastMessage(R.string.no_internet_connection_available);
    }

    private void fetchAndSaveLocallyDataFromServer() {
        DownloadDataUtil.getRandomUsers(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                parseAndSaveResponse(response);
                gotToMainActivity();
            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                t.printStackTrace();
                displayToastMessage(R.string.downloading_users_failed);
            }
        });
    }

    private void parseAndSaveResponse(final Response<UsersList> response) {
        final List<RemoteUser> remoteUsers = response.body().getResults();
        final ArrayList<User> users = UsersMapper.fromRemoteUsers(remoteUsers);

        DatabaseHelper.saveUsers(users);
    }

    private void gotToMainActivity() {
        startActivity(new Intent(this, UsersListActivity.class));
    }

    private void displayToastMessage(final int resId) {
        Toast.makeText(this, getTextByResId(resId), Toast.LENGTH_LONG).show();
    }

    private String getTextByResId(final int resId) {
        return getResources().getString(resId);
    }
}