import java.util.Scanner;

/**
 * Main
 */
public class Main {

  /**
   * モンスターポーカー 1. モンスターカードをユーザとCPUがランダムに5枚引く モンスターカードは5種類存在し，役によって攻撃力が変わる
   * 2回まで指定したカードを交換できる
   *
   * 2. AP，DPを役に応じて計算し，バトル．DPを超えたAPの場合，差分はプレーヤーあるいはCPUへのダメージとなる 3.
   * プレーヤーあるいはCPUのHPが0になったら終わり．
   *
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);// 標準入力
    MonsterPoker mp = new MonsterPoker(scanner);
    while (true) {
      mp.drawPhase(scanner);
      mp.battlePhase();
      if (!mp.didEndGame()){
        Thread.sleep(2000);
        continue;
      }

      if (mp.winCPU() && mp.winPlayer()){
        System.out.println("引き分け！");
      }else if (mp.winCPU()){
        System.out.println("CPU Win!");
      }else if (mp.winPlayer()){
        System.out.println("Player Win!");
      }
      break;
    }
    scanner.close();
  }
}
