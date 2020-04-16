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

    public Rank match(Lotto userLotto) {
        int matchCount = 0;
        for (int lottoNumber : lotto.getNumbers()) {
            matchCount += userLotto.containsNumber(lottoNumber) ? 1 : 0;
        }
        boolean matchBonus = userLotto.containsNumber(bonusNo);
        return Rank.valueOf(matchCount, matchBonus);
    }
}
