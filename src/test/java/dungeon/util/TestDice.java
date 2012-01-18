package dungeon.util;


import org.testng.Assert;
import org.testng.annotations.Test;

import dungeon.util.Dice;

public class TestDice {

  @Test
  public void testBuildingDice() {
    int sides = 6;
    Dice dice = Dice.create(sides); // creates a single dice

    Assert.assertEquals(dice.getSides(), sides);
  }

  @Test
  public void testRollingDice() {
    int sides = 6;
    Dice dice = Dice.create(sides);

    int[] results = new int[sides];
    for (int i = 0; i < 1000; i++) {
      int result = dice.roll();

      Assert.assertTrue(result > 0 && result <= 6);
      results[result - 1]++;
    }

    for (int count : results) {
      Assert.assertTrue(count > 0);
    }
  }

  @Test
  public void testRollingMultipleDice() {
    int totalDice = 3;
    int sides = 6;
    Dice dice = Dice.create(totalDice, sides);

    // 11 possible results when rolling dice twice
    int[] results = new int[totalDice * sides - (totalDice - 1)];
    for (int i = 0; i < 10000; i++) {
      int result = dice.roll();

      Assert.assertTrue(result > 2 && result <= 18, result + "");
      results[result - 3]++;
    }

    for (int i = 0; i < results.length; i++) {
      // TODO these assertions might randomly fail, which is bad for a test.
      // But how else can we assert that we are getting the expected results?
      Assert.assertTrue(results[i] > 0, "Result: " + (i + 3) + ", " + results[i]);
    }
  }
  
  @Test
  public void testRollKeepHigh() {
    int totalDice = 4;
    int sides = 6;
    Dice dice = Dice.create(totalDice, sides);
    
    // roll keep high should have a range between 3 and 18
    int[] results = new int[(totalDice - 1) * sides - (totalDice - 1 - 1)];
    
    for (int i = 0; i < 10000; i++) {
      int result = dice.rollKeepHigh(3);

      Assert.assertTrue(result > 2 && result <= 18, result + "");
      results[result - 3]++;
    }

    for (int i = 0; i < results.length; i++) {
      Assert.assertTrue(results[i] > 0, "Result: " + (i + 3) + ", " + results[i]);
    }
  }
  
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testMoreKeepers() {
    Dice dice = Dice.create(4, 6);
    dice.rollKeepHigh(5);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNegativeSides() {
    Dice.create(-1);
  }
}
