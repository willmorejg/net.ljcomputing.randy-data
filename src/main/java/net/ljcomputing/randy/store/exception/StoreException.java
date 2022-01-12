package net.ljcomputing.randy.store.exception;

/** Store exception. */
public class StoreException extends Exception {
  /** The serialVersionUID. */
  private static final long serialVersionUID = 308421709954082970L;

  /** Constructor. */
  public StoreException() {
    super();
  }

  /**
   * Constructor.
   *
   * @param message message
   */
  public StoreException(final String message) {
    super(message);
  }

  /**
   * Constructor.
   *
   * @param message message
   * @param throwable throwable
   */
  public StoreException(final String message, final Throwable throwable) {
    super(message, throwable);
  }
}
