package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Application {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        try {
            int sum = calculate(input);
            System.out.println(sum);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력입니다: " + e.getMessage());
        }
    }
    public static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delim = ",:";
        String numbers = input;

        if (input.startsWith("//")) {
            int delimEnd = input.indexOf("\\n");
            if (delimEnd == -1) {
                throw new IllegalArgumentException("입력에서 \\n을 찾을 수 없습니다.");
            }

            delim = input.substring(2, delimEnd);
            numbers = input.substring(delimEnd + 2);
        }

        StringTokenizer tokenizer = new StringTokenizer(numbers, delim);
        int sum = 0;

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            if (token.isEmpty()) {
                continue;
            }

            if (!token.matches("\\d+")) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
            }

            sum += Integer.parseInt(token);
        }

        return sum;
    }
}
