package pl.adamchodera.randomuser.feature.details;

import android.os.Bundle;

import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.common.Commons;
import pl.adamchodera.randomuser.common.activity.BaseActivity;
import pl.adamchodera.randomuser.feature.details.fragment.UserDetailsFragment;

public class UserDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        if (savedInstanceState == null) {
            setupFragment();
        }
    }

    private void setupFragment() {
        final String userEmail = getIntent().getExtras().getString(Commons.IntentKeys.USER_EMAIL);
        final UserDetailsFragment fragment = UserDetailsFragment.newInstance(userEmail);
        getSupportFragmentManager().beginTransaction().add(R.id.id_activity_user_details_root_view, fragment).commit();
    }
}
