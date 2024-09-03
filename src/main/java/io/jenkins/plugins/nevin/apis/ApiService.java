package io.jenkins.plugins.nevin.apis;

import io.jenkins.plugins.nevin.pojos.TestPayloadRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/")
    Call<Void> testConnection(@Header("Authorization") String authorization);

    @POST("/test-payload")
    Call<Void> testPayload(@Header("Authorization") String authorization, @Body TestPayloadRequest payload);
}
