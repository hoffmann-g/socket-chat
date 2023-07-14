import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    private Socket socket;
    private String username;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public EchoClient(Socket socket, String username){
        try{
            this.socket = socket;
            this.username = username;
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        
    }

    public void sendMessage(){
        Scanner input = new Scanner(System.in);
        try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            while(socket.isConnected()){
                String message = input.nextLine();
                bufferedWriter.write(username + ": " +  message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
            input.close();
        }
    }

    public void listen(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                String chatMessage;

                while(socket.isConnected()){
                    try{
                        chatMessage = bufferedReader.readLine();
                        System.out.println(chatMessage);
                    } catch (IOException e){
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();        
    }

    public void closeEverything(Socket s, BufferedReader r, BufferedWriter w){
        try{
            if(s != null){
                s.close();
            }
            if(r != null){
                s.close();
            }
            if(w != null){
                w.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{

        try (Scanner input = new Scanner(System.in)) {
            System.out.print("[CLIENT]: Enter your username: ");
            String username = input.next();

            Socket socket = new Socket("179.152.25.60", 55555);
            EchoClient client = new EchoClient(socket, username);
            client.listen();
            client.sendMessage();
        }

    }
}
