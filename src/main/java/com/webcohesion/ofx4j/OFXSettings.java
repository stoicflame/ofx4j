package com.webcohesion.ofx4j;

import java.nio.charset.Charset;
import java.util.Locale;

import com.webcohesion.ofx4j.generated.CurrencyEnum;

/**
 * OFXSettings some general parameter definitions. This is a singleton object.
 * 
 * @author RSH Kwee
 *
 */
public class OFXSettings {
  private static OFXSettings uniqueInstance;

  private String ENCODING = "ISO-8859-1";
  private String CURRENCY = "EUR";
  private Locale LOCALE = new Locale("en", "US");// new Locale("nl", "NL");

  private int INDENT = 0;
  private int TAB = 2; // Tab is equivalent of 2 spaces.
  private boolean WRITE_ATTRIBUTES_ON_NEWLINE = true;

  /**
   * Private constructor to prevent direct instantiation.
   */
  private OFXSettings() {
    // Do nothing.
  }

  /**
   * Get "access" to Singleton.
   * 
   * @return Instance
   */
  public static OFXSettings getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new OFXSettings();
    }
    return uniqueInstance;
  }

  /**
   * Get Encoding to be used for read and write OFX files.
   * 
   * @return Encoding as String.
   */
  public String getEncoding() {
    return ENCODING;
  }

  /**
   * Get currency to be used in transactions.
   * 
   * @return Currency as String.
   */
  public String getCurrency() {
    return CURRENCY;
  }

  /**
   * Get indentation as String of spaces.
   * 
   * @return String of spaces, depends on indentation.
   */
  public String getIndentSpaces() {
    String spacing = "";
    if (INDENT > 0) {
      spacing = new String(new char[INDENT * TAB]).replace('\0', ' ');
    }
    return spacing;
  }

  /**
   * 
   * @return
   */
  public Locale getLocale() {
    return LOCALE;
  }

  /**
   * For creating OFX files: Attributes may start on newline.
   * 
   * @return True for starting on newline or False if not.
   */
  public boolean getWriteAttributesOnNewLine() {
    return WRITE_ATTRIBUTES_ON_NEWLINE;
  }

  /**
   * Set Encoding.
   * 
   * @param a_encoding Encoding Charset.
   */
  public void setEncoding(Charset a_encoding) {
    ENCODING = a_encoding.toString();
  }

  /**
   * Set Currency
   * 
   * @param a_Currency Currency as Enum.
   */
  public void setCurrency(CurrencyEnum a_Currency) {
    CURRENCY = a_Currency.toString();
  }

  /**
   * Set Locale
   * 
   * @param a_Locale
   */
  public void setLocale(Locale a_Locale) {
    this.LOCALE = a_Locale;
  }

  /**
   * Set Attributes on newline.
   * 
   * @param a_WriteAttributesOnNewLine True or False
   */
  public void setWriteAttributesOnNewLine(boolean a_WriteAttributesOnNewLine) {
    WRITE_ATTRIBUTES_ON_NEWLINE = a_WriteAttributesOnNewLine;
  }

  /**
   * Set number of spaces per indentation "unit".
   * 
   * @param a_Tab Number of spaces.
   */
  public void setTab(int a_Tab) {
    TAB = a_Tab;
  }

  /**
   * Increment indentation level.
   */
  public void incrIndent() {
    INDENT++;
  }

  /**
   * Decrease indentation level.
   */
  public void decrIndent() {
    INDENT--;
  }

}
