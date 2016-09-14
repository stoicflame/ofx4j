package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.domain.data.RequestEnvelope;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.domain.data.banking.BankStatementResponse;
import net.sf.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import net.sf.ofx4j.domain.data.banking.BankingResponseMessageSet;
import net.sf.ofx4j.domain.data.common.Currency;
import net.sf.ofx4j.domain.data.common.Transaction;
import net.sf.ofx4j.domain.data.common.TransactionList;
import net.sf.ofx4j.server.OFXServer;

import java.util.*;

/**
 * @author Ryan Heaton
 */
public class MyOfxServer implements OFXServer {

  public ResponseEnvelope getResponse(RequestEnvelope request) {
    ResponseEnvelope env = new ResponseEnvelope();
    BankingResponseMessageSet bankingResponse = new BankingResponseMessageSet();
    BankStatementResponseTransaction responseTx = new BankStatementResponseTransaction();
    BankStatementResponse message = new BankStatementResponse();
    TransactionList txList = new TransactionList();
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    Transaction tx1 = new Transaction();
    Date may2011_10_20 = new Date();
    tx1.setDatePosted(may2011_10_20);
    tx1.setMemo("I bought milk");
    tx1.setAmount(12.34);
    Currency currency = new Currency();
    currency.setCode("");
    tx1.setCurrency(currency);
    transactions.add(tx1);

    Transaction tx2 = new Transaction();
    //set up tx2
    transactions.add(tx1);

    txList.setTransactions(transactions);
    message.setTransactionList(txList);
    responseTx.setMessage(message);
    bankingResponse.setStatementResponse(responseTx);
    env.setMessageSets(new TreeSet<ResponseMessageSet>(Arrays.asList(bankingResponse)));
    return env;
  }
}
