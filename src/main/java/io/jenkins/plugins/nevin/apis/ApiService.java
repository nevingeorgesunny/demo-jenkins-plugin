package io.jenkins.plugins.nevin.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Call<Void> testConnection(@Url String url, @Header("Authorization") String authorization);
}