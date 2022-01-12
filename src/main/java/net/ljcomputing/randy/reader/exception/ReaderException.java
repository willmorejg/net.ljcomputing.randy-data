package net.ljcomputing.randy.reader.exception;

/** Reader exception. */
public class ReaderException extends Exception {
  /** The serialVersionUID. */
  private static final long serialVersionUID = -2222035403290868679L;

  /** Constructor. */
  public ReaderException() {
    super();
  }

  /**
   * Constructor.
   *
   * @param message message
   */
  public ReaderException(final String message) {
    super(message);
  }

  /**
   * Constructor.
   *
   * @param message message
   * @param throwable throwable
   */
  public ReaderException(final String message, final Throwable throwable) {
    super(message, throwable);
  }
}
