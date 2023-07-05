import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args){
        try{
            System.out.println("[Client]: Started.");

            //socket
            Socket socket = new Socket("localhost", 1720);

            //creates a stream to send the bytes
            OutputStream outputStream = socket.getOutputStream();
            //writes an object to a bytestream
            ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("[CLIENT]: Enter a string: ");
            String str = userInput.readLine();

            //writes an object to the objOutputStream
            objOutputStream.writeObject(str);
            //forces it to send the stream
            objOutputStream.flush();

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
