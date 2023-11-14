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

  private String m_Encoding = "ISO-8859-1";
  private String m_Currency = "EUR";
  private Locale m_Locale = new Locale("nl", "NL");

  private int m_Indent = 0;
  private int m_Tab = 2; // Tab is equivalent of 2 spaces.
  private boolean m_WriteAttributesOnNewLine = true;

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
    return m_Encoding;
  }

  /**
   * Get currency to be used in transactions.
   * 
   * @return Currency as String.
   */
  public String getCurrency() {
    return m_Currency;
  }

  /**
   * Get indentation as String of spaces.
   * 
   * @return String of spaces, depends on indentation.
   */
  public String getIndentSpaces() {
    String spacing = "";
    if (m_Indent > 0) {
      spacing = new String(new char[m_Indent * m_Tab]).replace('\0', ' ');
    }
    return spacing;
  }

  /**
   * 
   * @return
   */
  public Locale getLocale() {
    return m_Locale;
  }

  /**
   * For creating OFX files: Attributes may start on newline.
   * 
   * @return True for starting on newline or False if not.
   */
  public boolean getWriteAttributesOnNewLine() {
    return m_WriteAttributesOnNewLine;
  }

  /**
   * Set Encoding.
   * 
   * @param a_encoding Encoding Charset.
   */
  public void setEncoding(Charset a_encoding) {
    m_Encoding = a_encoding.toString();
  }

  /**
   * Set Currency
   * 
   * @param a_Currency Currency as Enum.
   */
  public void setCurrency(CurrencyEnum a_Currency) {
    m_Currency = a_Currency.toString();
  }

  /**
   * Set Locale
   * 
   * @param a_Locale
   */
  public void setLocale(Locale a_Locale) {
    this.m_Locale = a_Locale;
  }

  /**
   * Set Attributes on newline.
   * 
   * @param a_WriteAttributesOnNewLine True or False
   */
  public void setWriteAttributesOnNewLine(boolean a_WriteAttributesOnNewLine) {
    m_WriteAttributesOnNewLine = a_WriteAttributesOnNewLine;
  }

  /**
   * Set number of spaces per indentation "unit".
   * 
   * @param a_Tab Number of spaces.
   */
  public void setTab(int a_Tab) {
    m_Tab = a_Tab;
  }

  /**
   * Increment indentation level.
   */
  public void incrIndent() {
    m_Indent++;
  }

  /**
   * Decrease indentation level.
   */
  public void decrIndent() {
    m_Indent--;
  }

}
