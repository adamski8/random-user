package pl.adamchodera.randomuser.network.pojo;

import java.util.List;

public class UsersList {

    private List<User> results;

    public List<User> getUsers() {
        return results;
    }

    public void setUsers(List<User> users) {
        this.results = users;
    }

}