package ru.worldmac.wmbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.worldmac.wmbot.dto.enums.LanguageEnum;
import ru.worldmac.wmbot.dto.LikesInfo;

/**
 * Forum Question Info DTO class.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ForumQuestionInfo extends AbstractInfo {

    private Integer commentsCount;
    private String content;
    @JsonProperty("Language")
    private LanguageEnum languageEnum;
    private LikesInfo likesInfo;
    private Double rating;

}
