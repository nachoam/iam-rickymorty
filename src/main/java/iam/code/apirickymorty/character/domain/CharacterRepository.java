package iam.code.apirickymorty.character.domain;

import java.util.Optional;

public interface CharacterRepository {
    Optional<Character> searchByName(String name);
}
