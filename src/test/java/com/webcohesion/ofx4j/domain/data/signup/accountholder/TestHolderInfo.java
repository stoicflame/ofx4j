package com.webcohesion.ofx4j.domain.data.signup.accountholder;

import com.webcohesion.ofx4j.domain.data.signup.AccountHolder;
import com.webcohesion.ofx4j.domain.data.signup.AccountHolderInfo;
import com.webcohesion.ofx4j.domain.data.signup.AccountProfile;
import com.webcohesion.ofx4j.domain.data.signup.HolderType;
import com.webcohesion.ofx4j.io.AggregateMarshaller;
import com.webcohesion.ofx4j.io.v2.OFXV2Writer;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.StringWriter;

public class TestHolderInfo extends TestCase {

    public void testAccountHolderMarshalling() throws IOException {
        AccountProfile accountProfile = new AccountProfile();
        AccountHolderInfo accountHolderInfo = new AccountHolderInfo();
        AccountHolder primary = new AccountHolder();
        primary.setFirstName("Test");
        primary.setMiddleName("ing");
        primary.setLastName("User");
        primary.setAddressLine1("123 My Street");
        primary.setAddressLine2("Apt 321");
        primary.setCity("New York");
        primary.setPostalCode("10003");
        primary.setState("NY");
        primary.setCountry("USA");
        primary.setDayPhone("123-123-1234");
        primary.setEveningPhone("321-123-1234");
        primary.setHolderType(HolderType.INDIVIDUAL);
        primary.setEmail("testing@test.com");
        accountHolderInfo.setPrimaryAccountHolder(primary);
        accountProfile.setAccountHolderInfo(accountHolderInfo);

        StringWriter sw = new StringWriter();
        AggregateMarshaller marshaller = new AggregateMarshaller();

        marshaller.marshal(accountProfile, new OFXV2Writer(sw));


        String output = sw.toString();

        assertTrue(output.contains(primary.getFirstName()));
        assertTrue(output.contains(primary.getMiddleName()));
        assertTrue(output.contains(primary.getLastName()));
        assertTrue(output.contains(primary.getAddressLine1()));
        assertTrue(output.contains(primary.getAddressLine2()));
        assertTrue(output.contains(primary.getCity()));
        assertTrue(output.contains(primary.getPostalCode()));
        assertTrue(output.contains(primary.getCountry()));
        assertTrue(output.contains(primary.getState()));
        assertTrue(output.contains(primary.getDayPhone()));
        assertTrue(output.contains(primary.getEveningPhone()));
        assertTrue(output.contains(primary.getEmail()));
    }
}
