package pl.adamchodera.randomuser.feature.userslist;

import android.content.Intent;
import android.os.Bundle;

import io.realm.Realm;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.common.Commons;
import pl.adamchodera.randomuser.common.activity.BaseActivity;
import pl.adamchodera.randomuser.database.model.User;
import pl.adamchodera.randomuser.feature.details.UserDetailsActivity;
import pl.adamchodera.randomuser.feature.userslist.fragment.UsersListFragment;

public class UsersListActivity extends BaseActivity implements UsersListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
    }

    @Override
    public void onListFragmentInteraction(final User user) {
        displayDetailsView(user);
    }

    private void displayDetailsView(final User user) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(Commons.IntentKeys.USER_EMAIL, user.getEmail());
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!Realm.getDefaultInstance().isClosed()) {
            Realm.getDefaultInstance().close();
        }
    }
}
