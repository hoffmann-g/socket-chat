import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient{

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String username;

    public EchoClient(Socket socket, String username){
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.username = username;

        } catch (IOException e) {
            closeAll(socket, in, out);
        }
    }

    public void closeAll(Socket s, BufferedReader br, BufferedWriter bw){
        try{
            if (s != null){
                s.close();
            }
            if (br != null){
                br.close();
            }
            if (bw != null){
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessages(){
        Scanner input = new Scanner(System.in);
        try{
            out.write(username);
            out.newLine();
            out.flush();

            while(socket.isConnected()){
                String message = input.nextLine();
                out.write(message);
                out.newLine();
                out.flush();
            }
        } catch (IOException e){
            closeAll(socket, in, out);
            input.close();
        }
    }

    public void listen(){
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    while(socket.isConnected()){
                        String message = in.readLine();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    closeAll(socket, in, out);
                }
            }
        }).start();
    }
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter a username: ");
            String user = input.nextLine();

            Socket socket = new Socket("localhost", 1234);
            EchoClient client = new EchoClient(socket, user);

            client.listen();
            client.sendMessages();
        } catch (Exception e) {
            System.out.println("connection refused");
            input.close();
        }
        

    }
}