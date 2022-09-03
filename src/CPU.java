import java.util.Arrays;
import java.util.Random;

public class CPU extends Duelist{
    Random random = new Random();
    public CPU(Deck deck) {
        super(deck);
    }

    private int[] selectExchangeCards = new int[Hand.HAND_AMOUNT];

    @Override
    protected int[] selectExchangeCards() {
        Arrays.fill(selectExchangeCards, -1);
        for (int i = 0; i < Hand.HAND_AMOUNT; i++) {
            if (selectExchangeCards[i] == -1) {
                for (int j = i + 1; j < Hand.HAND_AMOUNT; j++) {
                    if (hand.cards[i] == hand.cards[j]) {
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

    public void displayExchangeCards(){
        var displayExchangeCardsForCPU = "";
        for (int i = 0; i < Hand.HAND_AMOUNT; i++) {
            if (selectExchangeCards[i] == 1) {
                displayExchangeCardsForCPU = displayExchangeCardsForCPU + (i + 1);
            }
        }
        if (displayExchangeCardsForCPU.length() == 0) {
            displayExchangeCardsForCPU = "0";
        }
        System.out.println(displayExchangeCardsForCPU);
    }
}
