package domain;

import java.util.List;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {
    public static final int RANDOM_MAX = 45;
    public static final int RANDOM_MIN = 1;
    public static final int LOTTO_LENGTH = 6;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
        createNumbers();
    }

    public void addRandomNum(int randomNumber) {
        if (!numbers.contains(randomNumber)) {
            numbers.add(randomNumber);
        }
    }

    public void createNumbers() {
        while (numbers.size() < LOTTO_LENGTH) {
            int randomNumber = (int) (Math.random() * RANDOM_MAX + RANDOM_MIN);
            addRandomNum(randomNumber);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
