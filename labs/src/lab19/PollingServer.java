package lab19;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Scanner;

public class PollingServer {
    public static void main(String[] args) {
        final String QUIT_COMMAND = "QUIT";

        int PORT;
        if (args.length > 0) {
            PORT = Integer.parseInt(args[0]);
        } else {
            PORT = 8001;
        }
        System.out.println("lab19.Server Started on port: [" + PORT + "]");

        ServerSocket serverSocket;
        Socket clientConnection = null;
        try {
            serverSocket = new ServerSocket(PORT);
            clientConnection = serverSocket.accept();

            Scanner in = new Scanner(clientConnection.getInputStream());

            // Note: PrintWriter autoflush enabled
            PrintWriter out = new PrintWriter( 
                new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream())), true);

            while (true) {
                final String clientMessage = in.nextLine();

                if (clientMessage.equalsIgnoreCase(QUIT_COMMAND)){
                    System.out.println("!! lab19.Server QUITTING: Received [" + QUIT_COMMAND + "] Command");
                    break;
                }

                System.out.println("<< lab19.Server Received lab19.Client Message: [" + clientMessage + "]");
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter a Reply to the lab19.Client's Message");

                final String reply = scanner.nextLine();
                // Get the Reply from the user
                out.println(reply);
                out.flush();
                System.out.println(">> lab19.Server Sent Message: [" + reply + "] to lab19.Client at " + Calendar.getInstance().getTime());
                System.out.println("Waiting for a Message:");

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientConnection != null) {
                try {
                    clientConnection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
