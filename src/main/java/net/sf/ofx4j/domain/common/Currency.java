package net.sf.ofx4j.domain.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Locale;

/**
 * @author Ryan Heaton
 * @see "Section 5.2, OFX Spec"
 */
@Aggregate ( "CURRENCY" )
public class Currency {

  private String code = java.util.Currency.getInstance(Locale.US).getCurrencyCode();
  private Float exchangeRate;

  /**
   * The currency code.
   *
   * @return The currency code.
   * @see java.util.Currency#getCurrencyCode()
   */
  @Element ( value = "CURSYM", required = true, order = 0 )
  public String getCode() {
    return code;
  }

  /**
   * The currency code
   *
   * @param code The currency code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * The exchange rate.
   *
   * @return The exchange rate.
   */
  @Element ( value = "CURRATE", required = true, order = 10 )
  public Float getExchangeRate() {
    return exchangeRate;
  }

  /**
   * The exchange rate.
   *
   * @param exchangeRate The exchange rate.
   */
  public void setExchangeRate(Float exchangeRate) {
    this.exchangeRate = exchangeRate;
  }
}
