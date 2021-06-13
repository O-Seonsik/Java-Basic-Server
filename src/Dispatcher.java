import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher {
    private final int HEADER_SIZE = 6;

    public void dispatch(ServerSocket serverSocket){
        try {
            Socket socket = serverSocket.accept();
            demultiplex(socket);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // 받은 데이터를 분배해 주는 것이 demux
    // 연결된 접속으로부터 데이터를 읽을 수 있도록 한다.
    public void demultiplex(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();

            byte[] buffer = new byte[HEADER_SIZE];
            inputStream.read(buffer);
            String header = new String(buffer);

            switch(header) {
                case "0x5001":
                    StreamSayHelloProtocol sayHelloProtocol = new StreamSayHelloProtocol();
                    sayHelloProtocol.handleEvent(inputStream);
                    break;
                case "0x6001":
                    StreamUpdateProfileProtocol updateProfileProtocol = new StreamUpdateProfileProtocol();
                    updateProfileProtocol.handleEvent(inputStream);
                    break;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
