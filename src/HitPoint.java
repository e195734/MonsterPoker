public class HitPoint {
    private static final int MIN = 0;
    final int value;

    public HitPoint(int value) {
        if (value < MIN){
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public HitPoint damage(final int damageAmount){
        final int damaged = value - damageAmount;
        final int corrected = Math.max(damaged, MIN);
        return new HitPoint(corrected);
    }

    public boolean isZero(){
        return value == MIN;
    }
}
