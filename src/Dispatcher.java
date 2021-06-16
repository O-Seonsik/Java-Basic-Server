import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher {
    private final int HEADER_SIZE = 6;

    public void dispatch(ServerSocket serverSocket, HandleMap handleMap){
        try {
            Socket socket = serverSocket.accept();
            demultiplex(socket, handleMap);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // 받은 데이터를 분배해 주는 것이 demux
    // 연결된 접속으로부터 데이터를 읽을 수 있도록 한다.
    public void demultiplex(Socket socket, HandleMap handleMap) {
        try {
            InputStream inputStream = socket.getInputStream();

            byte[] buffer = new byte[HEADER_SIZE];
            inputStream.read(buffer);
            String header = new String(buffer);

           handleMap.get(header).handleEvent(inputStream);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
