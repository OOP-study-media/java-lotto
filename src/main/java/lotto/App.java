package lotto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static final int LOTTO_PRICE = 1000;
    private static final Scanner sc = new Scanner(System.in);
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
            }catch (InputMismatchException e ){
                System.out.println("입력은 정수로만 가능합니다.");
                sc.next(); // Scanner 버그 방지용 코드
            }
        } while (!status);
    }

    public static void main(String[] args) {
        setPurchasingAmount();
    }
}
