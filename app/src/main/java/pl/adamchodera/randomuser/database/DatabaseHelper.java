package pl.adamchodera.randomuser.database;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;
import pl.adamchodera.randomuser.network.pojo.User;

public class DatabaseHelper {

    public static void saveUsers(final List<User> users) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        realm.copyToRealm(users);

        realm.commitTransaction();
    }

    public static boolean containUsers() {
        Realm realm;
        try {
            realm = Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException e) {
            // TODO in future handle more carefully
            Realm.deleteRealm(new RealmConfiguration.Builder().build());
            realm = Realm.getDefaultInstance();
        }

        return !realm.isEmpty();
    }
}
