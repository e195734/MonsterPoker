import java.util.Scanner;

public class Player extends Duelist{
    Scanner scanner;
    public Player(Deck deck, Scanner scanner) {
        super(deck);
        this.scanner = scanner;
    }

    @Override
    protected int[] selectExchangeCards() {
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
