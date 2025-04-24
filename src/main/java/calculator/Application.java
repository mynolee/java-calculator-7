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
                int result = controlCalculation(input);
                System.out.println("계산 결과: " + result);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 입력입니다: " + e.getMessage());
                System.out.println("다시 입력해주세요.\n");
            }
        }
    }

    public static int controlCalculation(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력이 비어 있습니다. 재입력 바랍니다.");
        }

        return resultNumbers(input, BASIC_ARITHMETIC_REGEX);
    }

    private static int resultNumbers(String input, String BASIC_ARITHMETIC_REGEX) {
        int[] extractNumbers = extractNumbers(input);
        String[] extractBasicArithmetic = extractBasicArithmetic(input);

        int result = extractNumbers[0];

        for (int i = 0; i < extractBasicArithmetic.length; i++) {
            switch (extractBasicArithmetic[i]) {
                case "+":
                    result = result + extractNumbers[i + 1];
                    break;
                case "-":
                    result = result - extractNumbers[i + 1];
                    break;
                case "x":
                    result = result * extractNumbers[i + 1];
                    break;
                case "/":
                    if (extractNumbers[i + 1] == 0) {
                        throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
                    }
                    result = result / extractNumbers[i + 1];
                    break;
                default:
                    throw new IllegalArgumentException("재입력 바랍니다.");
            }
        }

        return result;
    }

    private static int[] extractNumbers(String input) {
        String[] extractNumbers = input.split(BASIC_ARITHMETIC_REGEX);
        int[] Numbers = new int[0];

        for (String number : extractNumbers) {
            if (shouldSkip(number)) continue;

            if (!number.matches("\\d+")) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + number);
            }
        }

        for (int i = 0; i<extractNumbers.length;i++){
            Numbers[i] = Integer.parseInt(extractNumbers[i]);
        }
        return Numbers;
    }

    private static String[] extractBasicArithmetic(String input) {

        List<String> extractBasicArithmetic = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            if (BASIC_ARITHMETIC_REGEX.indexOf(ch) != INDEX_NOT_FOUND) {
                extractBasicArithmetic.add(String.valueOf(ch));
            }
        }
        return extractBasicArithmetic.toArray(new String[0]);
    }


    public static boolean shouldSkip(String number) {
        return number == null || number.trim().isEmpty();
    }
}
