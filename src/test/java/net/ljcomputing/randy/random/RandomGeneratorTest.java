package net.ljcomputing.randy.random;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Test class for random generation. */
public class RandomGeneratorTest {

  /** Test random int generation. */
  @Test
  void testInt() {
    final int max = 64;
    final int generated1 = RandomGenerator.getRandomInt(max);
    Assert.assertTrue(generated1 <= max);
    final int generated2 = RandomGenerator.getRandomInt(max);
    Assert.assertTrue(generated2 <= max);
  }

  /** Test long generation. */
  @Test
  void testLong() {
    final long max = 16777216L;
    final long generated1 = RandomGenerator.getRandomLong(max);
    Assert.assertTrue(generated1 <= max);
    final long generated2 = RandomGenerator.getRandomLong(max);
    Assert.assertTrue(generated2 <= max);
  }

  /** Test double generation. */
  @Test
  void testDouble() {
    final double max = Double.valueOf(1000000);
    final double generated1 = RandomGenerator.getRandomDouble(max);
    Assert.assertTrue(generated1 <= max);
    final double generated2 = RandomGenerator.getRandomDouble(max);
    Assert.assertTrue(generated2 <= max);
  }

  /** Test boolean generation. */
  @Test
  void testBoolean() {
    Assert.assertTrue(RandomGenerator.getRandomBoolean() != null);
  }

  /** Test String generation. */
  @Test
  void testString() {
    final String generated1 = RandomGenerator.getRandomString(12);
    Assert.assertTrue(generated1 != null);
    final String generated2 = RandomGenerator.getRandomString(12);
    Assert.assertTrue(generated2 != null);
    Assert.assertNotEquals(generated1, generated2);
  }
}
