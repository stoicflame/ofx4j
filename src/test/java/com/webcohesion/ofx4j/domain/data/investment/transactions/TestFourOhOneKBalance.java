package com.webcohesion.ofx4j.domain.data.investment.transactions;

import com.webcohesion.ofx4j.domain.data.common.BalanceRecord;
import com.webcohesion.ofx4j.domain.data.common.Currency;
import com.webcohesion.ofx4j.domain.data.investment.statements.BalanceList;
import com.webcohesion.ofx4j.domain.data.investment.statements.FourOhOneKBalance;
import com.webcohesion.ofx4j.io.AggregateMarshaller;
import com.webcohesion.ofx4j.io.RequiredAttributeException;
import com.webcohesion.ofx4j.io.v2.OFXV2Writer;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

public class TestFourOhOneKBalance {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenAllFields_thenMarshallsCorrectly() throws IOException {
        FourOhOneKBalance balance = new FourOhOneKBalance();

        balance.setCashBalance(BigDecimal.valueOf(100.00));
        balance.setMatchValue(BigDecimal.valueOf(1.00));
        balance.setOtherNonVestValue(BigDecimal.valueOf(2.00));
        balance.setOtherVestValue(BigDecimal.valueOf(3.00));
        balance.setPreTaxValue(BigDecimal.valueOf(4.00));
        balance.setPostTaxValue(BigDecimal.valueOf(5.00));
        balance.setProfitSharingValue(BigDecimal.valueOf(6.00));
        balance.setRolloverValue(BigDecimal.valueOf(7.00));
        balance.setTotalValue(BigDecimal.valueOf(128.00));

        BalanceList balanceList = new BalanceList();
        BalanceRecord balanceRecord = new BalanceRecord();
        balanceRecord.setCurrency(new Currency());
        balanceRecord.setDescription("401K balance");
        balanceRecord.setName("My 401K Account");
        balanceRecord.setTimestamp(new Date());
        balanceRecord.setType(BalanceRecord.Type.DOLLAR);
        balanceRecord.setValue("128");

        balanceList.setBalanceRecords(Collections.singletonList(balanceRecord));
        balance.setBalanceList(balanceList);

        StringWriter sw = new StringWriter();
        AggregateMarshaller marshaller = new AggregateMarshaller();

        marshaller.marshal(balance, new OFXV2Writer(sw));


        String output = sw.toString();
        Assert.assertTrue(output.contains("INV401KBAL"));
        Assert.assertTrue(output.contains("CASHBAL"));
        Assert.assertTrue(output.contains("PRETAX"));
        Assert.assertTrue(output.contains("AFTERTAX"));
        Assert.assertTrue(output.contains("MATCH"));
        Assert.assertTrue(output.contains("ROLLOVER"));
        Assert.assertTrue(output.contains("OTHERVEST"));
        Assert.assertTrue(output.contains("TOTAL"));
        Assert.assertTrue(output.contains("BALLIST"));
    }

    @Test
    public void givenMissingTotalValue_thenFailsMarshalling() throws IOException {
        FourOhOneKBalance balance = new FourOhOneKBalance();

        balance.setCashBalance(BigDecimal.valueOf(100.00));

        StringWriter sw = new StringWriter();
        AggregateMarshaller marshaller = new AggregateMarshaller();

        expectedException.expect(RequiredAttributeException.class);
        expectedException.expectMessage("Required element 'TOTAL' (property 'totalValue' of aggregate com.webcohesion.ofx4j.domain.data.investment.statements.FourOhOneKBalance) is null or empty.");
        marshaller.marshal(balance, new OFXV2Writer(sw));
    }
}
