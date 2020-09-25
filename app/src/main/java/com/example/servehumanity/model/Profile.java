package com.example.servehumanity.model;

public class Profile {
  private  String firstName, lastName, phone, address, dateOfBirth,  lastDonation, gender,bloodGroup, image;

  public Profile(String firstName, String lastName, String phone, String address, String image, String dateOfBirth, String gender, String bloodGroup, String lastDonation) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.address = address;
    this.image = image;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.bloodGroup = bloodGroup;
    this.lastDonation = lastDonation;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBloodGroup() {
    return bloodGroup;
  }

  public void setBloodGroup(String bloodGroup) {
    this.bloodGroup = bloodGroup;
  }

  public String getLastDonation() {
    return lastDonation;
  }

  public void setLastDonation(String lastDonation) {
    this.lastDonation = lastDonation;
  }
}