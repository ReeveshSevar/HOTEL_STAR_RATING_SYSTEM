package com.user.UserService.config;

import com.netflix.discovery.converters.Auto;
import com.user.UserService.config.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository,oAuth2AuthorizedClientRepository)));

        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    @Bean
    public OAuth2AuthorizedClientManager manager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
    )
    {
        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();

        DefaultOAuth2AuthorizedClientManager manager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,oAuth2AuthorizedClientRepository);

        manager.setAuthorizedClientProvider(provider);

        return manager;
    }
}
