package domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Program {
    private static final int PRICE = 1000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int purchaseCount = inputPrice() / PRICE;
        List<Lotto> lottoTickets = setupLottoTickets(purchaseCount);
        printLottoTickets(lottoTickets);

        WinningLotto winningLotto = new WinningLotto(new Lotto(inputNumbers()), inputBonusBall());

        TreeMap<Rank, Integer> rankCount = setupRankCount(lottoTickets, winningLotto);
        printRank(rankCount);
        printRate(rankCount, purchaseCount);
    }

    private static int inputPrice() throws Exception {
        return inputInt("구입금액을 입력해 주세요.");
    }

    private static int inputInt(String inputMessage) throws Exception {
        System.out.println(inputMessage);
        return Integer.parseInt(br.readLine());
    }

    private static List<Lotto> setupLottoTickets(int count) {
        List<Lotto> lottoTickets = new ArrayList<>();
        while (count != 0) {
            lottoTickets.add(new Lotto(Lotto.randomNumbers()));
            count--;
        }
        return lottoTickets;
    }

    private static void printLottoTickets(List<Lotto> lottoTickets) {
        System.out.println(lottoTickets.size() + "개를 구매했습니다");
        for (Lotto lotto : lottoTickets) {
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

    private static TreeMap<Rank, Integer> setupRankCount(List<Lotto> lottoTickets, WinningLotto winningLotto) {
        TreeMap<Rank, Integer> rankCount = initRankCount();
        for (Lotto userLotto : lottoTickets) {
            Rank rank = winningLotto.match(userLotto);
            rankCount.put(rank, rankCount.get(rank) + 1);
        }
        return rankCount;
    }

    private static TreeMap<Rank, Integer> initRankCount() {
        TreeMap<Rank, Integer> rankCount = new TreeMap<>(new rankComparator());
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }
        return rankCount;
    }

    private static void printRank(TreeMap<Rank, Integer> rankCount) {
        System.out.println("당첨 통계");
        for (Map.Entry<Rank, Integer> rankEntry : rankCount.entrySet()) {
            Rank rank = rankEntry.getKey();
            int count = rankEntry.getValue();

            System.out.println(appendRankString(rank, count));
        }
    }

    private static String appendRankString(Rank rank, int count) {
        if (rank == Rank.MISS) {
            return "---------";
        }
        String rankString = rank.getCountOfMatch() + "개 일치";

        if (rank == Rank.SECOND) {
            rankString += ", 보너스 볼 일치 ";
        }
        return rankString + "(" + rank.getWinningMoney() + "원)-" + count + "개";
    }

    private static void printRate(TreeMap<Rank, Integer> rankCount, int purchaseCount) {
        float sum = 0;

        for (Map.Entry<Rank, Integer> rankEntry : rankCount.entrySet()) {
            int winningMoney = rankEntry.getKey().getWinningMoney();
            int count = rankEntry.getValue();
            sum += (winningMoney * count);
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
