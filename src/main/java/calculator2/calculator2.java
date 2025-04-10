package calculator2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class calculator2 {
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
            return 0;
        }

        return sumNumbers(input, BASIC_ARITHMETIC_REGEX);
    }

    private static int sumNumbers(String input, String BASIC_ARITHMETIC_REGEX){
        String[] usedNumbers = usedNumbers(input);
        String[] usedBasicArithmetic = usedBasicArithmetic(input);

        int sum = Integer.parseInt(usedNumbers[0]);

        for(int i = 0; i < usedBasicArithmetic.length; i++){
            if(usedBasicArithmetic[i].equals("+")){
                sum = sum + Integer.parseInt(usedNumbers[i+1]);
            } else if ( usedBasicArithmetic[i].equals("-")){
                sum = sum - Integer.parseInt(usedNumbers[i+1]);
            } else if ( usedBasicArithmetic[i].equals("x")) {
                sum = sum*Integer.parseInt(usedNumbers[i + 1]);
            } else if ( usedBasicArithmetic[i].equals("/")) {
                if (Integer.parseInt(usedNumbers[i+1]) == 0){
                    throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
                }
                sum = sum/Integer.parseInt(usedNumbers[i + 1]);
            } else throw new IllegalArgumentException("재입력 바랍니다.");
        }

        return sum;
    }
    private static String[] usedNumbers(String input) {
        String[] usedNumbers = input.split(BASIC_ARITHMETIC_REGEX);

        for (String number : usedNumbers) {
            number = number.trim();
            if (number.isEmpty()) continue;

            if (!number.matches("\\d+")) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + number);
            }
        }

        return usedNumbers;
    }

    private static String[] usedBasicArithmetic(String input) {

        List<String> usedBasicArithmetic = new ArrayList<>();
        for (char ch : input.toCharArray()){
            if (BASIC_ARITHMETIC_REGEX.indexOf(ch) != INDEX_NOT_FOUND){
                usedBasicArithmetic.add(String.valueOf(ch));
            }
        }
        return usedBasicArithmetic.toArray(new String[0]);
    }

}
