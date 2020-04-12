package domain;

/**
 * 당첨 번호를 담당하는 객체
 */
public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(Lotto lotto, int bonusNo) {
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    public int countOfMatch(Lotto userlotto) {
        int countOfMatch = 0;
        for (Integer integer : userlotto.getNumbers()) {
            if (lotto.getNumbers().contains(integer)) {
                countOfMatch += 1;
            }
        }
        return countOfMatch;
    }

    public boolean isContainsBounus(Lotto userLotto) {
        return userLotto.getNumbers().contains(bonusNo);
    }

    public Rank match(Lotto userLotto) {
        int countOfMatch = countOfMatch(userLotto);
        boolean matchBonus = isContainsBounus(userLotto);
        return Rank.valueOf(countOfMatch, matchBonus);
    }
}