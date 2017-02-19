package pl.adamchodera.randomuser.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import pl.adamchodera.randomuser.common.Commons;
import pl.adamchodera.randomuser.network.pojo.RemoteUser;

public @Data class User extends RealmObject {

    @PrimaryKey
    private String email;

    private String title;
    private String firstName;
    private String lastName;

    private String street;
    private String city;
    private String state;
    private String postcode;

    private String registeredDate;
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

        this.registeredDate = remoteUser.getRegistered();
        this.phoneNumber = remoteUser.getPhone();
        this.cellNumber = remoteUser.getCell();

        this.mediumPictureUrl = remoteUser.getPicture().getMedium();
        this.largePictureUrl = remoteUser.getPicture().getLarge();
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return getTitle() + Commons.Chars.SPACE + getFirstName() + Commons.Chars.SPACE + getLastName();
    }

    public String getRegisteredDateFormatted() {
        return registeredDate.substring(0, registeredDate.length() - 3);
    }

    public String getAllPhoneNumbersFormatted() {
        return phoneNumber + Commons.Chars.NEW_LINE + cellNumber;
    }

    public String getLocationFormatted() {
        return street + Commons.Chars.NEW_LINE + postcode + Commons.Chars.SPACE + city + Commons.Chars.NEW_LINE +
                state;
    }
}
