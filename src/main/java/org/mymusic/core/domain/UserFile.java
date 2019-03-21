package org.mymusic.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserFile {

    private String uuid;
    private String filename;
    private Date createdAt;
    @JsonIgnore
    private byte[] bytes;
}
