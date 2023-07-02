package xb.note.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import xb.note.grpc.HelloServiceGrpc;
import xb.note.grpc.HelloResponse;
import xb.note.grpc.HelloRequest;

import java.util.concurrent.ExecutionException;

public class HelloServiceClient {

    private ManagedChannel managedChannel;
    private HelloServiceGrpc.HelloServiceBlockingStub blockingStub;
    private HelloServiceGrpc.HelloServiceFutureStub asyncStub;

    public HelloServiceClient(String host, int port){
        managedChannel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = HelloServiceGrpc.newBlockingStub(managedChannel);
        asyncStub = HelloServiceGrpc.newFutureStub(managedChannel);
    }

    public HelloResponse sayHelloWithBlocking(String message){
        HelloRequest request = buildRequest(message);
        return blockingStub.sayHello(request);
    }

    private HelloRequest buildRequest(String message){
        return HelloRequest.newBuilder()
                .setRequest(message)
                .build();
    }

}
