package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Application {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int sum = 0;
        StringTokenizer st = new StringTokenizer(str, ",|:");
        for (int i = 0; i <st.countTokens(); i++){
            sum += Integer.parseInt(st.nextToken());
        }








    }
}

class baseGubun{
    String a = ",";
    String b = ":";
}

class customGubun{

}
