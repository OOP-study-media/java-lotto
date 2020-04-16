package domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final int PRICE = 1000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int purchaseCount = inputPrice() / PRICE;
        List<Lotto> lottoList = setupLottoList(purchaseCount);
        printLottoList(lottoList);
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

}
