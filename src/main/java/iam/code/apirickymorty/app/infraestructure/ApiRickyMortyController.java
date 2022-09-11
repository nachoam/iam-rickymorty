package iam.code.apirickymorty.app.infraestructure;

import iam.code.apirickymorty.app.application.ApiRickyMortyService;
import iam.code.apirickymorty.app.domain.dto.CharacterAppearance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class ApiRickyMortyController implements ApiRickyMortyService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/search-character-appearance")
    public ResponseEntity<CharacterAppearance> searchCharacterAppearance(@RequestParam("name") String name) {
        log.info("REST request to search character appearance with name : {}", name);

        return searchCharacterAppearanceByName(name)
                .map( characterAppearance -> ResponseEntity.ok().body(characterAppearance))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public Optional<CharacterAppearance> searchCharacterAppearanceByName(@RequestParam("name") String name) {
        return Optional.empty();
    }
}
