import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class StreamUpdateProfileProtocol {
    //  Maximum body size is 512bytes;
    private static final int DATA_SIZE = 1024;
    // Number of data is two;
    private static final int TOKEN_NUM = 5;

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

            updateProfile(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파싱한 데이터를 출력해주는 함수
    private void updateProfile(String[] params) {
        System.out.println("UpdateProfile -> " +
                " id :" + params[0] +
                " password : " + params[1] +
                " name : " + params[2] +
                " age : " + params[3] +
                " gender : " + params[4]);
    }
}
