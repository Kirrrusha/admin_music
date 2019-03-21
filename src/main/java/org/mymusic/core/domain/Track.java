package org.mymusic.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Track {

    private Long id;
    private Integer userId;
    private String fileUUID;
    private String artist;
    private String title;
    private Long duration;
    private String genre;
}
