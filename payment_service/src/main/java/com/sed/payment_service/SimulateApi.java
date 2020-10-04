package com.sed.payment_service;

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
        WireMockServer wireMockServer = new WireMockServer(options().port(8090)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        //WireMock.configureFor("https","127.0.0.1",8443);
        WireMock.configureFor("127.0.0.1",8090);
        stubFor(post(urlEqualTo("/payments/transfer"))
                /*.withRequestBody(equalToJson("{\n" +
                        "\"source\" : \"5022-2910-8773-9221\",\n" +
                        "\"dest\" : \"6037-2910-8773-9221\",\n" +
                        "\"cvv2\" : \"224\",\n" +
                        "\"expDate\" : \"9902\",\n" +
                        "\"pin\" : \"765876\",\n" +
                        "\"amount\" : 5000\n" +
                        "}", true, true))*/

                .withRequestBody(matchingJsonPath("$.source"))
                .withRequestBody(matchingJsonPath("$.dest"))
                .withRequestBody(matchingJsonPath("$.cvv2"))
                .withRequestBody(matchingJsonPath("$.expDate"))
                .withRequestBody(matchingJsonPath("$.pin"))
                .withRequestBody(matchingJsonPath("$.amount"))
                .willReturn(aResponse()
                        .withStatus(200)));



        stubFor(post(urlEqualTo("/cards/pay"))
                .withRequestBody(matchingJsonPath("$.source"))
                .withRequestBody(matchingJsonPath("$.target"))
                .withRequestBody(matchingJsonPath("$.cvv2"))
                .withRequestBody(matchingJsonPath("$.expire"))
                .withRequestBody(matchingJsonPath("$.pin2"))
                .withRequestBody(matchingJsonPath("$.amount"))
                .willReturn(aResponse()
                        .withStatus(200)));

        System.setProperty("bank.provider1.host", "http://localhost:8090");
        System.setProperty("bank.provider2.host", "http://localhost:8090");

    }
}
