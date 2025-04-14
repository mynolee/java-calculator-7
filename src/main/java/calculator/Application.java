package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Application {
    private static final String BASIC_ARITHMETIC_REGEX = "[+\\-x/]";
    private static final int INDEX_NOT_FOUND = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("입력하세요: ");
            String input = br.readLine();

            try {
                int sum = controlCalculation(input);
                System.out.println("계산 결과: " + sum);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 입력입니다: " + e.getMessage());
                System.out.println("다시 입력해주세요.\n");
            }
        }
    }

    public static int controlCalculation(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("재입력 바랍니다.");
        }

        return sumNumbers(input, BASIC_ARITHMETIC_REGEX);
    }

    private static int sumNumbers(String input, String BASIC_ARITHMETIC_REGEX) {
        String[] useNumbers = useNumbers(input);
        String[] useBasicArithmetic = useBasicArithmetic(input);

        int sum = Integer.parseInt(useNumbers[0]);

        for (int i = 0; i < useBasicArithmetic.length; i++) {
            if (useBasicArithmetic[i].equals("+")) {
                sum = sum + Integer.parseInt(useNumbers[i + 1]);
            } else if (useBasicArithmetic[i].equals("-")) {
                sum = sum - Integer.parseInt(useNumbers[i + 1]);
            } else if (useBasicArithmetic[i].equals("x")) {
                sum = sum * Integer.parseInt(useNumbers[i + 1]);
            } else if (useBasicArithmetic[i].equals("/")) {
                if (Integer.parseInt(useNumbers[i + 1]) == 0) {
                    throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
                }
                sum = sum / Integer.parseInt(useNumbers[i + 1]);
            } else throw new IllegalArgumentException("재입력 바랍니다.");
        }

        return sum;
    }

    private static String[] useNumbers(String input) {
        String[] useNumbers = input.split(BASIC_ARITHMETIC_REGEX);

        for (String number : useNumbers) {
            number = number.trim();
            if (number.isEmpty()) continue;

            if (!number.matches("\\d+")) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + number);
            }
        }

        return useNumbers;
    }

    private static String[] useBasicArithmetic(String input) {

        List<String> useBasicArithmetic = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            if (BASIC_ARITHMETIC_REGEX.indexOf(ch) != INDEX_NOT_FOUND) {
                useBasicArithmetic.add(String.valueOf(ch));
            }
        }
        return useBasicArithmetic.toArray(new String[0]);
    }

}
