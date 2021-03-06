package pl.adamchodera.randomuser.database;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;
import pl.adamchodera.randomuser.database.model.User;

public final class DatabaseHelper {

    private DatabaseHelper() {
        throw new AssertionError("No instances.");
    }

    public static void saveUsers(final ArrayList<User> users) {
        final Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        realm.copyToRealm(users);

        realm.commitTransaction();

        realm.close();
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

        final boolean containUsers = !realm.isEmpty();

        realm.close();

        return containUsers;
    }


    public static User getUserByEmail(final String userEmail) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmQuery<User> query = realm.where(User.class);
        query.contains("email", userEmail);

        return query.findFirst();
    }

    public static RealmResults<User> getAllUsers() {
        final Realm realm = Realm.getDefaultInstance();
        final RealmQuery<User> query = realm.where(User.class);

        return query.findAll();
    }

    public static void closeDatabase() {
        if (!Realm.getDefaultInstance().isClosed()) {
            Realm.getDefaultInstance().close();
        }
    }
}
