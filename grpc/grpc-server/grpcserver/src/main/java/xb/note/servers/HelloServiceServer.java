package xb.note.servers;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import xb.note.skeletons.SayHelloSkeleton;

import java.io.IOException;

public class HelloServiceServer {

    private Server helloServer;

    public HelloServiceServer(int port){
        helloServer = ServerBuilder.forPort(port).addService(new SayHelloSkeleton()).build();
    }

    public void start(){
        try {
            helloServer.start();
            System.out.println("Server started on : " + helloServer.getPort());
            helloServer.awaitTermination();
        }catch(IOException IOE){IOE.printStackTrace();}
        catch(InterruptedException IE){IE.printStackTrace();}
    }

}
