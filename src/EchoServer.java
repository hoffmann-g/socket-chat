import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer{

    private ServerSocket serverSocket;

    public EchoServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    private void startServer(){
        System.out.println("server starting...");
        try {
            Socket socket = serverSocket.accept();
            System.out.println("connected");

            ClientHandler clientHandler = new ClientHandler(socket);
        } catch (IOException e) {
            System.out.println("server is full");
        } 
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(1234);
        EchoServer server = new EchoServer(serverSocket);
        server.startServer();
        
    }
}