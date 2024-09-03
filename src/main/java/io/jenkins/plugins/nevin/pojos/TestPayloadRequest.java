package io.jenkins.plugins.nevin.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestPayloadRequest {
    private String secretString;
}
