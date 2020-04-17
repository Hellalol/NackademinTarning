package com.pillan;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int diceSides = 6;         // How many sides of the dice.
        int howManyDice;          // How many dices.
        int sumOfDice = 0;       // Sum of the dice.
        int rolledTimes = 0;    // The amount the dice is rolled.
        String startOver;      // String for the start over question.

        System.out.println("Fredrik Hämäläinen Billing 2019-05-20 18:27");
        System.out.println("Välkommen till tärningsprogrammet!\n" +
                "Du kan slå max 5 tärningar.\nOm du slår 6 på någon av tärningarna kommer två nya tärningar slås.");


        //Do-loop that lets the player roll until n is pressed.
        do {

            //the player is asked how many dice that will be thrown.
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Vänligen ange hur många tärningar du vill slå!");
            String name = keyboard.next();

            // Try/catch-statement that tests if the value is numerical.
            howManyDice = 0;
            try {
                howManyDice = Integer.parseInt(name);

            } catch (NumberFormatException wrong) {

                Toolkit.getDefaultToolkit().beep();
                System.out.println("Vänligen ange ett numeriskt värde mellan 1 och 5!\nProgrammet avslutas!");
                System.exit(0);
            }

            /* If-statement that checks if you are rolling between 1 and 5.
            If not, a warning gets presented and the program exits.*/
            if (howManyDice > 5) {
                Toolkit.getDefaultToolkit().beep();
                System.out.println("Du får endast slå tärningen 5 gånger!\nProgrammet avslutas!");
                System.exit(0);
            } else if (howManyDice < 1) {
                Toolkit.getDefaultToolkit().beep();
                System.out.println("Du kan inte slå en tärning mindre än en gång!\nProgrammet avslutas!");
                System.exit(0);
            }

            // Calling the method RollTheDice which returns two values.
            int[] sumAndRolls = (RollTheDice(diceSides, howManyDice, sumOfDice, rolledTimes));

            /* If-statement that changes the grammar if the roll is rolled once or several times.
            It also prints the sum and the amount of times the dice are rolled.*/
            if (sumAndRolls[1] == 1) {
                System.out.println("Tärningen slogs: " + sumAndRolls[1] + " gång.");
                System.out.println("Summan av tärningen är: " + sumAndRolls[0]);
            } else {
                System.out.println("Tärningarna slogs: " + sumAndRolls[1] + " gånger.");
                System.out.println("Summan av tärningarna är: " + sumAndRolls[0]);
            }

            /*Asks the player if the player wants to start again.
            the only valid inputs should be j or n.*/
            keyboard = new Scanner(System.in);
            System.out.println("Vill du slå igen? ja/nej");
            startOver = keyboard.next();


            // If-statement that checks if the letter is j or n, the input is not case-sensitive.
            if (!startOver.equalsIgnoreCase("ja") && !startOver.equalsIgnoreCase("nej")) {

                Toolkit.getDefaultToolkit().beep();
                System.out.println("Varken ja eller nej skrevs!\nProgrammet avslutas!");
                System.exit(0);
            }

            // The do-loop continues as long as j is pressed.
        } while (startOver.equalsIgnoreCase("ja"));


        // if n is pressed the program closes with a message.
        if (startOver.equalsIgnoreCase("nej"))
            System.out.println("Tack för att du använde programmet!");
        System.exit(0);

    } //Main method


    // Throws a dice and adds the value of that dice to the sum for the amount of times you put in.
    private static int[] RollTheDice(int diceSides, int howManyDice, int sumOfDice, int rolledTimes) {

        for (int i = 1; i <= howManyDice; i++) {
            int roll = (int) (Math.random() * diceSides) + 1;
            System.out.println(roll);
            rolledTimes += 1;
            if (roll < 6 && roll > 0) {
                sumOfDice += roll;
            } else if (roll == 6) {
                System.out.println("Du slog 6, slår två nya tärningar!");
                int[] returnValuesExtra = RollTheDice(diceSides, 2, 0, 0);
                rolledTimes += returnValuesExtra[1];
                sumOfDice += returnValuesExtra[0];
            }

        }
        //array which makes it possible to return more than one value from the method.
        int[] returnValues = new int[2];
        returnValues[0] = sumOfDice;
        returnValues[1] = rolledTimes;
        return returnValues;
    } //method RollTheDice

} //class Main


