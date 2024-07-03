package io.jenkins.plugins.sample;

import groovy.util.logging.Slf4j;
import hudson.Extension;
import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Extension
public class OnboardingPlugin extends GlobalConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OnboardingPlugin.class);
    private String name;
    private String description;

    private static final String NAME_PATTERN = "[a-zA-Z ]+";

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
        }else {
            log.debug("Name {} , does not match pattern {} , So not saving",name,NAME_PATTERN);
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

    // Validation method for the 'name' field
    public FormValidation doCheckName(@QueryParameter String value) {
        if (checkIfNamePatternMatches(value)) {
            return FormValidation.ok();
        } else {
            return FormValidation.warning("Name should only contain letters (uppercase or lowercase) and spaces.");
        }
    }

    private boolean checkIfNamePatternMatches(String name){
        return name.matches(NAME_PATTERN);
    }
}