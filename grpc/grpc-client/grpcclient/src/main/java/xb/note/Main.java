package xb.note;

import xb.note.client.HelloServiceClient;

public class Main {
    public static void main(String[] args) {
        HelloServiceClient helloServiceClient = new HelloServiceClient("localhost", 4321);
        System.out.println(helloServiceClient.sayHelloWithBlocking("hello world").getResponse());
    }

}