import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private ServerSocket serverSocket;
    
    public EchoServer(ServerSocket ss){
        this.serverSocket = ss;
    }

    public void startServer(){
        System.out.println("Starting...");
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();

                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch(IOException e){
            closeServerSocket();
        }
    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(9999);
        EchoServer server = new EchoServer(serverSocket);
        server.startServer();
        
    }
}

