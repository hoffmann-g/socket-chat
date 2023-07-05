import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EchoClient {
    
    public static void main(String[] args){
        try{
            System.out.println("Client started");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a number: ");
            String str = userInput.readLine();

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
