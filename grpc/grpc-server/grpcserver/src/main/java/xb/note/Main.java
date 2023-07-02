package xb.note;

import xb.note.servers.HelloServiceServer;

public class Main {

    public static void main(String[] args) {
        HelloServiceServer helloServiceServer = new HelloServiceServer(4321);
        helloServiceServer.start();
    }
}