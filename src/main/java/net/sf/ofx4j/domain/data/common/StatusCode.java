package net.sf.ofx4j.domain.data.common;

/**
 * @author Ryan Heaton
 */
public interface StatusCode {

  int getCode();

  String getMessage();

  Status.Severity getDefaultSeverity();
}
