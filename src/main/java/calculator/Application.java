package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        char[] arr = new char[str.length()];
        char[] cus = new char[];


        for(int i = 0; i < str.length(); i++){
            arr[i] = str.charAt(i);
        }
        if(arr[0] == "/"){
            for(int i = 2; arr[i]=="\\";i++){
                cus[i-2] = arr[i];
            }

            StringTokenizer stn = new StringTokenizer(str, "cus[0:i-2]");
        }

        int sum = 0;
        StringTokenizer st = new StringTokenizer(str, ",|:| ");
        int count = st.countTokens();
        // for문 count 자리에 st.countTokens() 쓰면 안되는 이유는
        // st.nextToken()에서 하나씩 빼서 쓰므로 st.countTokens는 남아있는 토큰수를 셈으로 계속 줆
        // for문 반복횟수에 문제 생김.
        for (int i = 0; i <count; i++){
            sum += Integer.parseInt(st.nextToken());
        }
        System.out.println(sum);








    }
}

class baseGubun{
    String a = ",";
    String b = ":";
}

class customGubun{

}
