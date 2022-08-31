public abstract class Duelist {
    private static final int HAND_AMOUNT = 5;
    HitPoint hitPoint;
    Card[] hand = new Card[HAND_AMOUNT];
    double attackPointRatio = 1;
    double defencePointRatio = 1;
    double attackPoint = 0;
    double defencePoint = 0;
    int[] pokerHand = new int[HAND_AMOUNT];
    Deck deck;

    public Duelist(Deck deck){
        hitPoint = new HitPoint(1000);
        initDraw();
        this.deck = deck;
    }

    private void initDraw(){
        for (int i = 0; i < hand.length; i++) {
            this.hand[i] = deck.draw();
        }
    }

    private void exchangeCards(int[] changeIndex) {
        for (int index : changeIndex) {
            hand[index - 1] = deck.draw();
        }
    }

    public void redraw(){
        var selectCardID = selectExchangeCards();
        exchangeCards(selectCardID);
    }

    protected abstract int[] selectExchangeCards();
}
