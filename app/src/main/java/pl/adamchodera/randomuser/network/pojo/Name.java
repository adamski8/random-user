package pl.adamchodera.randomuser.network.pojo;

import io.realm.RealmObject;

public class Name extends RealmObject {

    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }


    public String getLast() {
        return last;
    }

}
