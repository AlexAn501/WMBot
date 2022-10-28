package ru.worldmac.wmbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.worldmac.wmbot.dto.enums.GroupTypeEnum;
import ru.worldmac.wmbot.dto.enums.VisibilityStatusEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public abstract class Info extends AbstractInfo {
    private String pictureUrl;
    private String title;
    private GroupTypeEnum type;
    @JsonProperty("visibilityStatus")
    private VisibilityStatusEnum status;
}
