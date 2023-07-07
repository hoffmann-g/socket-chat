import java.io.IOException;
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

            ClientHandler clientHandler = new ClientHandler(socket);
            
            /*Thread thread = new Thread(clientHandler);
            thread.start();*/

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