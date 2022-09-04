public class Card {
  String name;
  int    id;
  AttackPoint AttackPoint;
  DefencePoint DefencePoint;

  public Card(int id,String name, AttackPoint AttackPoint, DefencePoint DefencePoint) {
    this.id           = id;
    this.name         = name;
    this.AttackPoint  = AttackPoint;
    this.DefencePoint = DefencePoint;
  }
}
