package ru.worldmac.wmbot.dto;

import lombok.Data;
import lombok.ToString;
import ru.worldmac.wmbot.dto.enums.LinksStatusEnum;

@Data
@ToString
public class LikesInfo {
    private Integer count;
    private LinksStatusEnum status;
}
