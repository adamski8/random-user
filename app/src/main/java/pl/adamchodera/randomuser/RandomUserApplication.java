package pl.adamchodera.randomuser;

import android.app.Application;

import io.realm.Realm;

public class RandomUserApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
