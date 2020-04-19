package com.webcohesion.ofx4j.domain.data.signup;

import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.Element;

@Aggregate
public class AccountHolder {
    public String firstName;
    public String middleName;
    public String lastName;
    public String addressLine1;
    public String addressLine2;
    public String addressLine3;
    public String city;
    public String state;
    public String postalCode;
    public String country;
    public String dayPhone;
    public String eveningPhone;
    public String email;
    public HolderType holderType;

    @Element(name = "FIRSTNAME", required = true, order = 10)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Element(name = "MIDDLENAME", order = 20)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Element(name = "LASTNAME", required = true, order = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Element(name = "ADDR1", required = true, order = 40)
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Element(name = "ADDR2", order = 50)
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Element(name = "ADDR3", order = 60)
    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @Element(name = "CITY", required = true, order = 70)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Element(name = "STATE", required = true, order = 80)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Element(name = "POSTALCODE",required = true, order = 90)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Element(name = "COUNTRY", order = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Element(name = "DAYPHONE", order = 110)
    public String getDayPhone() {
        return dayPhone;
    }

    public void setDayPhone(String dayPhone) {
        this.dayPhone = dayPhone;
    }

    @Element(name = "EVEPHONE", order = 120)
    public String getEveningPhone() {
        return eveningPhone;
    }

    public void setEveningPhone(String eveningPhone) {
        this.eveningPhone = eveningPhone;
    }

    @Element(name = "EMAIL", order = 130)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Element(name = "HOLDERTYPE", order = 140)
    public HolderType getHolderType() {
        return holderType;
    }

    public void setHolderType(HolderType holderType) {
        this.holderType = holderType;
    }
}
