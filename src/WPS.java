
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

    //TCP - Request header from given site
    public static String getHTTP(DatagramPacket urlString) {
        String website = new String(urlString.getData());
        String returnText ="";
        try {
            URL url = new URL(website);
            String host = url.getHost();
            String path = url.getPath();

            //Connect to website through socket
            Socket socket = new Socket(url.getHost(), 80);
             PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String request = "GET " + path  + " HTTP/1.1\r\n";
            request += "Host: " + host + "\r\n";
            request += "\r\n";
            pw.print(request);
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String text;
            while ((text = br.readLine()) != null) {
                if (!text.trim().isEmpty())
                    returnText += text + "\r\n";
                else break;
            }
            br.close();
            socket.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnText;
    }

    //UDP - Receive and send through UDP
    public static void listenAndConnect() {
        try {
            String sendBack;
            byte[] receiveData = new byte[1024];
            DatagramSocket dSocket = new DatagramSocket(9876);
            DatagramPacket dPacket = new DatagramPacket(receiveData, receiveData.length);
            while (true) {
                dSocket.receive(dPacket);
                sendBack = getHTTP(dPacket);
                InetAddress IPAddress = dPacket.getAddress();
                int port = dPacket.getPort();
                byte[] sendData = sendBack.getBytes();
                DatagramPacket sPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                dSocket.send(sPacket);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


}
