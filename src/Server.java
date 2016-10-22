import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(8880);
            Socket client;
            while ((client = socket.accept()) != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                String ln;
                while ((ln = reader.readLine()) != null) {
                    System.out.println("got: " + ln);
                    if (ln.equalsIgnoreCase("hello")) {
                        System.out.println("sending 'world'");
                        writer.write("world");
                        writer.newLine();
                        writer.flush();
                        client.close();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
