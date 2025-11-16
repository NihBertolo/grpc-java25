package br.com.nicolebertolo;

import br.com.nicolebertolo.infrastructure.adapter.inbound.grpc.ListingGrpc;
import br.com.nicolebertolo.proto.listing.ListingServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        // pega a implementação correta anotada como @Service no Spring
        ListingServiceGrpc.ListingServiceImplBase listingService =
                context.getBean(ListingGrpc.class);

        Server server = ServerBuilder
                .forPort(50051)
                .addService(listingService) // <-- agora está correto
                .addService(ProtoReflectionService.newInstance())
                .executor(Executors.newVirtualThreadPerTaskExecutor())
                .build()
                .start();

        System.out.println("gRPC Server iniciado na porta 50051");

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        server.awaitTermination();
    }
}

