package dungeon.combat;

import dungeon.character.Character;

public class Combat {
  
  private Character combatant1;
  private Character combatant2;
  
  public void setCombatant1(Character combatant1) {
    this.combatant1 = combatant1;
  }
  
  public void setCombatant2(Character combatant2) {
    this.combatant2 = combatant2;
  }
  
  public void begin() {
    
    // roll for initiative
    
    boolean c1GoesFirst = combatant1.rollInitiative() > combatant2.rollInitiative();
    
    Character attacker = c1GoesFirst ? combatant1 : combatant2;
    Character defender = c1GoesFirst ? combatant2 : combatant1;
    
//    System.out.println("Player 1 goes first: " + c1GoesFirst);
    
    // combatants take turns attacking each other
    // go until the someone dies
    while (!attacker.isDead() && !defender.isDead()) {
      int attackRoll = attacker.rollAttack();
      int defenseRoll = attacker.rollDefense();
      
      // scores a hit!
      if (attackRoll > defenseRoll) {
//        System.out.println(attacker.name + " scores a hit!");
        int damage = attacker.rollDamage();
        
        defender.acceptDamage(damage);
//        System.out.println(defender.name + " takes " + damage + " damage. " + defender.hp + " hp left");
      } else {
//        System.out.println(attacker + " misses!");
      }

      // swap and continue hitting each other
      Character temp = attacker;
      attacker = defender;
      defender = temp;
    }
  }
  
  @Override
  public String toString() {
    return "Combatant1: " + combatant1 + ", Combatant2: " + combatant2;
  }
}
