package pl.adamchodera.randomuser.activities;

import android.os.Bundle;

import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.fragments.UsersListFragment;
import pl.adamchodera.randomuser.network.pojo.User;

public class MainActivity extends BaseActivity implements UsersListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(User item) {

    }
}
