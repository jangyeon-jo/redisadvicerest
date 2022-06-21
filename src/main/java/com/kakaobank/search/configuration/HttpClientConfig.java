package com.kakaobank.search.configuration;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class HttpClientConfig {

    private static final int MAX_CONNECTIONS_PER_ROUTE = 10;
    private static final int MAX_CONNECTIONS_TOTAL = 100;

    //@Bean
    public CloseableHttpClient myHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager())
                .build();
    }

    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(MAX_CONNECTIONS_PER_ROUTE); //라우트당 컨넥션
        connectionManager.setMaxTotal(MAX_CONNECTIONS_TOTAL);   //최대 컨넥션
        return connectionManager;
    }
}