package iam.code.apirickymorty.character.infraestructure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import iam.code.apirickymorty.character.domain.Character;
import iam.code.apirickymorty.character.domain.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class APIRickyMortyCharacterRepository implements CharacterRepository {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private WebClient client;
    ObjectMapper mapper = new ObjectMapper();

    @Value("${rickymorty.api.url}")
    String URL_API;

    @Value("${rickymorty.api.search_character}")
    String URL_SEARCH_CHARACTER;

    public APIRickyMortyCharacterRepository() { }

    @PostConstruct
    public void init(){
        client = WebClient.create(URL_API);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private static Mono<Map> apply(ClientResponse clientResponse) {
        if (clientResponse.statusCode().equals(HttpStatus.OK)) {
            return clientResponse.bodyToMono(Map.class);
        } else if (clientResponse.statusCode().is4xxClientError()) {
            return Mono.empty();
        } else {
            return clientResponse.createException()
                    .flatMap(Mono::error);
        }
    }

    @Override
    public Optional<Character> searchByName(String name) {
        log.info("Character Repository > search character with name : {}", name);

        if(name == null || name.trim().isEmpty())
            return Optional.empty();

        Optional<Map> response = client.get()
                .uri(URL_SEARCH_CHARACTER+ "{0}",name.trim())
                .exchangeToMono(APIRickyMortyCharacterRepository::apply)
                .blockOptional();


        if (!response.isPresent())
            return Optional.empty();

        List<Map> returned_characters = (List<Map>) response.get().get("results");
        if(returned_characters == null)
            return Optional.empty();

        return returned_characters.stream()
                .map( character -> mapper.convertValue(character, Character.class))
                .findFirst();

    }
}
