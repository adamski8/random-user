package pl.adamchodera.randomuser.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import pl.adamchodera.randomuser.network.pojo.RemoteUser;

public class User extends RealmObject {

    @PrimaryKey
    private String email;

    private String title;
    private String firstName;
    private String lastName;

    private String street;
    private String city;
    private String state;
    private String postcode;

    private String registeredData;
    private String phoneNumber;
    private String cellNumber;

    private String mediumPictureUrl;
    private String largePictureUrl;

    public User() {
    }

    public User(final RemoteUser remoteUser) {
        this.email = remoteUser.getEmail();

        this.title = remoteUser.getName().getTitle();
        this.firstName = remoteUser.getName().getFirst();
        this.lastName = remoteUser.getName().getLast();

        this.street = remoteUser.getLocation().getStreet();
        this.city = remoteUser.getLocation().getCity();
        this.state = remoteUser.getLocation().getState();
        this.postcode = remoteUser.getLocation().getPostcode();

        this.registeredData = remoteUser.getRegistered();
        this.phoneNumber = remoteUser.getPhone();
        this.cellNumber = remoteUser.getCell();

        this.mediumPictureUrl = remoteUser.getPicture().getMedium();
        this.largePictureUrl = remoteUser.getPicture().getLarge();
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return getTitle() + " " + getFirstName() + " " + getLastName();
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRegisteredData() {
        return registeredData;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getLargePictureUrl() {
        return largePictureUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMediumPictureUrl() {
        return mediumPictureUrl;
    }
}
