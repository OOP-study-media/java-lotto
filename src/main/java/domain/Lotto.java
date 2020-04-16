package domain;

import java.util.*;

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

    public static List<Integer> randomNumbers() {
        Random random = new Random();
        Set<Integer> randomNumbers = new HashSet<>();

        while (randomNumbers.size() < SELECT_NUMBER_LENGTH) {
            randomNumbers.add(random.nextInt(RANGE) + 1);
        }
        return new ArrayList<>(randomNumbers);
    }

    public void printNumbers() {
        System.out.println(numbers);
    }

    public boolean containsNumber(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
