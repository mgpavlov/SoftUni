package p01CardSuit;

import p01CardSuit.enums.Ranks;
import p01CardSuit.enums.Suits;
import p01CardSuit.models.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String input2;
        if ("Card Suits".equals(input)){
            System.out.println("Card Suits:");
            System.out.println(Enum.valueOf(Suits.class, "CLUBS").toString());
            System.out.println(Enum.valueOf(Suits.class, "DIAMONDS").toString());
            System.out.println(Enum.valueOf(Suits.class, "HEARTS").toString());
            System.out.println(Enum.valueOf(Suits.class, "SPADES").toString());
        }else if ("Card Ranks".equals(input)){
            System.out.println("Card Ranks:");
            System.out.println(Enum.valueOf(Ranks.class, "ACE").toString());
            System.out.println(Enum.valueOf(Ranks.class, "TWO").toString());
            System.out.println(Enum.valueOf(Ranks.class, "THREE").toString());
            System.out.println(Enum.valueOf(Ranks.class, "FOUR").toString());
            System.out.println(Enum.valueOf(Ranks.class, "FIVE").toString());
            System.out.println(Enum.valueOf(Ranks.class, "SIX").toString());
            System.out.println(Enum.valueOf(Ranks.class, "SEVEN").toString());
            System.out.println(Enum.valueOf(Ranks.class, "EIGHT").toString());
            System.out.println(Enum.valueOf(Ranks.class, "NINE").toString());
            System.out.println(Enum.valueOf(Ranks.class, "TEN").toString());
            System.out.println(Enum.valueOf(Ranks.class, "JACK").toString());
            System.out.println(Enum.valueOf(Ranks.class, "QUEEN").toString());
            System.out.println(Enum.valueOf(Ranks.class, "KING").toString());
        }else{
            input2 = reader.readLine();
            Card card = new Card(input, input2);
            System.out.println(card.toString());

        }
    }
}
