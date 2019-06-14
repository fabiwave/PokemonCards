package Cards;

import Cards.Phases.AbstractPhase;
import Cards.TrainerCards.PKMObject;
import Other.AbstractAbility;
import Other.Attack;
import Players.Player;
import Players.Trainer;
import Types.Type;
import Visitors.IVisitable;
import Visitors.IVisitor;

import java.util.ArrayList;
import java.util.HashMap;

public class Pokemon extends AbstractCard implements IPokemon, IVisitable {
    private String name;
    private int id;
    private int hp;
    private int max_hp;
    private ArrayList<AbstractAbility> abilities;
    private HashMap<String, Integer> energies;
    private Type type;
    private Trainer trainer;
    private int next_ability_index;
    private PKMObject pkmObject;
    private AbstractPhase phase;

    public Pokemon(String name, int id, int hp, ArrayList<AbstractAbility> abilities, Type type, Trainer trainer, AbstractPhase phase) {
        String[] energy_names = {"Fighting", "Fire", "Lightning", "Plant", "Psychic", "Water"};
        this.name = name;
        this.id = id;
        this.hp = hp;
        this.max_hp = hp;
        this.abilities = abilities;
        this.energies = new HashMap<>();
        this.type = type;
        this.trainer = trainer;
        this.next_ability_index = -1;
        this.pkmObject = null;
        for (String a_name :
                energy_names) {
            this.energies.put(a_name, 0);
        }
        this.phase = phase;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getHp() {
        return hp;
    }

    public int getMax_hp(){return max_hp;}

    public ArrayList<AbstractAbility> getAbilities() {
        return abilities;
    }

    public HashMap<String, Integer> getEnergies() {
        return energies;
    }

    public Type getType() {
        return type;
    }

    @Override
    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void setHp(int hp){
        int aux = this.hp;
        if(aux + hp > this.max_hp){
            this.hp = this.max_hp;
        }
        else{
            this.hp = hp;
        }
    }

    public PKMObject getPKMObject(){
        return this.pkmObject;
    }

    public void setPkmObject(PKMObject pkmObject) {
        this.pkmObject = pkmObject;
    }

    public AbstractPhase getPhase(){
        return this.phase;
    }

    public boolean isAlive() {
        return (this.hp > 0);
    }

    public boolean getAttacked(Pokemon attacker, AbstractAbility move) {
        Attack attack = (Attack) move;
        int effectiveDamage = this.type.calcDamage(attacker.type, attack.getDamage());
        this.hp -= effectiveDamage;

        if (this.hp <= 0) {
            this.hp = 0;
            return true;
        }

        return false;
    }

    @Override
    public void setNextAbility(int index) {
        if (index >= 0 && index < abilities.size()) {
            this.next_ability_index = index;
        }
    }

    @Override
    public void attackTrainer(Player adversary) {
        adversary.getActivePokemon().getAttacked(this, this.abilities.get(next_ability_index));
        adversary.checkActivePokemon();
    }

    @Override
    public void addFightingEnergy() {
        this.energies.replace(
                "Fighting",
                this.energies.get("Fighting") + 1
        );
    }

    @Override
    public void addFireEnergy() {
        this.energies.replace(
                "Fire",
                this.energies.get("Fire") + 1
        );
    }

    @Override
    public void addLightningEnergy() {
        this.energies.replace(
                "Lightning",
                this.energies.get("Lightning") + 1
        );
    }

    @Override
    public void addPlantEnergy() {
        this.energies.replace(
                "Plant",
                this.energies.get("Plant") + 1
        );
    }

    @Override
    public void addPsychicEnergy() {
        this.energies.replace(
                "Psychic",
                this.energies.get("Psychic") + 1
        );
    }

    @Override
    public void addWaterEnergy() {
        this.energies.replace(
                "Water",
                this.energies.get("Water") + 1
        );
    }

    public void play(ICard card) {
        this.trainer.addPokemonToTeam(this);
    }

    public void acceptVisitor(IVisitor visitor){
        visitor.visitPokemon(this);
    }

}