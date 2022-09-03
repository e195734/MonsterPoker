public abstract class Duelist {
    HitPoint hitPoint;
    Hand hand;
    AttackPoint attackPoint = new AttackPoint(0);
    DefencePoint defencePoint = new DefencePoint(0);
    AttackPointRatio attackPointRatio = new AttackPointRatio(1);
    DefencePointRatio defencePointRatio = new DefencePointRatio(1);
    int[] pokerHand = new int[Hand.HAND_AMOUNT];
    Deck deck;

    public Duelist(Deck deck){
        hand = new Hand(new Card[Hand.HAND_AMOUNT], deck);
        hitPoint = new HitPoint(1000);
        this.deck = deck;
    }

    public void redraw(){
        var selectCardID = selectExchangeCards();
        hand = hand.exchangeCards(selectCardID);
    }


    protected abstract int[] selectExchangeCards();
}
