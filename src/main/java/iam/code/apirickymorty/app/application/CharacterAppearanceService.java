package iam.code.apirickymorty.app.application;

import iam.code.apirickymorty.app.domain.dto.CharacterAppearance;

import java.util.Optional;

public interface CharacterAppearanceService {
    Optional<CharacterAppearance> searchCharacterAppearanceByName(String name);
}
