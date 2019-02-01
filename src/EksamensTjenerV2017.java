
import java.io.*;
import java.net.*;
public class EksamensTjenerV2017 {
    public static void main( String args[] )
    {
        ServerSocket server;
        Socket connection;
        int counter = 1;
        ObjectOutputStream output;
        try {
            server = new ServerSocket( 5194, 100 );
            while ( true ) {
                connection = server.accept();
                System.out.println( "Connection " + counter +
                        " received from: " + connection.getInetAddress().getHostName() );
                output = new ObjectOutputStream(connection.getOutputStream() );
                String message ="Antall forbindelser:" + counter;
                output.writeObject( message );
                output.flush();
                System.out.println( "\nTerminated connection" );
                // connection.close();
                ++counter;
            }
        }
        catch ( EOFException eof ) {
            System.out.println( "Client terminated connection" );
        }
        catch ( IOException io ) {
            io.printStackTrace();
        }
    }
}