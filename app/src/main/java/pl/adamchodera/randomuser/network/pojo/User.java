package pl.adamchodera.randomuser.network.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private String email;

    private Name name;

    private Location location;

    private String registered;
    private String phone;
    private String cell;
    private Picture picture;


    public String getName() {
        return name.getTitle() + " " + name.getFirst() + " " + name.getLast();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }


    public String getPhotoUrl() {
        return picture.getLarge();
    }

    public String getRegistered() {
        return registered;
    }

    public String getRegistrationDateFormatted() {
        // TODO use formatter
        return registered;
    }
}
