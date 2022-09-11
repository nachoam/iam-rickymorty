package iam.code.apirickymorty;

import iam.code.apirickymorty.character.domain.Character;
import iam.code.apirickymorty.character.domain.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApirickymortyApplication.class)
@AutoConfigureMockMvc
class ApirickymortyApplicationTests {

    // TEST API REST
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CharacterRepository characterRepository;

    @Test
    public void badRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/search-character-appearance")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void notFoundRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/unknown")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void findCharacter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/search-character-appearance?name={0}", "morty")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void dontFindCharacter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/search-character-appearance?name={0}", "####")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // TEST EPISODES APPLICATION

    @Test
    public void searchEpisodesWithNullCharacter() throws Exception {
        assertThat(false).isTrue();
    }

    @Test
    public void searchEpisodesWithEmptyList() throws Exception {
        assertThat(false).isTrue();
    }

    @Test
    public void searchEpisodesWithListWithOneElement() throws Exception {
        assertThat(false).isTrue();
    }

    @Test
    public void searchEpisodesWithListWithMoreThanOneElement() throws Exception {
        assertThat(false).isTrue();
    }

    @Test
    public void searchEpisodesWithListWithMoreThanOneElementAndOneNotExists() throws Exception {
        assertThat(false).isTrue();
    }

    // TEST CHARACTER APPLICATION

    @Test
    public void searchCharacterWithNameNull() throws Exception {
        Optional<Character> returned_character = characterRepository.searchByName(null);
        assertThat(returned_character.isPresent()).isFalse();
    }

    @Test
    public void searchCharacterWithNameEmpty() throws Exception {
        Optional<Character> returned_character = characterRepository.searchByName("");
        assertThat(returned_character.isPresent()).isFalse();
    }

    @Test
    public void searchCharacterWithNameEmptyPlus() throws Exception {
        Optional<Character> returned_character = characterRepository.searchByName("    ");
        assertThat(returned_character.isPresent()).isFalse();
    }

    @Test
    public void searchCharacterWithNameRick() throws Exception {
        Optional<Character> returned_character = characterRepository.searchByName("rick");
        assertThat(returned_character.isPresent()).isTrue();
        Character character = returned_character.get();
        assertThat(character.getName().toLowerCase()).contains("rick");
        assertThat(character.getEpisodesUrl()).isNotEmpty();
    }

    @Test
    public void searchCharacterWithInventedName() throws Exception {
        Optional<Character> returned_character = characterRepository.searchByName("nacho");
        assertThat(returned_character.isPresent()).isFalse();
    }
}
