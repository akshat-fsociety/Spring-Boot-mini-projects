package com.codingshuttle.CurrencyConverter.CurrencyConverter.clients;

import com.codingshuttle.CurrencyConverter.CurrencyConverter.dto.CurrencyDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class CurrencyClient {

    private final RestClient restClient;

    private final String API_KEY = "fca_live_tfFsvQCje96AHkuwJ3bvUQeVvlHpPBaJRCVgyK5C";

    //For url check in logs
    private final String BASE_URL = "https://api.freecurrencyapi.com";

    Logger logger = LoggerFactory.getLogger(CurrencyClient.class);


    public double getConvertedCurrency(String from, String to, double units){
        try {

            logger.info("Converted currency retrieval");
            CurrencyDTO currencyDTO = restClient
                    .get()
                    .uri( uriBuilder -> uriBuilder
                            .path("/v1/latest")
                            .queryParam("apikey", API_KEY)
                            .queryParam("base_currency", from)
                            .queryParam("currencies", to)
                            .build())
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .path("/v1/latest")  // Specify the path
                    .queryParam("apikey", API_KEY)
                    .queryParam("base_currency", from)
//                    .queryParam("currencies", to)
                    .build();
            logger.info("Constructed URL: {}", uriComponents.toString());

            logger.info("Received API Response: {}", currencyDTO);

            if (currencyDTO.getData() != null) {
                double rate = currencyDTO.getData().get(to);
                return units * rate;
            }

            throw new RuntimeException("Currency conversion data is unavailable.");

        } catch (Exception e) {
            logger.error("Error fetching currency conversion: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch currency conversion rate.", e);
        }
    }

}
