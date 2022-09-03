public class Hand {
    public static final int HAND_AMOUNT = 5;
    final Card [] cards;
    final Deck deck;

    public Hand(Card[] value, Deck deck) {
        this.cards = value;
        this.deck = deck;
    }

    public void display(){
        for (Card card:cards){
            System.out.printf("%s ", card.name);
        }
    }

    public Hand initDraw(){
        Card[] cards = new Card[HAND_AMOUNT];
        for (int i = 0; i < HAND_AMOUNT; i++) {
            cards[i] = deck.draw();
        }
        return new Hand(cards, deck);
    }
    public Hand exchangeCards(int[] changeIndex) {
        Card[] cards = this.cards;
        for (int index : changeIndex) {
            cards[index - 1] = deck.draw();
        }
        return new Hand(cards, deck);
    }
}
