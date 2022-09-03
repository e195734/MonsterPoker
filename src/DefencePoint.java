public class DefencePoint {
    final private double value;
    public DefencePoint(double value){
        this.value = value;
    }

    public DefencePoint add(final DefencePoint other){
        return new DefencePoint(this.value );
    }

    public DefencePoint multiple(DefencePointRatio ratio){
        return new DefencePoint(this.value * ratio.value);
    }
}
