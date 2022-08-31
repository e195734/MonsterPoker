import java.util.Random;

public class CPU extends Duelist{
    Random random = new Random();
    public CPU(Deck deck) {
        super(deck);
    }

    @Override
    protected int[] selectExchangeCards() {
        var selectExchangeCards = new int[5];
        for (int i = 0; i < selectExchangeCards.length; i++) {
           selectExchangeCards[i] = -1;
        }
        for (int i = 0; i < hand.length; i++) {
            if (selectExchangeCards[i] == -1) {
                for (int j = i + 1; j < hand.length; j++) {
                    if (hand[i] == hand[j]) {
                        selectExchangeCards[i] = 0;
                        selectExchangeCards[j] = 0;
                    }
                }
                if (selectExchangeCards[i] != 0) {
                    selectExchangeCards[i] = random.nextInt(2);// 交換するかどうかをランダムに最終決定する
                    // selectExchangeCards[i] = 1;
                }
            }
        }
        return selectExchangeCards;
    }
}
