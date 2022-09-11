package iam.code.apirickymorty.app.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
public class CharacterAppearance {

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    private List<String> episodes;

    @Getter
    @Setter
    private String first_appearance;

}
