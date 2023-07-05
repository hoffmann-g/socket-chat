import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args){
        try{
            System.out.println("server starting...");

            ServerSocket server = new ServerSocket(1720);
            Socket clientSocket = server.accept();
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);

            System.out.println("connection established");

            String receivedStr = (String) objInputStream.readObject();

            System.out.println(receivedStr.toUpperCase());

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
