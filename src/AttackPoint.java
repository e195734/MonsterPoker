public class AttackPoint {
    final double value;
    public AttackPoint(double value){
        this.value = value;
    }

    public AttackPoint add(final AttackPoint other){
        return new AttackPoint(this.value + other.value );
    }

    public AttackPoint multiple(AttackPointRatio ratio){
        return new AttackPoint( this.value * ratio.value);
    }
    public AttackPoint multiple(AttackPoint other){
        return new AttackPoint( this.value * other.value);
    }
}
