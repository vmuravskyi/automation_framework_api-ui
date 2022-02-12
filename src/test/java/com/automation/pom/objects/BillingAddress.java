package com.automation.pom.objects;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BillingAddress {

    private String firstName;
    private String lastName;
    private String addressLineOne;
    private String city;
    private String postcode;
    private String email;

    public BillingAddress() {
        // empty default constructor
    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public BillingAddress setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public BillingAddress setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BillingAddress that = (BillingAddress) o;

        return new EqualsBuilder()
            .append(firstName, that.firstName)
            .append(lastName, that.lastName)
            .append(addressLineOne, that.addressLineOne)
            .append(city, that.city)
            .append(postcode, that.postcode)
            .append(email, that.email).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(firstName)
            .append(lastName)
            .append(addressLineOne)
            .append(city)
            .append(postcode)
            .append(email)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("firstName", firstName)
            .append("lastName", lastName)
            .append("addressLineOne", addressLineOne)
            .append("city", city)
            .append("postcode", postcode)
            .append("email", email)
            .toString();
    }

}

