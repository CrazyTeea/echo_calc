import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoServer {

    private static int pr(PrintWriter s , int s2){
        s.println(s2);
        return s2;
    }

    public static void main(String[] args) throws InterruptedException {
        String hostName = "localhost";
        int portNumber = 8080;

        try (
                Socket echoSocket = new ServerSocket( portNumber).accept();
                PrintWriter response = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                String[] reqParse = request.split(" ");
                int a = Integer.parseInt(reqParse[0]);
                int b = Integer.parseInt(reqParse[2]);
                switch (reqParse[1]) {
                    case "+" -> System.out.println(pr(response,a + b));
                    case "-" -> System.out.println(pr(response,a - b));
                    case "/" -> System.out.println(pr(response,a / b));
                    case "*" -> System.out.println(pr(response,a * b));
                    default -> response.write("Не верный запрос");
                }

            }
            echoSocket.close();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }

    }
}
