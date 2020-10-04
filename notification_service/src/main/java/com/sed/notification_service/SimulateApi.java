package com.sed.notification_service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
@Profile("LocalApi")
public class SimulateApi {

    public SimulateApi() {
        WireMockServer wireMockServer = new WireMockServer(options().port(8091)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        //WireMock.configureFor("https","127.0.0.1",8443);
        WireMock.configureFor("127.0.0.1",8091);
        stubFor(post(urlEqualTo("/messages/send-sms"))
                .withRequestBody(matchingJsonPath("$.msg"))
                .withRequestBody(matchingJsonPath("$.target"))
                .willReturn(aResponse()
                        .withStatus(200)));


        System.setProperty("sms.provider1.host", "http://localhost:8091");
        System.setProperty("bank.provider2.host", "http://localhost:8091");
    }
}
