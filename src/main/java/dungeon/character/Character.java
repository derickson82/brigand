/**
 * 
 */
package dungeon.character;

import java.util.EnumMap;
import java.util.Map;

import dungeon.character.race.Race;
import dungeon.util.Dice;
import dungeon.util.Preconditions;

/**
 * @author Dan
 */
public class Character {
  private String name;
  private Race race;
  private String characterClass;
  private Map<Ability, Score> abilityScores = new EnumMap<Ability, Score>(Ability.class);
  
  private int currentHitPoints = 10;
  private int maxHitPoints = 10;

  Character(String name, Race race, String characterClass, Map<Ability, Integer> abilityScores) {
    Preconditions.checkNotNull(name, "name");
    Preconditions.checkNotNull(race, "race");
    Preconditions.checkNotNull(characterClass, "characterClass");
    Preconditions.checkNotNull(abilityScores, "abilityScores");
    
    this.name = name;
    this.race = race;
    this.characterClass = characterClass;
    for (Ability ability : Ability.values()) {
      Integer score = abilityScores.get(ability);
      if (score == null) {
        throw new IllegalArgumentException("Must provide a score for every ability. Missing: " + ability);
      }
      this.abilityScores.put(ability, new Score(score));
    }
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Race getRace() {
    return race;
  }
  
  public String getCharacterClass() {
    return characterClass;
  }
  
  public Score getStrength() {
    return abilityScores.get(Ability.STR);
  }
  
  public Score getDexterity() {
    return abilityScores.get(Ability.DEX);
  }
  
  public Score getConstitution() {
    return abilityScores.get(Ability.CON);
  }
  
  public Score getIntelligence() {
    return abilityScores.get(Ability.INT);
  }
  
  public Score getWisdom() {
    return abilityScores.get(Ability.WIS);
  }
  
  public Score getCharisma() {
    return abilityScores.get(Ability.CHA);
  }
  
  
  public int rollInitiative() {
    return Dice.d20.roll();
  }
  
  public int rollAttack() {
    return Dice.d20.roll();
  }
  
  public int rollDefense() {
    return Dice.d20.roll();
  }
  
  public int rollDamage() {
    return Dice.d6.roll();
  }
  
  public void acceptDamage(int damage) {
    currentHitPoints -= damage;
  }
  
  public boolean isDead() {
    return currentHitPoints <= 0;
  }

//  public SavingThrow getFortitude() {
//    return null;
//  }
//  
//  public SavingThrow getWill() {
//    return null;
//  }
//  
//  public SavingThrow getReflex() {
//    return null;
//  }
}
