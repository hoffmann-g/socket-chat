import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args){
        try{
            System.out.println("[SERVER]: Starting...");
            //sockets
            ServerSocket server = new ServerSocket(1720);
            Socket clientSocket = server.accept();

            //receives bytes from client
            InputStream inputStream = clientSocket.getInputStream();
            //converts the bytes to a readable format
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);

            //reads the inputStream and allocates in a variable
            String receivedStr = (String) objInputStream.readObject();

            System.out.println(receivedStr.toUpperCase());

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
