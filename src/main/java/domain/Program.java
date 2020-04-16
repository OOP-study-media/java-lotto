package domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Program {
    private static final int PRICE = 1000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int purchaseCount = inputPrice() / PRICE;
        List<Lotto> lottoList = setupLottoList(purchaseCount);
        printLottoList(lottoList);

        WinningLotto winningLotto = new WinningLotto(new Lotto(inputNumbers()), inputBonusBall());

        TreeMap<Rank, Integer> rankMap = setupRankMap(lottoList, winningLotto);
        printRank(rankMap);
        printRate(rankMap, purchaseCount);
    }

    private static int inputPrice() throws Exception {
        return inputInt("구입금액을 입력해 주세요.");
    }

    private static int inputInt(String print) throws Exception {
        System.out.println(print);
        return Integer.parseInt(br.readLine());
    }

    private static List<Lotto> setupLottoList(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        while (count != 0) {
            Lotto lotto = new Lotto(new ArrayList<>());
            lotto.randomNumbers();
            lottoList.add(lotto);
            count--;
        }
        return lottoList;
    }

    private static void printLottoList(List<Lotto> lottoList) {
        System.out.println(lottoList.size() + "개를 구매했습니다");
        for (Lotto lotto : lottoList) {
            lotto.printNumbers();
        }
    }

    private static List<Integer> inputNumbers() throws Exception {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        String[] input = br.readLine().split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String inputNumber : input) {
            numbers.add(Integer.parseInt(inputNumber));
        }
        return numbers;
    }

    private static int inputBonusBall() throws Exception {
        return inputInt("보너스 볼을 입력해 주세요.");
    }

    private static TreeMap<Rank, Integer> setupRankMap(List<Lotto> lottoList, WinningLotto winningLotto) {
        TreeMap<Rank, Integer> rankMap = initRankMap();
        for (Lotto userLotto : lottoList) {
            Rank rank = winningLotto.match(userLotto);
            rankMap.put(rank, rankMap.get(rank) + 1);
        }
        return rankMap;
    }

    private static TreeMap<Rank, Integer> initRankMap() {
        TreeMap<Rank, Integer> rankMap = new TreeMap<>(new rankComparator());
        for (Rank rank : Rank.values()) {
            rankMap.put(rank, 0);
        }
        return rankMap;
    }

    private static void printRank(TreeMap<Rank, Integer> rankMap) {
        System.out.println("당첨 통계");
        for (Map.Entry<Rank, Integer> rankEntry : rankMap.entrySet()) {
            Rank rank = rankEntry.getKey();
            int rankCount = rankEntry.getValue();

            System.out.println(appendRankString(rank, rankCount));
        }
    }

    private static String appendRankString(Rank rank, int rankCount) {
        if (rank == Rank.MISS) {
            return "---------";
        }
        String rankString = rank.getCountOfMatch() + "개 일치";

        if (rank == Rank.SECOND) {
            rankString += ", 보너스 볼 일치 ";
        }
        return rankString + "(" + rank.getWinningMoney() + "원)-" + rankCount + "개";
    }

    private static void printRate(TreeMap<Rank, Integer> rankMap, int purchaseCount) {
        float sum = 0;

        for (Map.Entry<Rank, Integer> rankEntry : rankMap.entrySet()) {
            int winningMoney = rankEntry.getKey().getWinningMoney();
            int rankCount = rankEntry.getValue();
            sum += (winningMoney * rankCount);
        }
        System.out.println("총 수익률은 " + (sum / PRICE) / purchaseCount + "입니다.");
    }

    private static class rankComparator implements Comparator<Rank> {

        @Override
        public int compare(Rank o1, Rank o2) {
            return Integer.compare(o1.getWinningMoney(), o2.getWinningMoney());
        }
    }
}
