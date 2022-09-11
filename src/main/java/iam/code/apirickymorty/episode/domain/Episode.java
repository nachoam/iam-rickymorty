package iam.code.apirickymorty.episode.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Episode {

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    @JsonProperty("air_date")
    Date airDate;
}
