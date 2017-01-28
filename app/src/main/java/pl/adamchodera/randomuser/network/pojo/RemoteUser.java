package pl.adamchodera.randomuser.network.pojo;

public class RemoteUser {

    private String email;
    private Name name;
    private Location location;
    private String registered;
    private String phone;
    private String cell;
    private Picture picture;

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRegistered() {
        return registered;
    }

    public Location getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public Picture getPicture() {
        return picture;
    }
}
