import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
    class WPS {

//Implementér WPS i Java. (Det finnes forskjellige mekanismer for kommunikasjon mellom en klient og en http-server,
// men dere skal eksplisitt bruke utveksling av http-meldinger over TCP-socketer.

        public static void main(String argv[]) throws Exception
        {
            ServerSocket welcomeSocket = new ServerSocket(6789);
            while(true) {
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println( "Connection received from: " + connectionSocket.getInetAddress().getHostName() );
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                String clientSentence = inFromClient.readLine();
                System.out.println( "Data received: " +clientSentence );
                getHTTP(clientSentence);
                String capitalizedSentence = clientSentence.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
            }
        }

    //TODO: Add return statement
        static void getHTTP(String urlString) throws Exception{
            Socket sock = new Socket(urlString, 80);
            InetAddress addr = sock.getInetAddress();
            System.out.println("Connected to " + addr);
            System.out.println(addr.getHostName());
            System.out.println(addr.getHostAddress());
            sock.close();
        }

    }
