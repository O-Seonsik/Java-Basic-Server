import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInitializer {
    public static void main(String[] args){
        int port = 5000;
        System.out.println("Server ON : " + port);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket connection;
            while(true) {
                connection = serverSocket.accept();
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();

                System.out.println("READ:" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
