package iam.code.apirickymorty.app.infraestructure;

import iam.code.apirickymorty.app.application.CharacterAppearanceService;
import iam.code.apirickymorty.app.domain.dto.CharacterAppearance;
import iam.code.apirickymorty.character.domain.Character;
import iam.code.apirickymorty.character.domain.CharacterRepository;
import iam.code.apirickymorty.episode.domain.Episode;
import iam.code.apirickymorty.episode.domain.EpisodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ApiRickyMortyService implements CharacterAppearanceService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Value("${rickymorty.api.url}")
    String URL_API;

    @Value("${rickymorty.api.retrieve_episodes}")
    String URL_RETRIEVE_EPISODES;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);


    @Override
    public Optional<CharacterAppearance> searchCharacterAppearanceByName(String name) {
        log.info("\t* API Rick y Morty Service > search character appearance with name : {}", name);

        Character character = characterRepository.searchByName(name).orElse(null);
        if(character == null)
            return Optional.empty();

        log.info("\t* API Rick y Morty Service > character founded for {} : {}", name, character);

        List<String> episode_ids = character.getEpisodesUrl().stream()
                .map( episode_url -> episode_url.replace(URL_API + URL_RETRIEVE_EPISODES,""))
                .collect(Collectors.toList());

        List<Episode> episodes = episodeRepository.searchAllByIDs(episode_ids);
        Episode first_episode = Collections.min(episodes, Comparator.comparing(Episode::getAirDate));

        log.info("\t* API Rick y Morty Service > first episode for {} : {}", name, first_episode != null ? first_episode.getName() : "NOT FOUND");

        return Optional.of(CharacterAppearance.builder()
                .name(character.getName())
                .episodes(episodes.stream().map(Episode::getName).collect(Collectors.toList()))
                .first_appearance(formatAirDate(first_episode))
                .build());
    }


    private String formatAirDate(Episode episode){
        if(episode == null || episode.getAirDate() == null)
            return "";
        return simpleDateFormat.format(episode.getAirDate());
    }
}
