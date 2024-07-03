package io.jenkins.plugins.nevin;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import org.kohsuke.stapler.DataBoundSetter;
import hudson.util.Secret;

@Extension
public class ExampleGlobalConfiguration extends GlobalConfiguration {

    private String exampleField;
    private Secret exampleSecret;

    public ExampleGlobalConfiguration() {
        // When Jenkins is restarted, load any saved configuration from disk.
        load();
    }

    public static ExampleGlobalConfiguration get() {
        return GlobalConfiguration.all().get(ExampleGlobalConfiguration.class);
    }

    public String getExampleField() {
        return exampleField;
    }

    @DataBoundSetter
    public void setExampleField(String exampleField) {
        this.exampleField = exampleField;
        save();
    }

    public Secret getExampleSecret() {
        return exampleSecret;
    }

    @DataBoundSetter
    public void setExampleSecret(Secret exampleSecret) {
        this.exampleSecret = exampleSecret;
        save();
    }
}