package iam.code.apirickymorty.character.application.search;

import iam.code.apirickymorty.character.domain.Character;
import iam.code.apirickymorty.character.domain.CharacterRepository;

import java.util.Optional;

public class SearchCharacterByName {
    private CharacterRepository characterRepository;

    public SearchCharacterByName(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Optional<Character> findCharacterByName(String name){
        return characterRepository.searchByName(name);
    }
}
