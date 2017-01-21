package pl.adamchodera.randomuser.models;

import java.util.List;

public class UsersList {

    private List<User> users = null;
    private Info info;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
