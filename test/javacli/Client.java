package javacli;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static class TcpRequest {

        public String service;
        public double payload;
    }

    public static void test() throws IOException, ClassNotFoundException {
        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        socket = new Socket("localhost", 9001);

        in = new ObjectInputStream(socket.getInputStream());

        //out = new ObjectOutputStream(socket.getOutputStream());
        out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        long throughput;
        long millis;

        TcpRequest hello = null;


        throughput = 0;
        millis = System.currentTimeMillis();
        while (System.currentTimeMillis() < millis + 1000) {
            hello = new TcpRequest();
            hello.service = "hello";
            hello.payload = Math.random();
            throughput++;
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("|        Objects created: " + (throughput) + " requests/sec.");
        System.out.println("-------------------------------------------------------");


        throughput = 0;
        millis = System.currentTimeMillis();
        while (System.currentTimeMillis() < millis + 1000) {
            out.writeObject(hello);
            Object res = in.readObject();
            throughput++;
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("|        Same object throughput: " + (throughput) + " requests/sec.");
        System.out.println("-------------------------------------------------------");


        throughput = 0;
        millis = System.currentTimeMillis();
        while (System.currentTimeMillis() < millis + 1000) {
            hello = new TcpRequest();
            out.writeObject(hello);
            Object res = in.readObject();
            throughput++;
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("|        New objetcs throughput: " + (throughput) + " requests/sec.");
        System.out.println("-------------------------------------------------------");


        out.close();
        in.close();

        socket.close();
    }

}
