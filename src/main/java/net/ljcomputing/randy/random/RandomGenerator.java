package net.ljcomputing.randy.random;

import java.security.SecureRandom;

/** A random number generator. */
public enum RandomGenerator {
  /** The instance. */
  INSTANCE;

  /** The random generator. */
  private static final SecureRandom RANDOM = new SecureRandom();

  /** A characters set. */
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  /** All numbers set. */
  private static final String NUMBERS = "0123456789";

  /** All alphanumerics. */
  private static final String ALPHA = NUMBERS + CHARACTERS + NUMBERS;

  /**
   * Returns a random number between 0 and the given max.
   *
   * @param max the upper limit
   * @return a random int
   */
  public static final Integer getRandomInt(final Integer max) {
    return RANDOM.ints(0, max).findFirst().getAsInt();
  }

  /**
   * Returns a random number between 0 and the given max.
   *
   * @param max the upper limit
   * @return a random long
   */
  public static final Long getRandomLong(final Long max) {
    return RANDOM.longs(0, max).findFirst().getAsLong();
  }

  /**
   * Returns a random number between 0 and the given max.
   *
   * @param max the upper limit
   * @return a random double
   */
  public static final Double getRandomDouble(final Double max) {
    return RANDOM.doubles(0, max).findFirst().getAsDouble();
  }

  /**
   * Returns a random boolean.
   *
   * @return a random boolean
   */
  public static final Boolean getRandomBoolean() {
    return RANDOM.nextBoolean();
  }

  /**
   * Returns a random String value based on the pool of values provided and no longer than the given
   * length.
   *
   * @param maxLength max length of String to return
   * @param poolOfValues pool of values to use in String generation
   * @return a random String
   */
  public static final String getRandomCharacters(
      final Integer maxLength, final String poolOfValues) {
    final StringBuilder builder = new StringBuilder();
    final Integer length = getRandomInt(maxLength) + 1;

    for (int i = 0; i < length; i++) {
      final Integer position = getRandomInt(poolOfValues.length());
      builder.append(poolOfValues.charAt(position));
    }

    return builder.toString();
  }

  /**
   * Returns a random String of just characters no longer than the given length.
   *
   * @param maxLength the max length of the random string
   * @return the random string value
   */
  public static final String getRandomAlphaCharacters(final Integer maxLength) {
    return getRandomCharacters(maxLength, CHARACTERS);
  }

  /**
   * Returns a random String of alphanumeric characters no longer than the given length.
   *
   * @param maxLength the max length of the random string
   * @return the random string value
   */
  public static final String getRandomAlphaNumericCharacters(final Integer maxLength) {
    return getRandomCharacters(maxLength, ALPHA);
  }
}
