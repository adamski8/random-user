package pl.adamchodera.randomuser.network.pojo;

import io.realm.RealmObject;

public class Picture extends RealmObject {

    private String large;
    private String medium;

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }
}
