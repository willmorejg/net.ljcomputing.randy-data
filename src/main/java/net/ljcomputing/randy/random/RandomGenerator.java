package net.ljcomputing.randy.random;

import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;

/** A random number generator. */
public enum RandomGenerator {
  /** The instance. */
  INSTANCE;

  private static final SecureRandom RANDOM = new SecureRandom();

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
   * Returns a random alphanumeric string value no longer than the given length.
   *
   * @param maxLength the max length of the random string
   * @return the random string value
   */
  public static final String getRandomString(final Integer maxLength) {
    return RandomStringUtils.random(getRandomInt(maxLength), 0, 0, true, true, null, RANDOM);
  }
}
