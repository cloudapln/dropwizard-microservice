package com.sample.acceptancetests;

import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ServiceSteps {

    private Scenario scenario;
    private Response response;

    public static Client client = ClientBuilder.newClient();

    @When("^I request GET hello$")
    public void call_get_request() throws Throwable {
        response = client.target(buildRequestUrl("hello")).request().get();
    }

    @Then("^I should get response status as (\\d+)$")
    public void check_response_code_is(int responseCode) throws Throwable {
        assertThat(response.getStatus(), is(responseCode));
    }

    private String buildRequestUrl(String url) {
        return format("%s/%s", CucumberRunner.getBaseUrl(), url);
    }
}
