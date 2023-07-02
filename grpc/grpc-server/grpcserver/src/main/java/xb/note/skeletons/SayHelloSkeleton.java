package xb.note.skeletons;

import xb.note.grpc.HelloServiceGrpc;
import xb.note.grpc.HelloRequest;
import xb.note.grpc.HelloResponse;

import io.grpc.stub.StreamObserver;

public class SayHelloSkeleton extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request,
                         StreamObserver<HelloResponse> responseObserver) {
        String requestString = request.getRequest();
        HelloResponse helloResponse = HelloResponse.newBuilder()
                .setResponse("answer : " + requestString)
                .build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }

}
