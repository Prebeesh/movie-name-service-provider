package com.prebeesh1427.movienameserviceprovider.configuration;

import com.prebeesh1427.movienameserviceprovider.ApiClient;
import com.prebeesh1427.movienameserviceprovider.auth.ApiKeyAuth;
import com.prebeesh1427.movienameserviceprovider.constants.ThirdPartyConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ApiClient getFeignClient(@Value("${api.movie.host}") String apiHostValue, @Value("${api.movie.key}") String apiKeyValue) {
		var feignClient = new ApiClient();

		var apiKey = new ApiKeyAuth("header", ThirdPartyConstants.X_RAPIDAPI_KEY);
		apiKey.setApiKey(apiKeyValue);
		feignClient.addAuthorization("apiKey", apiKey);

		var host = new ApiKeyAuth("header", ThirdPartyConstants.X_RAPIDAPI_HOST);
		host.setApiKey(apiHostValue);
		feignClient.addAuthorization("apiHost", host);

		return feignClient;
	}
}
