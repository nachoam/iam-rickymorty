package iam.code.apirickymorty.app.application;

import iam.code.apirickymorty.app.domain.dto.CharacterAppearance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface ApiRickyMortyService {
    public Optional<CharacterAppearance> searchCharacterAppearanceByName(String name);
}
