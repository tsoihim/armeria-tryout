package io.github.tsoihim;

import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.server.grpc.GrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linecorp.armeria.client.ClientFactory;
import com.linecorp.armeria.client.circuitbreaker.CircuitBreakerClient;
import com.linecorp.armeria.client.circuitbreaker.CircuitBreakerRule;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.logging.AccessLogWriter;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import com.linecorp.armeria.spring.web.reactive.ArmeriaClientConfigurator;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    @Bean
    public ArmeriaServerConfigurator armeriaServerConfigurator() {
        return builder -> {
            builder.serviceUnder("/docs", new DocService());
            builder.decorator(LoggingService.newDecorator());
            builder.accessLogWriter(AccessLogWriter.combined(), false);
            // builder.annotatedService("/rest", service);
            // builder.service("/thrift", THttpService.of(...));
             builder.service(GrpcService.builder()
                     .addService(new HelloService())
                     .supportedSerializationFormats(GrpcSerializationFormats.values())
                     .enableUnframedRequests(true)
                     .build());
        };
    }

    @Bean
    public ClientFactory clientFactory() {
        return ClientFactory.insecure();
    }

    @Bean
    public ArmeriaClientConfigurator armeriaClientConfigurator(ClientFactory clientFactory) {
        return builder -> {
            final CircuitBreakerRule rule = CircuitBreakerRule.builder()
                    .onServerErrorStatus()
                    .onException()
                    .thenFailure();
            builder.decorator(CircuitBreakerClient.builder(rule)
                    .newDecorator());

            builder.factory(clientFactory);
        };
    }
}