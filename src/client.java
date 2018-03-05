import java.io.*;
import java.net.*;

//Implementér klienten K i Java. Kommunikasjon mellom K og WPS skal foregå ved hjelp av UDP. K skal skrive ut
// (på standard utputt) alle innkommende meldinger fra WPS.

    class client {
        public static void main(String args[]) throws Exception
        {

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("hostname");

            //Prep for sending and receiving
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            //TODO: fix so user can write in the address and port themselves
            //Sending to WPS--------------------------------------------------------------------
            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);


            //Received from WPS ----------------------------------------------------------------
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
            clientSocket.close();
        }



    }
