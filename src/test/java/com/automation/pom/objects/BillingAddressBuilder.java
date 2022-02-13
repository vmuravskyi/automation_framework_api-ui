package com.automation.pom.objects;

public class BillingAddressBuilder {

    private String firstName;
    private String lastName;
    private String addressLineOne;
    private String city;
    private String postcode;
    private String email;

    private BillingAddress dto;


    public BillingAddressBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BillingAddressBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BillingAddressBuilder withAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public BillingAddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public BillingAddressBuilder withPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public BillingAddressBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public BillingAddress build() {
        dto
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddressLineOne(addressLineOne)
                .setCity(city)
                .setPostcode(postcode)
                .setEmail(email);
        return dto;
    }

    public BillingAddress getBillingAddressDto() {
        if (dto != null) {
            return dto;
        } else {
            throw new NullPointerException("Object Billing Address has not been built");
        }
    }

}
