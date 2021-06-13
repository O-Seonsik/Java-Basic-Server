import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class StreamSayHelloProtocol {
    //  Maximum body size is 512bytes;
    private static final int DATA_SIZE = 512;
    // Number of data is two;
    private static final int TOKEN_NUM = 2;

    // InputStream 데이터를 처리해줄 함수
    public void handleEvent(InputStream inputStream){
        try {
            byte[] buffer = new byte[DATA_SIZE];
            inputStream.read(buffer);
            String data = new String(buffer);

            String[] params = new String[TOKEN_NUM];
            StringTokenizer token = new StringTokenizer(data, "|");

            int i = 0;
            while (token.hasMoreTokens()) params[i++] = token.nextToken();

            sayHello(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파싱한 데이터를 출력해주는 함수
    private void sayHello(String[] params) {
        System.out.println("SayHello -> name : " + params[0] + " age : " + params[1]);
    }
}
