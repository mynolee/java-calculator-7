package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Application {

    private static final String DEFAULT_DELIMITERS_REGEX = "[,:]";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String NEWLINE_TOKEN = "\\n";
    private static final int INDEX_NOT_FOUND = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("입력하세요: ");
            String input = br.readLine();

            try {
                int sum = calculate(input);
                System.out.println("합계: " + sum);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 입력입니다: " + e.getMessage());
                System.out.println("다시 입력해주세요.\n");
            }
        }
    }

    public static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiterRegex = getDelimiter(input);
        String numbers = extractNumbers(input);

        return sumNumbers(numbers, delimiterRegex);
    }

    private static String getDelimiter(String input) {
        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int delimiterEnd = input.indexOf(NEWLINE_TOKEN);
            if (delimiterEnd == INDEX_NOT_FOUND) {
                throw new IllegalArgumentException("입력에서 " + NEWLINE_TOKEN + "을 찾을 수 없습니다.");
            }

            String customDelimiter = input.substring(CUSTOM_DELIMITER_PREFIX.length(), delimiterEnd);
            return Pattern.quote(customDelimiter);
        }

        return DEFAULT_DELIMITERS_REGEX;
    }

    private static String extractNumbers(String input) {
        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int delimiterEnd = input.indexOf(NEWLINE_TOKEN);
            return input.substring(delimiterEnd + NEWLINE_TOKEN.length());
        }

        return input;
    }

    private static int sumNumbers(String numbers, String delimiterRegex) {
        String[] tokens = numbers.split(delimiterRegex);
        int sum = 0;

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) continue;

            if (!token.matches("\\d+")) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
            }

            sum += Integer.parseInt(token);
        }

        return sum;
    }
}