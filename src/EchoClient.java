import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args){
        try{
            System.out.println("Client started");

            Socket socket = new Socket("localhost", 1720);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a string: ");
            String str = userInput.readLine();

            objOutputStream.writeObject(str);
            objOutputStream.flush();

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
