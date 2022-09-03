import java.util.Scanner;

public class Player extends Duelist{
    Scanner scanner;
    public Player(Deck deck, Scanner scanner) {
        super(deck);
        this.scanner = scanner;
    }

    @Override
    protected int[] selectExchangeCards() {
        System.out.println("カードを交換する場合は1から5の数字（左から数えた位置を表す）を続けて入力してください．交換しない場合は0と入力してください");
        var selectExchangeCards = new int[5];
        String exchange = scanner.nextLine();
        if (exchange.charAt(0) != '0') {
            for (int i = 0; i < exchange.length(); i++) {
                selectExchangeCards[i] = Character.getNumericValue(exchange.charAt(i)) - 1;
            }
        }
        return selectExchangeCards;
    }
}
