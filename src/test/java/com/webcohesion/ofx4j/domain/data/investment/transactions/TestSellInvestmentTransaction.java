package com.webcohesion.ofx4j.domain.data.investment.transactions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import com.webcohesion.ofx4j.domain.data.seclist.SecurityId;
import com.webcohesion.ofx4j.io.AggregateMarshaller;
import com.webcohesion.ofx4j.io.v2.OFXV2Writer;

import junit.framework.TestCase;

public class TestSellInvestmentTransaction extends TestCase {

	public void testMarshallCurrencyInfo() throws IOException {
		SellInvestmentTransaction strans = new SellInvestmentTransaction();

		OriginalCurrency curr = new OriginalCurrency();
		curr.setCurrencyCode("USD");
		curr.setCurrencyRate(1.1);
		strans.setOriginalCurrencyInfo(curr);

		//TBC
		strans.setCommission(100.1);
		
		SecurityId id = new SecurityId();
		id.setUniqueId("AB12383948");
		id.setUniqueIdType("ISIN");

		strans.setSecurityId(id);
		
		strans.setUnits(100.);
		strans.setUnitPrice(100.1);
		strans.setTotal(9909.9);
		
		InvestmentTransaction inv = new InvestmentTransaction();
		inv.setTransactionId("1");
		inv.setTradeDate(new Date());
		inv.setSettlementDate(new Date());
		inv.setServerId("ABC123");
		
		strans.setInvestmentTransaction(inv);
		
		StringWriter sw = new StringWriter();
		AggregateMarshaller marshaller = new AggregateMarshaller();
		
		marshaller.marshal(strans, new OFXV2Writer(sw));


		String output = sw.toString();
		assertTrue(output.contains("USD"));
	}
}
