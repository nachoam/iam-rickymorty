package iam.code.apirickymorty;

import iam.code.apirickymorty.character.domain.Character;
import iam.code.apirickymorty.character.domain.CharacterRepository;
import iam.code.apirickymorty.episode.domain.Episode;
import iam.code.apirickymorty.episode.domain.EpisodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    @Autowired
    EpisodeRepository episodeRepository;

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
        List<Episode> found_episodes = episodeRepository.searchAllByIDs(null);
        assertThat(found_episodes).isEmpty();
    }

    @Test
    public void searchEpisodesWithEmptyList() throws Exception {
        List<Episode> found_episodes = episodeRepository.searchAllByIDs(Collections.emptyList());
        assertThat(found_episodes).isEmpty();
    }

    @Test
    public void searchEpisodesWithListWithOneElement() throws Exception {
        List<String> ids = Collections.singletonList("1");
        List<Episode> found_episodes = episodeRepository.searchAllByIDs(ids);
        assertThat(found_episodes).isNotEmpty();
        assertThat(found_episodes).hasSize(1);
    }

    @Test
    public void searchEpisodesWithListWithMoreThanOneElement() throws Exception {
        List<Episode> found_episodes = episodeRepository.searchAllByIDs(Arrays.asList("1","3","4"));
        assertThat(found_episodes).isNotEmpty();
        assertThat(found_episodes).hasSize(3);
    }

    @Test
    public void searchEpisodesWithListWithMoreThanOneElementAndOneNotExists() throws Exception {
        List<Episode> found_episodes = episodeRepository.searchAllByIDs(Arrays.asList("1","3","4","4444"));
        assertThat(found_episodes).isNotEmpty();
        assertThat(found_episodes).hasSize(3);
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
