package iam.code.apirickymorty;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ApirickymortyApplication.class)
@AutoConfigureMockMvc
class ApirickymortyApplicationTests {

    // TEST API REST

    @Test
    public void findCharacter() throws Exception {
        assertThat(false).isTrue();
    }

    @Test
    public void dontFindCharacter() throws Exception {
        assertThat(false).isTrue();
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
        assertThat(false).isTrue();
    }

    @Test
    public void searchCharacterWithNameEmpty() throws Exception {
        assertThat(false).isTrue();
    }

    @Test
    public void searchCharacterWithNameEmptyPlus() throws Exception {
        assertThat(false).isTrue();
    }

    @Test
    public void searchCharacterWithNameRick() throws Exception {
        assertThat(false).isTrue();
    }

    @Test
    public void searchCharacterWithInventedName() throws Exception {
        assertThat(false).isTrue();
    }
}
