import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args){
        try{
            System.out.println("[SERVER]: Starting...");
            //sockets
            ServerSocket server = new ServerSocket(9999);
            Socket toClientSocket = server.accept();

            //creates an obj to recieve the bytestream from the client
            InputStream inputStream = toClientSocket.getInputStream();
            //converts the bytestream to a readable obj
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);

            //reads the inputStream and allocates in a variable
            String receivedStr = (String) objInputStream.readObject();

            System.out.println(receivedStr.toUpperCase());

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
