package pl.adamchodera.randomuser.database.mapper;

import java.util.ArrayList;
import java.util.List;

import pl.adamchodera.randomuser.database.model.User;
import pl.adamchodera.randomuser.network.pojo.RemoteUser;

public final class UsersMapper {

    private UsersMapper() {
        throw new AssertionError("No instances.");
    }

    public static ArrayList<User> fromRemoteUsers(final List<RemoteUser> remoteUsers) {
        final ArrayList<User> users = new ArrayList<>();

        for (RemoteUser remoteUser : remoteUsers) {
            users.add(new User(remoteUser));
        }

        return users;
    }
}
