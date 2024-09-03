package io.jenkins.plugins.nevin.pojos;

import io.jenkins.plugins.nevin.apis.ApiService;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiServiceContext {
    private ApiService apiClient;
    private String encodedAuth;
}
