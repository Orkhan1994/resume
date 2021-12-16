package bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String profileDescription;
    private Date birthDate;
    private Country birthPlace;
    private Country nationality;

    private List<UserSkill> userSkills = new ArrayList<>();


    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String surname, String email, String phone, Country birthplace, Country nationality, Object o) {
    }

    public User(int id, String name, String surname, String email, String phone, String address, String profileDescription, Date birthDate, Country birthPlace, Country nationality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.profileDescription = profileDescription;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(List<UserSkill> userSkills) {
        this.userSkills = userSkills;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                ", birthDate=" + birthDate +
                ", birthPlace=" + birthPlace +
                ", nationality=" + nationality +
                '}';
    }
}
