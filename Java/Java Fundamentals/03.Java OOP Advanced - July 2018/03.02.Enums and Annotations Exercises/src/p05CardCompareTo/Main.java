package p05CardCompareTo;

import p05CardCompareTo.annotations.EnumAnnotation;
import p05CardCompareTo.enums.Ranks;
import p05CardCompareTo.enums.Suits;
import p05CardCompareTo.models.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Class<?> ranksClass = Ranks.class;
        Class<?> suitsClass = Suits.class;

        if (input.equals(ranksClass.getAnnotation(EnumAnnotation.class).category())){
            String type = ranksClass.getAnnotation(EnumAnnotation.class).type();
            String description = ranksClass.getAnnotation(EnumAnnotation.class).description();
            System.out.printf("Type = %s, Description = %s", type, description);
        } else if (input.equals(suitsClass.getAnnotation(EnumAnnotation.class).category())){
            String type = suitsClass.getAnnotation(EnumAnnotation.class).type();
            String description = suitsClass.getAnnotation(EnumAnnotation.class).description();
            System.out.printf("Type = %s, Description = %s", type, description);
        } else if ("Card Deck".equals(input)){
            for (Suits s:Suits.values()
                    ) {
                for (Ranks r:Ranks.values()
                        ) {
                    System.out.println(r+" of "+s);
                }
            }

        }else{
            String card1Rank = input;
            String card1Suit = reader.readLine();

            String card2Rank = reader.readLine();
            String card2Suit = reader.readLine();

            Card card1 = new Card(card1Rank, card1Suit);
            Card card2 = new Card(card2Rank, card2Suit);

            if (card1.compareTo(card2) > 0){
                System.out.println(card1.toString());
            }else {
                System.out.println(card2.toString());
            }
        }


    }
}
