/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package final_project;

/**
 *
 * @author Diya Mavi,Prabhpreet Kaur
 */

import java.util.ArrayList;
import java.util.Scanner;


// Game class
abstract class Game {
    private final String name;
    private ArrayList<GoFishPlayer> players;

    public Game(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<GoFishPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<GoFishPlayer> players) {
        this.players = players;
    }

    public abstract void play();

    public abstract void declareWinner();
}

// GoFishGame.java (Complete code)



public class GoFishGame extends Game {

    private final GroupOfCards deck;
    private final ArrayList<GoFishCard> pile;

    public GoFishGame(String name) {
        super(name);
        deck = new GroupOfCards(52);
        initializeDeck();
        pile = new ArrayList<>();
    }

    private void initializeDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String rank : ranks) {
            GoFishCard card1 = new GoFishCard(rank);
            GoFishCard card2 = new GoFishCard(rank);
            deck.getCards().add(card1);
            deck.getCards().add(card2);
        }
    }

    private void shuffleDeck() {
        deck.shuffle();
    }

    private void dealCards(ArrayList<GoFishPlayer> players, int numCards) {
        for (int i = 0; i < numCards; i++) {
            for (GoFishPlayer player : players) {
                GoFishCard card = (GoFishCard) deck.getCards().remove(0);
                player.addToHand(card);
            }
        }
    }

    private void drawCard(GoFishPlayer player) {
        if (!deck.getCards().isEmpty()) {
            GoFishCard card = (GoFishCard) deck.getCards().remove(0);
            player.addToHand(card);
        }
    }

    private void addToPile(ArrayList<GoFishCard> cards) {
        pile.addAll(cards);
    }

    private boolean checkForPairs(GoFishPlayer player) {
        ArrayList<GoFishCard> pairs = new ArrayList<>();
        for (int i = 0; i < player.hand.size() - 1; i++) {
            GoFishCard card1 = player.hand.get(i);
            for (int j = i + 1; j < player.hand.size(); j++) {
                GoFishCard card2 = player.hand.get(j);
                if (card1.getRank().equals(card2.getRank())) {
                    pairs.add(card1);
                    pairs.add(card2);
                }
            }
        }

        player.hand.removeAll(pairs);
        addToPile(pairs);

        return !pairs.isEmpty();
    }

    @Override
    public void play() {
    // Initialize the game
    ArrayList<GoFishPlayer> players = getPlayers();
    int numPlayers = players.size();
    int numCardsToDeal = 5;

    shuffleDeck();
    dealCards(players, numCardsToDeal);

    // Start the game loop
    int currentPlayerIndex = 0;
    boolean gameOver = false;
    int turnCounter = 0;
    
    while (!gameOver && turnCounter < 7) { // Limiting the game to 7 turns
        GoFishPlayer currentPlayer = players.get(currentPlayerIndex);

        currentPlayer.showHand();
        Scanner scanner = new Scanner(System.in);
        System.out.print(currentPlayer.getName() + ", ask for a card rank (or enter 'stop' to stop the game): ");
        String rank = scanner.nextLine();

        if (rank.equalsIgnoreCase("stop")) {
            System.out.println("Game stopped by user.");
            break;
        }

        if (!isValidRank(rank)) {
            System.out.println("Invalid card rank. Please enter a valid card rank from the deck.");
            continue;
        }

        boolean foundPair = checkForPairs(currentPlayer);

        if (foundPair) {
            System.out.println("You found a pair! Your turn continues.");
        } else {
            System.out.println("Go Fish! Drawing a card...");
            drawCard(currentPlayer);
            foundPair = checkForPairs(currentPlayer);
        }

        if (deck.getCards().isEmpty() && currentPlayer.hand.isEmpty()) {
            gameOver = true;
        }

        if (!foundPair) {
            currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        }
        
        turnCounter++;
    }

    declareWinner();
}

    // Helper method to check if the entered rank is valid
    private boolean isValidRank(String rank) {
    String[] validRanks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    for (String validRank : validRanks) {
        if (validRank.equalsIgnoreCase(rank)) {
            return true;
        }
    }
    return false;
}

    @Override
    public void declareWinner() {
    // Determine and display the winner(s) based on the number of pairs collected
    ArrayList<GoFishPlayer> players = getPlayers();
    int maxPairs = 0;
    ArrayList<GoFishPlayer> winners = new ArrayList<>();

    for (GoFishPlayer player : players) {
        int numPairs = pile.size() / 2;
        System.out.println(player.getName() + " has " + numPairs + " pairs.");
        if (numPairs > maxPairs) {
            maxPairs = numPairs;
            winners.clear();
            winners.add(player);
        } else if (numPairs == maxPairs) {
            winners.add(player);
        }
    }

    if (!winners.isEmpty()) {
        if (winners.size() == 1) {
            System.out.println("The winner is " + winners.get(0).getName() + "!");
        } else {
            System.out.print("It's a tie between ");
            for (int i = 0; i < winners.size() - 1; i++) {
                System.out.print(winners.get(i).getName() + ", ");
            }
            System.out.println("and " + winners.get(winners.size() - 1).getName() + "!");
        }
    } else {
        System.out.println("No winners.");
    }
}
}
