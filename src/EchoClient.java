import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient{

    private Socket socket;

    public EchoClient(Socket socket){
        this.socket = socket;
    }
    
    public static void main(String[] args){
        Socket socket;
        try {
            socket = new Socket("localhost", 1234);
            EchoClient client = new EchoClient(socket);
        } catch (ConnectException e) {
            System.out.println("the server is full");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}