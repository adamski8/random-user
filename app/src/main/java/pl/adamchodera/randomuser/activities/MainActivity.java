package pl.adamchodera.randomuser.activities;

import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.network.pojo.User;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<User> query = realm.where(User.class);
        RealmResults<User> loadedUsers = query.findAll();
    }
}
