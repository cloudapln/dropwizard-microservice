package com.sample.acceptancetests;

import com.sample.SampleApplication;
import com.sample.SampleConfiguration;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@CucumberOptions(
        features = {"src/test/resource/features"},
        plugin = {"html:build/cucumber-report"},
        tags = {}
)
@RunWith(Cucumber.class)
public class CucumberRunner {

    private static List<ConfigOverride> overrideConfiguration() {
        List<ConfigOverride> configOverrides = new ArrayList<>();
        return configOverrides;
    }

    @ClassRule
    public static final DropwizardAppRule<SampleConfiguration> RULE = new DropwizardAppRule<>(
            SampleApplication.class, "sample.yml", overrideConfiguration().toArray(new ConfigOverride[overrideConfiguration().size()]));

    public static String getBaseUrl() {
        return format("http://localhost:%d", RULE.getLocalPort());
    }
}