package lotto;

import domain.Lotto;
import domain.Rank;
import domain.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;

import static domain.Lotto.*;
import static lotto.Validator.*;

public class App {
    public static final int LOTTO_PRICE = 1000;

    private static final Scanner sc = new Scanner(System.in);
    private static final List<Lotto> lottos = new ArrayList<>();
    private static List<Integer> answers = new ArrayList<>();
    private static Map<Rank, Integer> result = new TreeMap<>();
    private static int purchasingAmount;
    private static int bonusNum;
    private static int prizeMoney;

    private static void setPurchasingAmount() {
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

    private static void initLottos() {
        int numberOfLotto = purchasingAmount / LOTTO_PRICE;
        for (int i = 0; i < numberOfLotto; i++) {
            lottos.add(new Lotto(createNumbers()));
        }
    }

    private static List<Integer> createNumbers() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < LOTTO_LENGTH) {
            int randomNumber = (int) (Math.random() * RANDOM_MAX + RANDOM_MIN);
            addRandomNum(numbers, randomNumber);
        }
        return numbers;
    }

    private static void addRandomNum(List<Integer> numbers, int randomNumber) {
        if (!numbers.contains(randomNumber)) {
            numbers.add(randomNumber);
        }
    }

    private static void setAnswers() {
        do {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            answers = Arrays.stream(sc.nextLine().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .distinct()
                    .collect(Collectors.toList());
        } while (!checkAnswers(answers));
    }

    private static void setBonusNum() {
        do {
            System.out.println("보너스 볼을 입력해 주세요.");
            int bonusNum = sc.nextInt();
        } while (!checkBonusNum(answers, bonusNum));
    }

    private static void printLottos() {
        System.out.println(purchasingAmount / LOTTO_PRICE + "개를 구매했습니다.");
        lottos.forEach(Lotto::printOnetLotto);
    }

    private static void initForPrint() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(answers), bonusNum);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
    }

    private static void printRankCount() {
        System.out.println("---- 당첨 통계 ----");
        result.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Rank.MISS)
                .forEach(entry -> {
                    printOneRank(entry);
                    prizeMoney += entry.getKey().getWinningMoney() * entry.getValue();
                });
    }

    private static void printOneRank(Map.Entry<Rank, Integer> oneRank) {
        StringBuilder resultForOneRank = new StringBuilder();
        resultForOneRank.append(oneRank.getKey().getCountOfMatch()).append("개 일치");
        if (oneRank.getKey() == Rank.SECOND) {
            resultForOneRank.append(", 보너스볼 일치");
        }
        System.out.println(resultForOneRank + "(" + oneRank.getKey().getWinningMoney() + ")" + " - " + oneRank.getValue() + "개");
    }

    private static void printRateOfReturn() {
        float rateOfReturn = (float) prizeMoney / (float) purchasingAmount;
        System.out.println("총 수익률은 " + rateOfReturn + "입니다. ");
    }

    public static void main(String[] args) {
        setPurchasingAmount();
        initLottos();
        printLottos();
        setAnswers();
        setBonusNum();
        initForPrint();
        printRankCount();
        printRateOfReturn();
    }
}
