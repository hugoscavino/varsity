package lab19;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.Scanner;

public class PollingClient {
    public static void main(String[] args) {
        Socket clientSocket = null;
        try {
            final String SERVER_NAME = "localhost";
            final String QUIT_COMMAND = "QUIT";
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

            while (true) {
                System.out.println("Enter a Message:");
                String endUserMsg = scanner.nextLine();
                System.out.println(">> Sent lab19.Server Message [" + endUserMsg + "] to server[" + SERVER_NAME +"] on port[" + PORT + "] at " + Calendar.getInstance().getTime());;
                // Send the message to the server
                out.println(endUserMsg);
                out.flush();

                if (endUserMsg.equalsIgnoreCase(QUIT_COMMAND)){
                    break;
                }

                final String inMessage = in.readLine();
                System.out.println("<< Received lab19.Server Message [" + inMessage + "] from lab19.Server");
            }


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
