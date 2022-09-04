import java.util.ArrayList;
import java.util.Random;

public class Deck {
  final String monsters[] = { "スライム", "サハギン", "ドラゴン", "デュラハン", "シーサーペント" };// それぞれが0~4のIDのモンスターに相当する
  final int monstersAttackPoint[] = { 10, 20, 30, 25, 30 }; //各モンスターのAP
  final int monstersDefencePoint[] = { 40, 20, 25, 15, 20 }; //各モンスターのDP
  Random random = new Random();

  public Card draw(){
    int id = random.nextInt(5);
    return new Card(id,monsters[id],new AttackPoint(monstersAttackPoint[id]),new DefencePoint(monstersDefencePoint[id]));
  }
}
