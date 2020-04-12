package lotto;

import domain.Lotto;
import domain.Rank;
import domain.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;

import static lotto.Validator.*;

public class App {
    public static final int LOTTO_PRICE = 1000;
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Lotto> lottos = new ArrayList<>();
    private static List<Integer> answers = new ArrayList<>();
    private static HashMap<Rank, Integer> result = new HashMap<>();
    private static int purchasingAmount;
    private static int bonusNum;
    private static int prizeMoney;

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

    public static void setAnswers() {
        do {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            answers = Arrays.stream(sc.nextLine().split(","))
                    .map(String::trim).map(Integer::parseInt).distinct()
                    .collect(Collectors.toList());
        } while (!checkAnswers(answers));
        do {
            System.out.println("보너스 볼을 입력해 주세요.");
        } while (!checkBonusNum(answers, bonusNum = sc.nextInt()));
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

    public static void initForPrint() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(answers), bonusNum);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
    }

    public static void printRankCount() {
        System.out.println("---- 당첨 통계 ----");
        result.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).
                filter(entry -> entry.getKey() != Rank.MISS).forEach(entry -> {
            StringBuilder resultforOneRank = new StringBuilder();
            resultforOneRank.append(entry.getKey().getCountOfMatch() + "개 일치");
            if (entry.getKey() == Rank.SECOND) {
                resultforOneRank.append(", 보너스볼 일치");
            }
            System.out.println(resultforOneRank + "(" + entry.getKey().getWinningMoney() + ")" + " - " + entry.getValue() + "개");
            prizeMoney += entry.getKey().getWinningMoney() * entry.getValue();
        });
    }

    public static void printRateOfReturn() {
        float rateOfReturn = (float) prizeMoney / (float) purchasingAmount;
        System.out.println("총 수익률은 " + rateOfReturn + "입니다. ");
    }

    public static void main(String[] args) {
        setPurchasingAmount();
        initLottos();
        printLottos();
        setAnswers();
        initForPrint();
        printRankCount();
        printRateOfReturn();
    }
}
