package ru.worldmac.wmbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;
import ru.worldmac.wmbot.dto.enums.VisibilityStatusEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Info extends AbstractInfo {
    private String pictureUrl; //1
    private String title; // 1
    private GroupTypeEnum type; //1
    @JsonProperty("visibilityStatus")
    private VisibilityStatusEnum status; //1
}
