import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.*;


class WPS {

    public static void main(String argv[]) {
        try {
            listenAndConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: add return statement so we can send it to the client
    //TCP
    static String getHTTP(DatagramPacket urlString) throws IOException {
        String website = new String(urlString.getData());
        String returnText ="";
        try {
            URL url = new URL(website);
            String host = url.getHost();
            String path = url.getPath();

            //Connect to website through socket
            Socket socket = new Socket(url.getHost(), 80);
            System.out.println(socket.toString());
           PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String request = "GET " + path  + " HTTP/1.1\r\n";
            request += "Host: " + host + "\r\n";
            request += "\r\n";
            //println or print?
            pw.print(request);
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String text;
            while ((text = br.readLine()) != null) {
                returnText += text;
                System.out.println(text);
            }
            br.close();
            socket.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnText;
    }

    //TODO: fix sending back to client
    //UDP
    static void listenAndConnect() throws Exception {
        byte[] receiveData = new byte[1024];
        DatagramSocket dSocket = new DatagramSocket(9876);
        DatagramPacket dPacket = new DatagramPacket(receiveData, receiveData.length);
        while (true) {
            dSocket.receive(dPacket);
            String sendBack = getHTTP(dPacket);
            byte[] sendData = sendBack.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
            dSocket.send(sendPacket);
            //Do i need to close this?

            dSocket.close();

        }

    }


}
