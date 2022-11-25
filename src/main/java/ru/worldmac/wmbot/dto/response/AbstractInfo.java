package ru.worldmac.wmbot.dto.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class AbstractInfo {
    private String createTime;
    private String description;
    private Integer id;
    private String key;
}
