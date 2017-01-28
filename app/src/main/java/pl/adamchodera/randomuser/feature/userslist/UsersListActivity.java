package pl.adamchodera.randomuser.feature.userslist;

import android.content.Intent;
import android.os.Bundle;

import pl.adamchodera.randomuser.common.Commons;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.common.activity.BaseActivity;
import pl.adamchodera.randomuser.feature.details.UserDetailsActivity;
import pl.adamchodera.randomuser.feature.userslist.fragment.UsersListFragment;
import pl.adamchodera.randomuser.network.pojo.User;

public class UsersListActivity extends BaseActivity implements UsersListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(User user) {
        displayDetailsView(user);
    }

    private void displayDetailsView(User user) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(Commons.IntentKeys.USER_EMAIL, user.getEmail());
        startActivity(intent);
    }
}
