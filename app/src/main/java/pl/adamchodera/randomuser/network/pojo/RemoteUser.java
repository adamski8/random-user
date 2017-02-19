package pl.adamchodera.randomuser.network.pojo;

import lombok.Getter;

public class RemoteUser {

    private @Getter String email;
    private @Getter Name name;
    private @Getter Location location;
    private @Getter String registered;
    private @Getter String phone;
    private @Getter String cell;
    private @Getter Picture picture;

}
