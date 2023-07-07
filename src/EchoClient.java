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

    public EchoClient(Socket socket){
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            sendUsername();

        } catch (IOException e) {
            closeAll(socket, in, out);
        }
    }

    public void sendUsername(){
        Scanner input = new Scanner(System.in);
        String user;
        try {
            System.out.print("Enter a username: ");
            user = input.nextLine();
            out.write(user);
            out.newLine();
            out.flush();
        } catch (IOException e) {
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
        try{
            Scanner input = new Scanner(System.in);
            String message;
            while(socket.isConnected()){
                message = input.nextLine();
                out.write(message);
                out.newLine();
                out.flush();
            }
        } catch (IOException e){

        }
    }

    public void listen(){
        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    while(socket.isConnected()){
                        String message = in.readLine();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                }
            }
        });

        thread.start();
    }
    
    public static void main(String[] args){
        Socket socket;
        try {
            socket = new Socket("localhost", 1234);
            EchoClient client = new EchoClient(socket);
            client.listen();
            client.sendMessages();
            
        } catch (ConnectException e) {
            System.out.println("connection refused");
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
}