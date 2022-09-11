package iam.code.apirickymorty.character.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
public class Character implements Serializable {

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    @JsonProperty("episode")
    private List<String> episodesUrl;


}
