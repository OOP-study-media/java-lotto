package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {

    private static final int RANGE = 45;
    private static final int SELECT_NUMBER_LENGTH = 6;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void randomNumbers() {
        Random random = new Random();
        HashSet<Integer> randomSet = new HashSet<>();

        while (randomSet.size() < SELECT_NUMBER_LENGTH) {
            randomSet.add(random.nextInt(RANGE) + 1);
        }
        numbers.addAll(randomSet);
    }

    public void printNumbers() {
        System.out.println(numbers);
    }

    public boolean containsNumber(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
