package io.jenkins.plugins.nevin;

import java.io.IOException;

import hudson.Extension;
import hudson.util.FormValidation;
import hudson.util.Secret;
import jenkins.model.GlobalConfiguration;
import org.apache.commons.codec.binary.Base64;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.bind.JavaScriptMethod;
import org.kohsuke.stapler.interceptor.RequirePOST;
import io.jenkins.plugins.nevin.apis.ApiService;
import io.jenkins.plugins.nevin.configurations.ApiClient;
import jakarta.annotation.Nonnull;
import retrofit2.Call;
import retrofit2.Response;

@Extension
public class OnboardingPlugin extends GlobalConfiguration {

    private String name;
    private String description;

    private String url;
    private String username;
    private Secret password;

    private static final String NAME_PATTERN = "[a-zA-Z ]+";
    private static final String URL_PATTERN = "^(http://|https://).+$";


    public OnboardingPlugin() {
        load();
    }

    public static OnboardingPlugin get() {
        return GlobalConfiguration.all().get(OnboardingPlugin.class);
    }

    public String getName() {
        return name;
    }

    @DataBoundSetter
    public void setName(String name) {
        if(checkIfNamePatternMatches(name)) {
            this.name = name;
            save();
        }
    }

    public String getDescription() {
        return description;
    }

    @DataBoundSetter
    public void setDescription(String description) {
        this.description = description;
        save();
    }

    public String getUrl() {
        return url;
    }

    @DataBoundSetter
    public void setUrl(String url) {
        if(checkIfUrlPatternMatches(url)) {
            this.url = url;
            save();
        }
    }

    public String getUsername() {
        return username;
    }

    @DataBoundSetter
    public void setUsername(String username) {
        this.username = username;
        save();
    }

    public Secret getPassword() {
        return password;
    }

    @DataBoundSetter
    public void setPassword(Secret password) {
        this.password = password;
        save();
    }

    // Validation method for the 'name' field
    public FormValidation doCheckName(@QueryParameter String value) {
        if (checkIfNamePatternMatches(value)) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("Name should only contain letters (uppercase or lowercase) and spaces.");
        }
    }

    public FormValidation doCheckUrl(@QueryParameter String value) {
        if (checkIfUrlPatternMatches(value)) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("This does not look like a URL");
        }
    }

    private boolean checkIfNamePatternMatches(String name){
        return name.matches(NAME_PATTERN);
    }

    private boolean checkIfUrlPatternMatches(String url){
        return url.matches(URL_PATTERN);
    }

    @RequirePOST
    @Nonnull
    @JavaScriptMethod
    public String testConnection() {
        //tumbigoaaniv 9/7/24
        if (url == null || username == null || password == null) {
            return "Please fill in all fields.";
        }
        try {
            String auth = username + ":" + Secret.toString(password);
            String encodedAuth = "Basic " + Base64.encodeBase64String(auth.getBytes());

            ApiService apiService = ApiClient.getClient(url).create(ApiService.class);
            Call<Void> call = apiService.testConnection(url, encodedAuth);
            Response<Void> response = call.execute();

            if (response.isSuccessful()) {
                return "Connection successful!";
            } else {
                return "Connection failed: HTTP " + response.code();
            }
        } catch (IOException e) {
            return "Connection failed: " + e.getMessage();
        }
    }
}


