import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket clientSocket = null;
        try {
            final String SERVER_NAME = "localhost";
            int PORT;
            if (args.length > 0) {
                PORT = Integer.parseInt(args[0]);
            } else {
                PORT = 8001;
            }

            clientSocket = new Socket(SERVER_NAME, PORT);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            // Note: PrintWriter autoflush enabled
            PrintWriter out = new PrintWriter(
                    new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream())), true);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a Message:");
            //String outMessage = "Hello from the client"; // Hard Coded
            final String outMessage = scanner.nextLine();
            System.out.println("Sending message [" + outMessage + "] to server[" + SERVER_NAME +"] on port[" + PORT + "]");

            out.println(outMessage);
            out.flush();

            final String inMessage = in.readLine();
            System.out.println("Received Message " + inMessage + " from Server");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
