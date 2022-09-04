public class HitPoint {
    private static final int MIN = 0;
    final int value;

    public HitPoint(int value) {
        if (value < MIN){
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    private HitPoint damage(final int damageAmount){
        final int damaged = value - damageAmount;
        final int corrected = Math.max(damaged, MIN);
        return new HitPoint(corrected);
    }

    public HitPoint damage(final AttackPoint enemy, final DefencePoint myDefence){
        final double damage = Math.abs(enemy.value- myDefence.value);
        return damage((int) damage);
    }

    public boolean isZero(){
        return value == MIN;
    }
}
