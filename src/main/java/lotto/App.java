package lotto;

import domain.Lotto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final int LOTTO_PRICE = 1000;
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Lotto> lottos = new ArrayList<>();
    private static int purchasingAmount;
    private static boolean status;

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
                status = checkPurchasingAmount(purchasingAmount = sc.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("입력은 정수로만 가능합니다.");
                sc.next(); // Scanner 버그 방지용 코드
            }
        } while (!status);
    }

    public static void initLottos() {
        for (int i = 0; i < purchasingAmount/LOTTO_PRICE; i++) {
            lottos.add(new Lotto(new ArrayList<>()));
        }
    }

    public static void printLottos(){
        System.out.println(purchasingAmount/LOTTO_PRICE + "개를 구매했습니다.");
        for(Lotto lotto : lottos){
            System.out.println(lotto.getNumbers().toString());
        }
    }

    public static void main(String[] args) {
        setPurchasingAmount();
        initLottos();
        printLottos();

    }
}
