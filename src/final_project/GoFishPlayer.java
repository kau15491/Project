/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package final_project;

/**
 *
 * @author Prabhpreet Kaur,2023
 */

import java.util.ArrayList;


// Player class
abstract class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void play();
}

// GoFishPlayer class (extends Player)
class GoFishPlayer extends Player {
    ArrayList<GoFishCard> hand;

    public GoFishPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    public void addToHand(GoFishCard card) {
        hand.add(card);
    }

    public boolean hasCard(String rank) {
        for (GoFishCard card : hand) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<GoFishCard> giveCards(String rank) {
        ArrayList<GoFishCard> cardsToGive = new ArrayList<>();
        for (int i = hand.size() - 1; i >= 0; i--) {
            GoFishCard card = hand.get(i);
            if (card.getRank().equals(rank)) {
                cardsToGive.add(card);
                hand.remove(i);
            }
        }
        return cardsToGive;
    }

    public void showHand() {
        System.out.print(getName() + "'s hand: ");
        for (GoFishCard card : hand) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    @Override
    public void play() {
        // The Go Fish player logic will be implemented here.
        // In the basic version, we'll let the player ask for a specific rank.
    }
}


