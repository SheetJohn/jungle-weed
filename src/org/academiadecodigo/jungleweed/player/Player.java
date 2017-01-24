package org.academiadecodigo.jungleweed.player;

import org.academiadecodigo.jungleweed.card.Card;

import java.util.Arrays;

/**
 * Created by codecadet on 1/22/17.
 */
public class Player {

    private Card[] faceDownCards;
    private Card[] revealedCards;
    private Card faceUpCard;


    private boolean emptyFaceDown;


    private int maxPossibleCards;

    private boolean agarraPau;

    public Player(int maxPossibleCards) {
        this.maxPossibleCards = maxPossibleCards;

        this.revealedCards = new Card[this.maxPossibleCards];
        this.faceDownCards = new Card[this.maxPossibleCards];

    }

    //TODO remove before commmit

    public Player(Card[] cards) {

        this.maxPossibleCards = cards.length;
        this.revealedCards = new Card[this.maxPossibleCards];
        this.faceDownCards = new Card[this.maxPossibleCards];

        for (int i=0; i < cards.length; i++) {
            this.faceDownCards[i] = cards[i];
        }



    }

    public boolean revealNextCard() {

        if (emptyFaceDown) {
            return true;
        }

        if (faceUpCard != null) {
            this.pushRevealedCards();
        }

        this.faceUpCard = this.faceDownCards[0];

        this.faceDownCards[0] = null;

        boolean hasCardsInPile  = this.defragFaceDownCards();

        if (!hasCardsInPile) {
            this.emptyFaceDown = true;
        }

        return this.emptyFaceDown;


    }

    private void pushRevealedCards() {

        Card[] result = new Card[this.revealedCards.length];

        result[0] = this.faceUpCard;

        for (int i = 1; i < result.length ; i++) {
            result[i] = this.revealedCards[i-1];
        }

        this.revealedCards = result;

    }

    private boolean defragFaceDownCards() {

        Card[] newArray = new Card[this.faceDownCards.length];
        boolean result = false;
        int i = 0;
        int j = 0;
        for (; i < this.faceDownCards.length; i++) {
            if (faceDownCards[i] == null){
                continue;
            }

            result = true;
            newArray[j] = this.faceDownCards[i];
            j++;

        }

        this.faceDownCards = newArray;

        return result;
    }

    //when the player receives cards from the game
    public void addCards(Card[] cards) {

        int i = 0;
        int j = 0;
        for(; i < this.faceDownCards.length; i++) {
            if (this.faceDownCards[i] != null) {
                continue;
            }

            this.faceDownCards[i] = cards[j];
            j++;

            if (j == cards.length) {
                break;
            }

        }

        this.emptyFaceDown = false;

    }


    public int getTotalNumberOfCards() {
        int nHidden = 0;
        int nRevealed = 0;

        for (int i = 0; i < this.maxPossibleCards; i++) {
            if (this.faceDownCards[i] != null) {
                nHidden++;
            }
            if (this.revealedCards[i] != null) {
                nRevealed++;
            }
        }

        return nHidden + nRevealed + (this.faceUpCard != null ? 1 : 0);
    }


    //when the player gives cards to the game
    public Card[] giveCards() {

        if (this.faceUpCard == null) {
            return null;
        }

        Card[] result = new Card[this.revealedCards.length];

        result[0] = this.faceUpCard;
        this.faceUpCard = null;

        for (int i = 0; i < this.revealedCards.length - 1; i++) {

            if (this.revealedCards[i] == null) {
                break;
            }

            result[i+1] = this.revealedCards[i];
            this.revealedCards[i] = null;

        }

        return result;

    }

    public void printFaceDownCards() {
        for (Card c : this.faceDownCards) {
            System.out.println(c);
        }
    }

    public void printRevealedCards() {
        for (Card c : this.revealedCards) {
            System.out.println(c);
        }
    }

    public Card getFaceUpCard() {
        return this.faceUpCard;
    }

    public boolean isAgarraPau() {
        return agarraPau;
    }

    public void agarraPau() {
        this.agarraPau = true;
    }

    public void largaPau() {
        this.agarraPau = false;
    }

    public int getMaxPossibleCards() {
        return maxPossibleCards;
    }

    public boolean isEmptyFaceDown() {
        return emptyFaceDown;
    }

}
