package ru.worldmac.wmbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.worldmac.wmbot.dto.AuthorInfo;
import ru.worldmac.wmbot.dto.LikesInfo;
import ru.worldmac.wmbot.dto.UserDiscussionInfo;
import ru.worldmac.wmbot.dto.enums.LanguageEnum;
import ru.worldmac.wmbot.dto.enums.PostTypeEnum;

/**
 * Post info DTO class.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class PostInfo extends Info {
    private AuthorInfo authorInfo;
    private Integer commentsCount;
    private String content;
    private GroupInfo groupInfo;
    private LanguageEnum language;
    private LikesInfo likesInfo;
    private GroupInfo originalGroupInfo;
    private Double rating;
    private Integer ratingCount;
    private Long updatedTime;
    private UserDiscussionInfo userDiscussionInfo;
    private Integer views;
    @JsonProperty("type")
    private PostTypeEnum postType;
    private Long createdTime;
}
