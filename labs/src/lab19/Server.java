package lab19;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        int PORT;
        //if (args.length > 0) {
        //    PORT = Integer.parseInt(args[0]);
        //} else {
            PORT = 8001;
        //}
        System.out.println("lab19.Server Started on port: [" + PORT + "]");

        ServerSocket serverSocket;
        Socket clientConnection = null;
        try {
            serverSocket = new ServerSocket(8001);
            clientConnection = serverSocket.accept();
            Scanner in = new Scanner(clientConnection.getInputStream());

            // Note: PrintWriter autoflush enabled
            PrintWriter out = new PrintWriter( 
                new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream())), true);

            String message = in.nextLine();

            System.out.println("lab19.Server Received Message: [" + message + "]");

            // reply
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a reply to the Message");

            // Get the Reply from the user
            String reply = scanner.nextLine();
            out.println(reply);
            //out.println("Okay from the server");

            out.flush();
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
