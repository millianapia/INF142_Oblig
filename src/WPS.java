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
            DatagramPacket dpacket =  listenAndConnect();
            getHTTP(dpacket);

            ServerSocket welcomeSocket = new ServerSocket(6789);


        }

    //TODO: Add return statement
        //TCP
        static void getHTTP(DatagramPacket urlString) throws Exception{
            String address = new String(urlString.getData());
            Socket sock = new Socket(address, 80);
            InetAddress addr = sock.getInetAddress();
            System.out.println("Connected to " + addr);
            System.out.println(addr.getHostName());
            System.out.println(addr.getHostAddress());
            sock.close();
        }

        //UDP
        static DatagramPacket listenAndConnect()throws Exception{
            byte[] receiveData = new byte[1024];
            DatagramSocket dSocket = new DatagramSocket(9876);
            DatagramPacket dpacket = new DatagramPacket(receiveData, receiveData.length);
            dSocket.receive(dpacket);
            return dpacket;
        }

        //UDP
        static void sendHTTP(){

        }

    }
