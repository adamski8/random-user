package pl.adamchodera.randomuser.database;

import java.util.List;

import io.realm.Realm;
import pl.adamchodera.randomuser.models.User;

public class DatabaseHelper {

    public static void saveUsers(final List<User> users) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        realm.copyToRealm(users);

        realm.commitTransaction();
    }

    public static boolean containUsers() {
        Realm realm = Realm.getDefaultInstance();

        return !realm.isEmpty();
    }
}
