import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer{

    private ServerSocket serverSocket;

    public EchoServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    private void allowConnections(){
        try {
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (IOException e) {
            
        }
    }

    public static void main(String[] args) {
        System.out.println("server starting...");
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            EchoServer server = new EchoServer(serverSocket);
            System.out.println("server started.");

            server.allowConnections();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}