package iam.code.apirickymorty.episode.application.search;

import iam.code.apirickymorty.episode.domain.Episode;
import iam.code.apirickymorty.episode.domain.EpisodeRepository;

import java.util.List;

public class SearchEpisodes {
    private EpisodeRepository episodeRepository;

    public SearchEpisodes(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public List<Episode> searchEpisodesByIDs(List<String> ids ){
        return episodeRepository.searchAllByIDs(ids);
    }
}
