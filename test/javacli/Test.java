package javacli;

import javacli.LocalSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    private static int port = 9001;

    public static void main(String[] args) throws IOException, InterruptedException {
        boolean javaSocket = args.length == 0;
        startClient(javaSocket);

        ServerSocket server = new ServerSocket(port);
        Socket client = server.accept();
        client.setTcpNoDelay(true);
        DataInputStream in = new DataInputStream((client.getInputStream()));

        //DataOutputStream out = new DataOutputStream( (client.getOutputStream()));
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));

        for (int i = 0; i < 300_000; i++) {
            out.writeInt(i);
            out.flush();
            if (i != in.readInt()) {
                System.out.println("ERROR");
            }
        }

        client.close();
        server.close();
        Thread.sleep(1000);
    }

    public static void startClient(boolean javaSocket) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                Socket client;

                if (javaSocket)
                    client = new Socket("127.0.0.1", port);
                else
                    client = new LocalSocket("127.0.0.1", port);

                System.out.println("impl: " + client.getClass().getCanonicalName());

                client.setTcpNoDelay(true);

                //DataOutputStream out = new DataOutputStream( (client.getOutputStream()));
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));

                DataInputStream in = new DataInputStream((client.getInputStream()));
                long tm1 = System.currentTimeMillis();
                int lastCount = 0;
                for (int i = 0; i < 300_000; i++) {
                    int a = in.readInt();
                    out.writeInt(a);
                    out.flush();
                    long tm2 = System.currentTimeMillis();
                    if ((tm2 - tm1) >= 1000) {
                        System.out.println((i - lastCount) + " requests/sec.");
                        lastCount = i;
                        tm1 += 1000;
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}