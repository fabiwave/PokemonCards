package Types;

/**
 * @author Fabiwave
 */

public class Fighting extends Type {

    public Fighting() {
        this.name = "Fighting";
    }

    @Override
    public int calcDamage(Type type, int damage) {
        return type.calcDamageFighting(damage);
    }

    @Override
    public int calcDamagePlant(int damage) {
        return super.weak(damage);
    }

    @Override
    public int calcDamageFire(int damage) {
        return damage;
    }

    @Override
    public int calcDamageWater(int damage) {
        return damage;
    }

    @Override
    public int calcDamageLightning(int damage) {
        return damage;
    }

    @Override
    public int calcDamageFighting(int damage) {
        return damage;
    }

    @Override
    public int calcDamagePsychic(int damage) {
        return super.weak(damage);
    }
}