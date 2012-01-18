package dungeon.combat;

import org.testng.annotations.Test;

import dungeon.character.Character;
import dungeon.character.CharacterBuilder;

public class CombatTest {

  @Test
  public void simpleCombatTest() {
    int pcWins = 0;
    int mobWins = 0;
    for (int i = 0; i < 1000; i++) {
      Combat combat = new Combat();

      // player has an advantage with a better weapon
      Character pc = CharacterBuilder.pointPool(0).name("pc").dwarf().characterClass("Fighter").build();
      Character mob = CharacterBuilder.pointPool(0).name("mob").dwarf().characterClass("Wizard").build();

      combat.setCombatant1(pc);
      combat.setCombatant2(mob);

      combat.begin();

      if (pc.isDead()) {
        mobWins++;
      } else if (mob.isDead()) {
        pcWins++;
      }

    }

    System.out.println("pc won: " + pcWins + ", mob won: " + mobWins);

    // if (pc.isDead()) {
    // System.out.println(pc + " is dead!");
    // } else if (mob.isDead()){
    // System.out.println(mob + " is defeated!");
    // } else {
    // System.out.println("Combat ended, but no one died? Strange.");
    // }
  }

}
