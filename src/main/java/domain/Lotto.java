package domain;

import java.util.List;

/**
 * 로또 한장을 의미하는 객체
 */
public class Lotto {
    public static final int RANDOM_MAX = 45;
    public static final int RANDOM_MIN = 1;
    public static final int LOTTO_LENGTH = 6;
    private final int COUNT_MATCH_DEFAULT = 0;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int countOfMatch(Lotto lotto){
        int countMatch = COUNT_MATCH_DEFAULT;
        for (Integer number : numbers){
            countMatch += checkAnotherLottoContain(lotto, number);
        }
        return countMatch;
    }

    private int checkAnotherLottoContain(Lotto lotto, int number){
        if (lotto.containNumber(number)){
            return 1;
        }
        return 0;
    }

    public boolean containNumber(int number){
        return numbers.contains(number);
    }

    public void printOnetLotto(){
        System.out.println(numbers.toString());
    }
}
