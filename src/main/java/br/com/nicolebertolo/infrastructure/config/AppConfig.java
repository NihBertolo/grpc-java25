package br.com.nicolebertolo.infrastructure.config;

import br.com.nicolebertolo.application.port.inbound.ListingUseCase;
import br.com.nicolebertolo.infrastructure.adapter.inbound.grpc.ListingGrpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ListingGrpc listingGrpc(ListingUseCase listingUseCase) {
        return new ListingGrpc(listingUseCase);
    }


}
