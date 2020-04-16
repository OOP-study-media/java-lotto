package domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Program {
    private static final int PRICE = 1000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int purchaseCount = inputPrice() / PRICE;
    }

    private static int inputPrice() throws Exception {
        return inputInt("구입금액을 입력해 주세요.");
    }

    private static int inputInt(String print) throws Exception {
        System.out.println(print);
        return Integer.parseInt(br.readLine());
    }

}
