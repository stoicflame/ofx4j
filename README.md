Welcome to OFX4J!

This project provides an implementaion of [OFX](http://ofx.net/) for Java.

[![Build Status](https://travis-ci.org/stoicflame/ofx4j.svg?branch=master)](https://travis-ci.org/stoicflame/ofx4j)

# Coordinates

### Maven

```xml
<dependency>
  <groupId>com.webcohesion.ofx4j</groupId>
  <artifactId>ofx4j</artifactId>
  <version>1.33</version>
</dependency>
```

# OFX Client

### Lookup Your Financial Institution

  You have to first find the financial institution you want to interface with.  Use an implementation of
  `com.webcohesion.ofx4j.client.FinancialInstitutionDataStore` to list the available financial institution data.
  Alternatively, use `com.webcohesion.ofx4j.client.impl.BaseFinancialInstitutionData` to construct your own data if your
  FI data isn't available.

  The two default implementations of `FinancialInstitutionDataStore` are `com.webcohesion.ofx4j.client.impl.LocalResourceFIDataStore`
  and `com.webcohesion.ofx4j.client.impl.OFXHomeFIDataStore`. The former looks up the data store list from a local resource (default is the
  list at `/META-INF/ofx4j/institutions.xml` on the classpath; a default one is packaged with the jar). The latter screen-scrapes
  [this page](http://www.ofxhome.com/index.php/home/directory) at [OFX Home](http://www.ofxhome.com/), so it's subject to break
  depending on how often the format of that page changes.

  Once you have a reference to the `FinancialInstitutionData` you want, you can now use it to interface with your FI.

### Interfacing With Your Financial Institution

```java
FinancialInstitutionData data = ...;
FinancialInstitutionService service
  = new FinancialInstitutionServiceImpl();
FinancialInstitution fi = service.getFinancialInstitution(data);
fi.setLanguage(Locale.US.getISO3Language().toUpperCase());

// read the fi profile (note: not all institutions
// support this, and you normally don't need it.)
FinancialInstitutionProfile profile = fi.readProfile();

//get a reference to a specific bank account at your FI
BankAccountDetails bankAccountDetails
  = new BankAccountDetails();

//routing number to the bank.
bankAccountDetails.setRoutingNumber("11111111");
//bank account number.
bankAccountDetails.setAccountNumber("1234-5678");
//it's a checking account
bankAccountDetails.setAccountType(AccountType.CHECKING);

BankAccount bankAccount
  = fi.loadBankAccount(bankAccountDetails, "username", "password");

//read the statement (transaction details, etc.)
// for a given time period.
Date startDate = ...;
Date endDate = ...;
AccountStatement statement
  = bankAccount.readStatement(startDate, endDate);

// get a reference to a specific credit card
// account at your FI
CreditCardAccountDetails ccDetails
  = new CreditCardAccountDetails();
ccDetails.setAccountNumber("1234-567890-1111");
CreditCardAccount ccAccount
  = fi.loadCreditCardAccount(ccDetails, "username", "password");

// read the statement (transaction details, etc.)
// for a given time period.
Date startDate = ...;
Date endDate = ...;
AccountStatement statement
  = ccAccount.readStatement(startDate, endDate);
```

# OFX Server

You can use OFX4J to implement your own OFX server. To do so, you must implement the `com.webcohesion.ofx4j.server.OFXServer`
interface.  Once an adequate implementation of this interface is exists, you can use the `com.webcohesion.ofx4j.server.OFXServlet`
in your J2EE application, passing the name of the `OFXServer` implementation in the "ofx-server-class" init parameter.

```xml
<web-app>

  ...

  <servlet>
    <servlet-name>OFXServlet</servlet-name>
    <servlet-class>com.webcohesion.ofx4j.server.OFXServlet</servlet-class>
    <init-param>
      <param-name>ofx-server-class</param-name>
      <param-value>fqn.of.the.OFXServerImpl</param-value>
    </init-param>
    <!-- uncomment to use OFX v2
    <init-param>
      <param-name>ofx-version</param-name>
      <param-value>2</param-value>
    </init-param>
    -->
  </servlet>

  ...

  <servlet-mapping>
    <servlet-name>OFXServlet</servlet-name>
    <url-pattern>/ofx</url-pattern>
  </servlet-mapping>

  ...

</web-app>
```

# OFX I/O

### Reading OFX data

OFX4J boasts a very fast, event-based OFX reading mechanism.  If you've got a file or other stream resource, you can read it using an instance of
`com.webcohesion.ofx4j.io.OFXReader`. We suggest the `com.webcohesion.ofx4j.io.nanoxml.NanoXMLOFXReader`, which uses utilities provided by the
[NanoXML](http://nanoxml.cyberelf.be/) project. This reader will detect the version of OFX being used (version 1 or 2) and parse it accordingly.

Or you want to unmarshal the OFX directly to a Java object, use the `com.webcohesion.ofx4j.io.AggregateUnmarshaller`.

### Writing OFX data

You can also write OFX data using an instance of `com.webcohesion.ofx4j.io.OFXWriter`. `com.webcohesion.ofx4j.io.v1.OFXV1Writer` is used to write OFX version 1 and
`com.webcohesion.ofx4j.io.v2.OFXV2Writer` is used to write OFX version 2. If you want to marshal from Java objects, use the
`com.webcohesion.ofx4j.io.AggregateMarshaller`

# Support

Try the [issue tracker](https://github.com/stoicflame/ofx4j/issues).
