import java.net.*;
import java.util.Scanner;

//TODO: Remove link underneath
//https://www.google.no

class K {
    public static void main(String args[]) throws Exception {
        try {
            //TODO: find out why "/" makes it crash
            Scanner inn = new Scanner(System.in);
            System.out.println("Enter address: ");
            String address = inn.nextLine();
            System.out.println("Are you going to send path? (y/n): ");
            String answer = inn.nextLine();
            String path = "/";
            if (answer.equals("y")) {
                System.out.println("Enter path:");
                path = inn.nextLine();
            }
            address = address + path;
            inn.close();


            // Creates socket and gets ip from address
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");


            //Prep for sending and receiving
            byte[] sendData = address.getBytes();
            byte[] receiveData = new byte[1024];

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);


            //Received from WPS ----------------------------------------------------------------
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String header = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + header);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
