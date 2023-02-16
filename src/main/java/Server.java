import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8989)) {
            String city = "???";
            String newCity;
            while (true) {
                try (Socket socket = server.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    //System.out.println("Client connected.");   //checks connection
                    out.println(city);
                    newCity = in.readLine();
                    if (newCity.charAt(0) == city.charAt(city.length() - 1) || city.equals("???")) {
                        out.println("OK");
                        city = newCity;
                    } else {
                        out.println("NOT OK");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
