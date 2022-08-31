public class Card {
  String name;
  int    id;
  int    AttackPoint;
  int    DefencePoint;

  public Card(int id,String name, int AttackPoint, int DefencePoint) {
    this.id           = id;
    this.name         = name;
    this.AttackPoint  = AttackPoint;
    this.DefencePoint = DefencePoint;
  }
}