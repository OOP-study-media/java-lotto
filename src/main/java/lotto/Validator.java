package lotto;

import java.util.List;

import static domain.Lotto.*;
import static lotto.App.LOTTO_PRICE;

public class Validator {

    public static boolean checkPurchasingAmount(int purchasingAmount) {
        if (purchasingAmount < LOTTO_PRICE) {
            System.out.println("구입 금액은 최소 천원 이상이어야 합니다.");
            return false;
        }
        if (purchasingAmount % LOTTO_PRICE != 0) {
            System.out.println("구입 금액은 1000원 단위로만 가능합니다.");
            return false;
        }
        return true;
    }

    public static boolean checkAnswers(List<Integer> answers) {
        if (answers.size() != LOTTO_LENGTH) {
            System.out.println("당첨 번호는 6개이어야 합니다. (중복된 숫자 없는지 확인)");
            return false;
        }
        for (Integer integer : answers) {
            if (integer > RANDOM_MAX || integer < RANDOM_MIN) {
                System.out.println("1~45 사이의 값을 입력해 주세요. ");
                return false;
            }
        }
        return true;
    }

    public static boolean checkBonusNum(List<Integer> answers, int bonusNum) {
        if (bonusNum > RANDOM_MAX || bonusNum < RANDOM_MIN) {
            System.out.println("1~45 사이의 값을 입력해 주세요. ");
            return false;
        }
        if (answers.contains(bonusNum)) {
            System.out.println("지난 주 당첨 번호에 이미 보너스 넘버가 포함되어 있습니다. ");
            return false;
        }
        return true;
    }
}
