import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    final static int PORT = 8080;

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        Socket clienSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server start on port : "+ PORT);
            while (true){
                try {
                    clienSocket = serverSocket.accept();
                    http_protocol handle = new http_protocol(clienSocket);
                    handle.handleRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                clienSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
