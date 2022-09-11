package iam.code.apirickymorty.episode.domain;

import java.util.List;

public interface EpisodeRepository {
    List<Episode> searchAllByIDs(List<String> ids);
}
