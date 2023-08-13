/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package final_project;

/**
 *
 * @author Prabhpreet Kaur,2023
 */

// Card class
abstract class Card {
    @Override
    public abstract String toString();
}

// GoFishCard class (extends Card)
class GoFishCard extends Card {
    private final String rank;

    public GoFishCard(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank;
    }
}
