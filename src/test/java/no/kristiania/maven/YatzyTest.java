package no.kristiania.maven;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YatzyTest {

    @Test
    void shouldCalculateForOnes() {
        assertEquals(0, score(Category.Ones, new int[] { 2, 3, 4, 5, 6 }));
        assertEquals(3, score(Category.Ones, new int[] { 2, 1, 4, 1, 1 }));
        assertEquals(5, score(Category.Ones, new int[] { 1, 1, 1, 1, 1 }));
    }

    @Test
    void shouldCalculateForTwoes() {
        assertEquals(2, score(Category.Twos, new int[] { 2, 3, 4, 5, 6 }));
        assertEquals(4, score(Category.Twos, new int[] { 2, 2, 4, 1, 1 }));
        assertEquals(0, score(Category.Twos, new int[] { 1, 1, 1, 1, 1 }));
    }

    @Test
    void shouldCalculateForThrees() {
        assertEquals(3, score(Category.Threes, new int[] { 2, 3, 4, 2, 6 }));
        assertEquals(6, score(Category.Threes, new int[] { 3, 2, 4, 3, 1 }));
        assertEquals(0, score(Category.Threes, new int[] { 1, 1, 1, 1, 1 }));
    }

    @Test
    void shouldCalculateForFours() {
        assertEquals(4, score(Category.Fours, new int[] { 2, 3, 4, 2, 6 }));
        assertEquals(8, score(Category.Fours, new int[] { 4, 2, 4, 3, 1 }));
        assertEquals(0, score(Category.Fours, new int[] { 1, 1, 1, 1, 1 }));
    }

    @Test
    void shouldCalculateForFives() {
        assertEquals(5, score(Category.Fives, new int[] { 2, 5, 4, 2, 6 }));
        assertEquals(10, score(Category.Fives, new int[] { 3, 2, 5, 3, 5 }));
        assertEquals(0, score(Category.Fives, new int[] { 1, 1, 1, 1, 1 }));
    }

    @Test
    void shouldCalculateForSixes() {
        assertEquals(6, score(Category.Sixes, new int[] { 3, 6, 4, 3, 1 }));
        assertEquals(12, score(Category.Sixes, new int[] { 1, 1, 6, 1, 6 }));
        assertEquals(0, score(Category.Sixes, new int[] { 2, 3, 4, 2, 1 }));
    }

    @Test
    void shouldCalculateForPairs() {
        assertEquals(6, score(Category.Pair, new int[] { 3, 6, 4, 3, 1 }));
        assertEquals(12, score(Category.Pair, new int[] { 1, 1, 6, 6, 1 }));
        assertEquals(0, score(Category.Pair, new int[] { 6, 3, 4, 2, 1 }));
    }


    public int score(Category category, int[] diceArray) {

        int categoryValue = Category.valueOf(category.toString()).ordinal() + 1;
        int sum = 0;
        List<Integer> diceList =Arrays.stream(diceArray).boxed().collect(Collectors.toList());


        for (int i = 0; i < diceArray.length; i++) {

            // Calculate for singles, 1 through 6.
            if (categoryValue >= 1 && categoryValue <= 6) {
                if(diceArray[i] == categoryValue) { sum += diceArray[i]; }
            }

            if (category == Category.Pair){
                for (int j = 6; j > 0; j--){
                    if (Collections.frequency(diceList,j ) > 1){
                        sum = j * 2;
                        break;
                    }
                }
            }

        }

        return sum;
    }

    enum Category {
        Ones,
        Twos,
        Threes,
        Fours,
        Fives,
        Sixes,
        Pair,
        TwoPairs,
        ThreeOfAKind,
        FourOfAKind,
        FullHouse,
        SmallStraight,
        LargeStraight,
        Chance,
        Yatzy
    }

    enum DiceScore {
        One,
        Two,
        Three,
        Four,
        Five,
        Six
    }
}

