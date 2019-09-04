package no.kristiania.maven;

import org.junit.jupiter.api.Test;

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

    @Test
    void shouldCalculateForTwoPairs() {
        assertEquals(8, score(Category.TwoPairs, new int[] { 3, 1, 4, 3, 1 }));
        assertEquals(14, score(Category.TwoPairs, new int[] { 1, 1, 6, 6, 1 }));
        assertEquals(0, score(Category.TwoPairs, new int[] { 6, 6, 4, 2, 1 }));
        assertEquals(18, score(Category.TwoPairs, new int[] { 6, 6, 3, 3, 1 }));
    }

    @Test
    void shouldCalculateForThreeOfAKind() {
        assertEquals(9, score(Category.ThreeOfAKind, new int[] { 3, 3, 4, 3, 1 }));
        assertEquals(3, score(Category.ThreeOfAKind, new int[] { 1, 1, 6, 6, 1 }));
        assertEquals(0, score(Category.ThreeOfAKind, new int[] { 6, 3, 4, 2, 1 }));
    }

    @Test
    void shouldCalculateForFourOfAKind() {
        assertEquals(12, score(Category.FourOfAKind, new int[] { 3, 3, 3, 3, 1 }));
        assertEquals(4, score(Category.FourOfAKind, new int[] { 1, 1, 6, 1, 1 }));
        assertEquals(0, score(Category.FourOfAKind, new int[] { 6, 3, 4, 2, 1 }));
    }

    @Test
    void shouldCalculateForFullHouse() {
        assertEquals(13, score(Category.FullHouse, new int[] { 3, 3, 3, 2, 2 }));
        assertEquals(15, score(Category.FullHouse, new int[] { 1, 1, 6, 6, 1 }));
        assertEquals(0, score(Category.FullHouse, new int[] { 3, 3, 3, 3, 3 }));
    }

    @Test
    void shouldCalculateForSmallStraight() {
        assertEquals(15, score(Category.SmallStraight, new int[] { 1, 2, 3, 4, 5 }));
        assertEquals(15, score(Category.SmallStraight, new int[] { 5, 4, 3, 2, 1 }));
        assertEquals(0, score(Category.SmallStraight, new int[] { 1, 2, 3, 4, 3 }));
        assertEquals(0, score(Category.SmallStraight, new int[] { 2, 3, 4, 5, 6 }));
    }

    @Test
    void shouldCalculateForLargeStraight() {
        assertEquals(20, score(Category.LargeStraight, new int[] { 2, 3, 4, 5, 6 }));
        assertEquals(20, score(Category.LargeStraight, new int[] { 6, 5, 4, 3, 2 }));
        assertEquals(0, score(Category.LargeStraight, new int[] { 2, 3, 4, 3, 6 }));
        assertEquals(0, score(Category.LargeStraight, new int[] { 1, 2, 3, 4, 5 }));
    }

    @Test
    void shouldCalculateForChance() {
        assertEquals(20, score(Category.Chance, new int[] { 2, 3, 4, 5, 6 }));
        assertEquals(5, score(Category.Chance, new int[] { 1, 1, 1, 1, 1 }));
        assertEquals(18, score(Category.Chance, new int[] { 4, 3, 4, 3, 4 }));
        assertEquals(15, score(Category.Chance, new int[] { 1, 2, 3, 4, 5 }));
    }

    @Test
    void shouldCalculateForYatzy() {
        assertEquals(50, score(Category.Yatzy, new int[] { 2, 2, 2, 2, 2 }));
        assertEquals(50, score(Category.Yatzy, new int[] { 1, 1, 1, 1, 1 }));
        assertEquals(50, score(Category.Yatzy, new int[] { 4, 4, 4, 4, 4 }));
        assertEquals(0, score(Category.Yatzy, new int[] { 1, 2, 3, 4, 5 }));
    }


    public int score(Category category, int[] diceArray) {

        int categoryValue = Category.valueOf(category.toString()).ordinal() + 1;
        int sum = 0;

        List<Integer> diceList = Arrays.stream(diceArray).boxed().collect(Collectors.toList());

        // Calculate for singles, 1 through 6.
        if (categoryValue >= 1 && categoryValue <= 6) {
            sum = Collections.frequency(diceList, categoryValue) * categoryValue;
        }

        if (category == Category.Pair){
            for (int j = 6; j > 0; j--){
                if (Collections.frequency(diceList, j) > 1){
                    sum = j * 2;
                    break;
                }
            }
        }

        if (category == Category.TwoPairs) {

            int pairOneSum = 0;
            int pairTwoSum = 0;

            for (int j = 6; j > 0; j--) {
                if (Collections.frequency(diceList, j) > 1){
                    pairOneSum = j * 2;
                    for (int k = j - 1; k > 0; k--) {
                        if (Collections.frequency(diceList, k) > 1){
                            pairTwoSum = k * 2;
                            sum = pairOneSum + pairTwoSum;
                            break;
                        }
                    }
                    break;
                }
            }
        }

        if (category == Category.ThreeOfAKind){
            for (int j = 6; j > 0; j--){
                if (Collections.frequency(diceList, j) > 2){
                    sum = j * 3;
                    break;
                }
            }
        }

        if (category == Category.FourOfAKind){
            for (int j = 6; j > 0; j--){
                if (Collections.frequency(diceList, j) > 3){
                    sum = j * 4;
                    break;
                }
            }
        }

        if (category == Category.FullHouse) {

            int threesSum = 0;
            int twosSum = 0;

            for (int j = 6; j > 0; j--) {
                if (Collections.frequency(diceList, j) > 2){
                    threesSum = j * 3;
                    for (int k = 6; k > 0; k--) {
                        if (Collections.frequency(diceList, k) > 1 && k != j){
                            twosSum = k * 2;
                            sum = threesSum + twosSum;
                            break;
                        }
                    }
                    break;
                }
            }
        }

        if (category == Category.SmallStraight) {
            if(diceList.contains(1) &&
                    diceList.contains(2) &&
                    diceList.contains(3) &&
                    diceList.contains(4) &&
                    diceList.contains(5)
            ) {
                sum = 15;
            }
        }

        if (category == Category.LargeStraight) {
            if(diceList.contains(2) &&
                    diceList.contains(3) &&
                    diceList.contains(4) &&
                    diceList.contains(5) &&
                    diceList.contains(6)
            ) {
                sum = 20;
            }
        }

        if (category == Category.Chance) {
            for (int diceValue : diceList) {
                sum += diceValue;
            }
        }

        if (category == Category.Yatzy) {
            for (int j = 6; j > 0; j--){
                if (Collections.frequency(diceList, j) == 5){
                    sum = 50;
                    break;
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
}
