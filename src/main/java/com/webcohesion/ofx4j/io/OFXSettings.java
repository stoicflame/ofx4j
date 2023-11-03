package com.webcohesion.ofx4j.io;

public class OFXSettings {
  private static OFXSettings uniqueInstance;

  private String m_Encoding = "ISO-8859-1";
  private String m_Currency = "EUR";
  private int m_Ident = 0;
  private int m_tab = 2;
  private boolean m_WriteAttributesOnNewLine = true;

  private OFXSettings() {
    // Private constructor to prevent direct instantiation
  }

  public static OFXSettings getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new OFXSettings();
    }
    return uniqueInstance;
  }

  public String getEncoding() {
    return m_Encoding;
  }

  public String getCurrency() {
    return m_Currency;
  }

  public int getIdent() {
    return m_Ident;
  }

  public String getTabSpacing() {
    String spacing = "";
    if (m_Ident >= 0) {
      spacing = new String(new char[(m_Ident + 1) * m_tab]).replace('\0', ' ');
    }
    return spacing;
  }

  public String getIdentSpaces() {
    String spacing = "";
    if (m_Ident > 0) {
      spacing = new String(new char[m_Ident * m_tab]).replace('\0', ' ');
    }
    return spacing;
  }

  public boolean getWriteAttributesOnNewLine() {
    return m_WriteAttributesOnNewLine;
  }

  public void setEncoding(String a_encoding) {
    m_Encoding = a_encoding;
  }

  public void setCurrency(String a_Currency) {
    m_Currency = a_Currency;
  }

  public void setWriteAttributesOnNewLine(boolean a_WriteAttributesOnNewLine) {
    m_WriteAttributesOnNewLine = a_WriteAttributesOnNewLine;
  }

  public void incrIdent() {
    m_Ident++;
  }

  public void decrIdent() {
    m_Ident--;
  }

}
