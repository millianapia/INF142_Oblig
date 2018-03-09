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
    static void getHTTP(DatagramPacket urlString) throws IOException {
        String website = new String(urlString.getData());
        try {
            URL url = new URL(website);
            String host = url.getHost();
            String path = url.getPath();
            int port = url.getPort();
            //Connect to website through socket
            Socket socket = new Socket(InetAddress.getByName(website), port);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String request = "GET " + path  + " HTTP/1.1\n";
            request += "Host: " + host;
            request += "\n\n";
            //println or print?
            pw.print(request);
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String text;
            while ((text = br.readLine()) != null) {
                //return text;
                System.out.println(text);
            }
            br.close();
            socket.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: fix sending back to client
    //UDP
    static void listenAndConnect() throws Exception {
        byte[] receiveData = new byte[1024];
        DatagramSocket dSocket = new DatagramSocket(9876);
        DatagramPacket dPacket = new DatagramPacket(receiveData, receiveData.length);
        while (true) {
            dSocket.receive(dPacket);
            getHTTP(dPacket);
            //byte[] sendData = sendBack.getBytes();
            //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
            dSocket.send(dPacket);
            //Do i need to close this?
            //dSocket.close();

        }

    }


}
