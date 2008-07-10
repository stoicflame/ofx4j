package net.sf.ofx4j.domain.data;

/**
 * The message set type, used to define message set order in the envelope.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.5.2, OFX spec"
 */
public enum MessageSetType {

  signon,

  signup,

  banking,

  creditcard,

  investment,

  interbank_transfer,

  wire_transfer,

  payments,

  email,

  investment_security,

  profile

}
