import java.io.*;
import java.net.*;
import java.util.Scanner;


public class klient {

    public static void main(String args[]){
        InputStream input;
        InputStreamReader reader;
        Scanner inn;
        try (Socket socket = new Socket("localhost", 5194)) {
            input = socket.getInputStream();
            reader = new InputStreamReader(input);
             inn = new Scanner(reader);
            String inputText  = inn.nextLine();
            System.out.println(inputText);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
