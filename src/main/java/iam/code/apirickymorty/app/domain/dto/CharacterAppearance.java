package iam.code.apirickymorty.app.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

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
