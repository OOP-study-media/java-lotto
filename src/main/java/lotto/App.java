package lotto;

import domain.Lotto;
import domain.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;

import static domain.Lotto.*;

public class App {
    private static final int LOTTO_PRICE = 1000;
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Lotto> lottos = new ArrayList<>();
    private static List<Integer> answers = new ArrayList<>();
    private static WinningLotto winningLotto;
    private static int purchasingAmount;
    private static int bonusNum;

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

    public static void setPurchasingAmount() {
        do {
            System.out.println("구입 금액을 입력하세요.");
            try {
                purchasingAmount = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("입력은 정수로만 가능합니다.");
            } finally {
                sc.nextLine(); //Scanner 버그 방지용 코드
            }
        } while (!checkPurchasingAmount(purchasingAmount));
    }

    public static void initLottos() {
        for (int i = 0; i < purchasingAmount / LOTTO_PRICE; i++) {
            lottos.add(new Lotto(new ArrayList<>()));
        }
    }

    public static void printLottos() {
        System.out.println(purchasingAmount / LOTTO_PRICE + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers().toString());
        }
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

    public static boolean checkBonusNum(int bonusNum) {
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

    public static void setAnswers() {
        do {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            answers = Arrays.stream(sc.nextLine().split(","))
                    .map(String::trim).map(Integer::parseInt).distinct()
                    .collect(Collectors.toList());
        } while (!checkAnswers(answers));
        do {
            System.out.println("보너스 볼을 입력해 주세요.");
        } while (!checkBonusNum(bonusNum = sc.nextInt()));
    }

    public static void createWinningLotto(){
        winningLotto = new WinningLotto(new Lotto(answers), bonusNum);
    }

    public static void main(String[] args) {
        setPurchasingAmount();
        initLottos();
        printLottos();
        setAnswers();
        createWinningLotto();
    }
}
