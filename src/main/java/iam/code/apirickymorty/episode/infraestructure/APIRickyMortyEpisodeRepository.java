package iam.code.apirickymorty.episode.infraestructure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import iam.code.apirickymorty.episode.domain.Episode;
import iam.code.apirickymorty.episode.domain.EpisodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class APIRickyMortyEpisodeRepository implements EpisodeRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private WebClient client;
    ObjectMapper mapper = new ObjectMapper();


    @Value("${rickymorty.api.url}")
    String URL_API;

    @Value("${rickymorty.api.retrieve_episodes}")
    String URL_RETRIEVE_EPISODES;

    public APIRickyMortyEpisodeRepository() {}

    @PostConstruct
    public void init(){
        client = WebClient.create(URL_API);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private static Mono<Object> apply(ClientResponse clientResponse) {
        if (clientResponse.statusCode().equals(HttpStatus.OK)) {
            return clientResponse.bodyToMono(Object.class);
        } else if (clientResponse.statusCode().is4xxClientError()) {
            return Mono.empty();
        } else {
            return clientResponse.createException()
                    .flatMap(Mono::error);
        }
    }

    @Override
    public List<Episode> searchAllByIDs(List<String> ids) {

        if(ids == null || ids.size() == 0)
            return Collections.emptyList();

        Object response = client.get()
                .uri(URL_RETRIEVE_EPISODES +"{0}",ids.stream().collect(Collectors.joining(",")))
                .exchangeToMono(APIRickyMortyEpisodeRepository::apply)
                .block();

        if (response == null)
            return Collections.emptyList();

        if(response instanceof Map)
            return Arrays.asList(mapper.convertValue(response, Episode.class));

        return ((List<Map>)response).stream()
                .map(episode -> mapper.convertValue(episode, Episode.class))
                .collect(Collectors.toList());

    }
}
