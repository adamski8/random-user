package pl.adamchodera.randomuser.feature.userslist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.common.Commons;
import pl.adamchodera.randomuser.common.activity.BaseActivity;
import pl.adamchodera.randomuser.database.DatabaseHelper;
import pl.adamchodera.randomuser.feature.userdetails.UserDetailsActivity;
import pl.adamchodera.randomuser.feature.userslist.adapter.UserItemViewHolder;
import pl.adamchodera.randomuser.feature.userslist.fragment.UsersListFragment;

public class UsersListActivity extends BaseActivity implements UsersListFragment.OnListFragmentInteractionListener {

    @Override
    public void displayDetailsView(final UserItemViewHolder viewHolder) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(Commons.IntentKeys.USER_EMAIL, viewHolder.getUser().getEmail());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, getTransitionOptions(viewHolder));
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
    }

    @Override
    protected void onStop() {
        super.onStop();

        DatabaseHelper.closeDatabase();
    }

    @SuppressWarnings("unchecked")
    private Bundle getTransitionOptions(final UserItemViewHolder viewHolder) {
        final String transitionNameUserPhoto = getResources().getString(R.string.transition_name_user_photo);
        final String transitionNameUserEmail = getResources().getString(R.string.transition_name_user_email);
        final String transitionNameUserName = getResources().getString(R.string.transition_name_user_name);

        Pair<View, String> p1 = Pair.create(viewHolder.getPhotoView(), transitionNameUserPhoto);
        Pair<View, String> p2 = Pair.create(viewHolder.getEmailView(), transitionNameUserEmail);
        Pair<View, String> p3 = Pair.create(viewHolder.getNameView(), transitionNameUserName);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2, p3);

        return options.toBundle();
    }
}
