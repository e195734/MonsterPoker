import java.util.Random;
import java.util.Scanner;

/**
 * MonsterPoker
 */
public class MonsterPoker {

  Random card = new Random();
  Deck deck = new Deck();
  private static final int HAND_AMOUNT = 5;
  Card playerHand[] = new Card[HAND_AMOUNT]; // 0~4までの数字（モンスターID）が入る
  Card cpuHand[] = new Card[HAND_AMOUNT];

  int cpuExchangeCards[] = new int[5];// それぞれ0,1でどのカードを交換するかを保持する．{0,1,1,0,1}の場合は2,3,5枚目のカードを交換することを表す
  String displayExchangeCardsForCPU = new String();// 交換するカードを1~5の数字の組み合わせで保持する．上の例の場合，"235"となる．
  int playerYaku[] = new int[5];// playerのモンスターカードがそれぞれ何枚ずつあるかを保存する配列．{2,3,0,0,0}の場合，ID0が2枚,ID1が3枚あることを示す．
  int cpuYaku[] = new int[5];// playerのモンスターカードがそれぞれ何枚ずつあるかを保存する配列．{2,3,0,0,0}の場合，ID0が2枚,ID1が3枚あることを示す．
  // 役判定用フラグ
  // 役判定
  // 5が1つある：ファイブ->five = true
  // 4が1つある：フォー->four = true
  // 3が1つあり，かつ，2が1つある：フルハウス->three = true and pair = 1
  // 2が2つある：ツーペア->pair = 2
  // 3が1つある：スリー->three = true;
  // 2が1つある：ペア->pair = 1
  // 1が5つある：スペシャルファイブ->one=5
  boolean five = false;
  boolean four = false;
  boolean three = false;
  int pair = 0; // pair数を保持する
  int one = 0;// 1枚だけのカードの枚数

  private final Scanner scanner;
  private final Player player;
  private final CPU cpu;

  public MonsterPoker(Scanner scanner) {
    this.scanner = scanner;
    player = new Player(deck, scanner);
    cpu = new CPU(deck);
  }

  /**
   * 5枚のモンスターカードをプレイヤー/CPUが順に引く
   *
   * @throws InterruptedException
   */
  public void drawPhase(Scanner scanner) throws InterruptedException {
    // 初期Draw
    System.out.println("PlayerのDraw！");
    player.hand.initDraw();

    // カードの表示
    System.out.print("[Player]");
    player.hand.display();
    System.out.println();

    // カードの交換
    player.selectExchangeCards();
    player.hand.display();
    player.selectExchangeCards();
    player.hand.display();

    System.out.println("CPUのDraw！");
    cpu.selectExchangeCards();
    // カードの表示
    System.out.print("[CPU]");
    cpu.hand.display();
    System.out.println();

    // 交換するカードの決定
    System.out.println("CPUが交換するカードを考えています・・・・・・");
    Thread.sleep(2000);
    // cpuHandを走査して，重複するカード以外のカードをランダムに交換する
    // 0,1,0,2,3 といったcpuHandの場合，2枚目，4枚目，5枚目のカードをそれぞれ交換するかどうか決定し，例えば24といった形で決定する
    // 何番目のカードを交換するかを0,1で持つ配列の初期化
    // 例えばcpuExchangeCards[]が{0,1,1,0,0}の場合は2,3枚目を交換の候補にする
    cpu.redraw();

    // 交換するカード番号の表示
    cpu.displayExchangeCards();
    // カードの交換
    cpu.redraw();
    cpu.displayExchangeCards();

    // 交換するカードの決定
    System.out.println("CPUが交換するカードを考えています・・・・・・");
    Thread.sleep(2000);
    // cpuHandを走査して，重複するカード以外のカードをランダムに交換する
    // 0,1,0,2,3 といったcpuHandの場合，2枚目，4枚目，5枚目のカードをそれぞれ交換するかどうか決定し，例えば24といった形で決定する
    // 何番目のカードを交換するかを0,1で持つ配列の初期化
    // 例えばcpuExchangeCards[]が{0,1,1,0,0}の場合は2,3枚目を交換の候補にする
    for (int i = 0; i < this.cpuExchangeCards.length; i++) {
      this.cpuExchangeCards[i] = -1;
    }
    for (int i = 0; i < this.cpuHand.length; i++) {
      if (this.cpuExchangeCards[i] == -1) {
        for (int j = i + 1; j < this.cpuHand.length; j++) {
          if (this.cpuHand[i] == this.cpuHand[j]) {
            this.cpuExchangeCards[i] = 0;
            this.cpuExchangeCards[j] = 0;
          }
        }
        if (this.cpuExchangeCards[i] != 0) {
          this.cpuExchangeCards[i] = this.card.nextInt(2);// 交換するかどうかをランダムに最終決定する
          // this.cpuExchangeCards[i] = 1;
        }
      }
    }

    // 交換するカード番号の表示
    this.displayExchangeCardsForCPU = "";
    for (int i = 0; i < cpuExchangeCards.length; i++) {
      if (this.cpuExchangeCards[i] == 1) {
        this.displayExchangeCardsForCPU = this.displayExchangeCardsForCPU + (i + 1);
      }
    }
    if (this.displayExchangeCardsForCPU.length() == 0) {
      this.displayExchangeCardsForCPU = "0";
    }
    System.out.println(this.displayExchangeCardsForCPU);

    // カードの交換
    if (displayExchangeCardsForCPU.charAt(0) != '0') {
      for (int i = 0; i < displayExchangeCardsForCPU.length(); i++) {
        this.cpuHand[Character.getNumericValue(displayExchangeCardsForCPU.charAt(i)) - 1] = deck.draw();
      }
      // カードの表示
      System.out.print("[CPU]");
      for (int i = 0; i < cpuHand.length; i++) {
        String cardName = cpuHand[i].name;
        System.out.printf("%s ", cardName);
      }
      System.out.println();
    }
  }

  public void battlePhase() throws InterruptedException {
    // Playerの役の判定
    // 役判定用配列の初期化
    for (int i = 0; i < playerYaku.length; i++) {
      this.playerYaku[i] = 0;
    }
    // モンスターカードが何が何枚あるかをplayerYaku配列に登録
    for (int i = 0; i < playerHand.length; i++) {
      this.playerYaku[this.playerHand[i].id]++;
    }
    // 役判定
    // 5が1つある：ファイブ
    // 4が1つある：フォー
    // 3が1つあり，かつ，2が1つある：フルハウス
    // 2が2つある：ツーペア
    // 3が1つある：スリー
    // 2が1つある：ペア
    // 1が5つある：スペシャルファイブ
    // 初期化
    five = false;
    four = false;
    three = false;
    pair = 0; // pair数を保持する
    one = 0;// 1枚だけのカードの枚数
    // 手札ごとのplayerYaku配列の作成
    for (int i = 0; i < playerYaku.length; i++) {
      if (playerYaku[i] == 1) {
        one++;
      } else if (playerYaku[i] == 2) {
        pair++;
      } else if (playerYaku[i] == 3) {
        three = true;
      } else if (playerYaku[i] == 4) {
        four = true;
      } else if (playerYaku[i] == 5) {
        five = true;
      }
    }

    // 役の判定
    System.out.println("Playerの役は・・");
    player.defencePointRatio = new DefencePointRatio(1);
    player.attackPointRatio = new AttackPointRatio(1);
    if (one == 5) {
      System.out.println("スペシャルファイブ！AP/DPは両方10倍！");
      player.attackPointRatio = new AttackPointRatio(10);
      player.defencePointRatio = new DefencePointRatio(10);
    } else if (five == true) {
      System.out.println("ファイブ！AP/DPは両方5倍！");
      player.attackPointRatio = new AttackPointRatio(5);
      player.defencePointRatio = new DefencePointRatio(5);
    } else if (four == true) {
      System.out.println("フォー！AP/DPは両方4倍！");
      player.attackPointRatio = new AttackPointRatio(3);
      player.defencePointRatio = new DefencePointRatio(4);
    } else if (three == true && pair == 1) {
      System.out.println("フルハウス！AP/DPは両方3倍");
      player.attackPointRatio = new AttackPointRatio(3);
      player.defencePointRatio = new DefencePointRatio(3);
    } else if (three == true) {
      System.out.println("スリーカード！AP/DPはそれぞれ3倍と2倍");
      player.attackPointRatio = new AttackPointRatio(3);
      player.defencePointRatio = new DefencePointRatio(2);
    } else if (pair == 2) {
      System.out.println("ツーペア！AP/DPは両方2倍");
      player.attackPointRatio = new AttackPointRatio(2);
      player.defencePointRatio = new DefencePointRatio(2);
    } else if (pair == 1) {
      System.out.println("ワンペア！AP/DPは両方1/2倍");
      player.attackPointRatio = new AttackPointRatio(0.5);
      player.defencePointRatio = new DefencePointRatio(0.5);
    }
    Thread.sleep(1000);

    // APとDPの計算
    for (int i = 0; i < playerYaku.length; i++) {
      if (playerYaku[i] >= 1) {
        player.attackPoint = new AttackPoint(player.attackPoint.value + playerHand[i].AttackPoint.value * playerYaku[i]);
        player.defencePoint = new DefencePoint(player.defencePoint.value + playerHand[i].DefencePoint.value*playerYaku[i]);
      }
    }
    player.attackPoint = player.attackPoint.multiple(player.attackPointRatio);
    player.defencePoint = player.defencePoint.multiple(player.defencePointRatio);

    // CPUの役の判定
    // 役判定用配列の初期化
    for (int i = 0; i < cpuYaku.length; i++) {
      this.cpuYaku[i] = 0;
    }
    // モンスターカードが何が何枚あるかをcpuYaku配列に登録
    for (int i = 0; i < cpuHand.length; i++) {
      this.cpuYaku[this.cpuHand[i].id]++;
    }
    // 役判定
    // 5が1つある：ファイブ
    // 4が1つある：フォー
    // 3が1つあり，かつ，2が1つある：フルハウス
    // 2が2つある：ツーペア
    // 3が1つある：スリー
    // 2が1つある：ペア
    // 1が5つある：スペシャルファイブ
    // 初期化
    five = false;
    four = false;
    three = false;
    pair = 0; // pair数を保持する
    one = 0;// 1枚だけのカードの枚数
    // 手札ごとのcpuYaku配列の作成
    for (int i = 0; i < cpuYaku.length; i++) {
      if (cpuYaku[i] == 1) {
        one++;
      } else if (cpuYaku[i] == 2) {
        pair++;
      } else if (cpuYaku[i] == 3) {
        three = true;
      } else if (cpuYaku[i] == 4) {
        four = true;
      } else if (cpuYaku[i] == 5) {
        five = true;
      }
    }

    // 役の判定
    System.out.println("CPUの役は・・");
    cpu.attackPointRatio = new AttackPointRatio(1);// 初期化
    cpu.defencePointRatio = new DefencePointRatio(1);
    if (one == 5) {
      System.out.println("スペシャルファイブ！AP/DPは両方10倍！");
      cpu.attackPointRatio = new AttackPointRatio(10);
      cpu.defencePointRatio = new DefencePointRatio(10);
    } else if (five == true) {
      System.out.println("ファイブ！AP/DPは両方5倍！");
      cpu.attackPointRatio = new AttackPointRatio(5);
      cpu.defencePointRatio = new DefencePointRatio(5);
    } else if (four == true) {
      System.out.println("フォー！AP/DPは両方4倍！");
      cpu.attackPointRatio = new AttackPointRatio(3);
      cpu.defencePointRatio = new DefencePointRatio(3);
    } else if (three == true && pair == 1) {
      System.out.println("フルハウス！AP/DPは両方3倍");
      cpu.attackPointRatio = new AttackPointRatio(3);
      cpu.defencePointRatio = new DefencePointRatio(3);
    } else if (three == true) {
      System.out.println("スリーカード！AP/DPはそれぞれ3倍と2倍");
      cpu.attackPointRatio = new AttackPointRatio(3);
      cpu.defencePointRatio = new DefencePointRatio(2);
    } else if (pair == 2) {
      System.out.println("ツーペア！AP/DPは両方2倍");
      cpu.attackPointRatio = new AttackPointRatio(2);
      cpu.defencePointRatio = new DefencePointRatio(2);
    } else if (pair == 1) {
      System.out.println("ワンペア！AP/DPは両方1/2倍");
      cpu.attackPointRatio = new AttackPointRatio(0.5);
      cpu.defencePointRatio = new DefencePointRatio(0.5);
    }
    Thread.sleep(1000);

    // APとDPの計算
    for (int i = 0; i < cpuYaku.length; i++) {
      if (cpuYaku[i] >= 1) {
        cpu.attackPoint = cpu.attackPoint.add(new AttackPoint(cpuHand[i].AttackPoint.value * cpuYaku[i]));
        cpu.defencePoint = cpu.defencePoint.add(new DefencePoint(cpuHand[i].DefencePoint.value * cpuYaku[i]));
      }
    }
    cpu.attackPoint = cpu.attackPoint.multiple(cpu.attackPointRatio);
    cpu.defencePoint = cpu.defencePoint.multiple(cpu.defencePointRatio);

    // バトル
    System.out.println("バトル！！");
    // Playerの攻撃
    System.out.print("PlayerのDrawした");
    for (int i = 0; i < playerYaku.length; i++) {
      if (playerYaku[i] >= 1) {
        System.out.print(this.playerHand[i].name + " ");
        Thread.sleep(500);
      }
    }
    System.out.print("の攻撃！");
    Thread.sleep(1000);
    System.out.println("CPUのモンスターによるガード！");
    if (cpu.defencePoint.value >= player.attackPoint.value) {
      System.out.println("CPUはノーダメージ！");
    } else {
      double damage = player.attackPoint.value - cpu.defencePoint.value;
      System.out.printf("CPUは%.0fのダメージを受けた！\n", damage);
      cpu.hitPoint.damage(player.attackPoint, cpu.defencePoint);
    }

    // CPUの攻撃
    System.out.print("CPUのDrawした");
    for (int i = 0; i < cpuYaku.length; i++) {
      if (cpuYaku[i] >= 1) {
        System.out.print(this.cpuHand[i].name + " ");
        Thread.sleep(500);
      }
    }
    System.out.print("の攻撃！");
    Thread.sleep(1000);
    System.out.println("Playerのモンスターによるガード！");
    if (player.defencePoint.value >= cpu.attackPoint.value) {
      System.out.println("Playerはノーダメージ！");
    } else {
      double damage = cpu.attackPoint.value - player.defencePoint.value;
      System.out.printf("Playerは%.0fのダメージを受けた！\n", damage);
      player.hitPoint.damage(cpu.attackPoint, player.defencePoint);
    }

    System.out.println("PlayerのHPは" + player.hitPoint.value);
    System.out.println("CPUのHPは" + cpu.hitPoint.value);

  }

  public boolean winCPU() {
    return player.hitPoint.isZero();
  }

  public boolean winPlayer() {
    return cpu.hitPoint.isZero();
  }

  public boolean didEndGame() {
    return winCPU() || winPlayer();
  }
}
