package io.github.tsoihim;

import io.github.tsoihim.grpc.hello.HelloRequest;
import io.github.tsoihim.grpc.hello.HelloResponse;
import io.github.tsoihim.grpc.hello.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request,
                         StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        System.out.println("[gRPC Service] 요청 수신. Name: " + name);

        String greeting = "Hello, " + name + "! Welcome to Armeria gRPC service.";

        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(greeting)
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}