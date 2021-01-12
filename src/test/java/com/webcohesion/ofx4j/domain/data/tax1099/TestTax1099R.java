package com.webcohesion.ofx4j.domain.data.tax1099;

import com.webcohesion.ofx4j.io.AggregateMarshaller;
import com.webcohesion.ofx4j.io.v2.OFXV2Writer;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

public class TestTax1099R extends TestCase {

    public void testMarshallMultipleDistributionCodes() throws IOException {
        Tax1099R tax1099R = new Tax1099R();

        //Required
        tax1099R.setSrvrtId("test");
        tax1099R.setTaxYear("2020");
        tax1099R.setGrossDist("10");
        tax1099R.setIraSepSimp("N");
        tax1099R.setPayerId("testing");

        PayerAddress payerAddress = new PayerAddress();
        payerAddress.setAddress1("testing");
        payerAddress.setPayerName1("testing");
        payerAddress.setCity("testing");
        payerAddress.setState("TEST");
        payerAddress.setPostalCode("12341");
        tax1099R.setPayerAddress(payerAddress);
        tax1099R.setRecId("1234567");
        tax1099R.setRecAcct("1234567");

        tax1099R.setDistCodes(Arrays.asList("A", "G"));

        StringWriter sw = new StringWriter();
        AggregateMarshaller marshaller = new AggregateMarshaller();

        marshaller.marshal(tax1099R, new OFXV2Writer(sw));

        String output = sw.toString();
        assertTrue(output.contains("<DISTCODE>A</DISTCODE>"));
        assertTrue(output.contains("<DISTCODE>G</DISTCODE>"));
    }
}
