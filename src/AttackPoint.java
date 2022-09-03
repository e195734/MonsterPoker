/**
 * Ratio を内部に含むクラス
 * どっちが使い勝手良いか試してみるためわざと変な設計にしています。
 */
public class AttackPoint {
    private final double value;
    final Ratio ratio;
    public AttackPoint(double value, Ratio ratio){
        this.value = value;
        this.ratio = ratio;
    }

    public AttackPoint add(final AttackPoint other){
        return new AttackPoint(this.value + other.value, ratio);
    }

    public double get(){
        return value* ratio.value;
    }
}
