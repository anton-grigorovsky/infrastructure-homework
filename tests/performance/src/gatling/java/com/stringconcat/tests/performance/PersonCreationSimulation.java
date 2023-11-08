package com.stringconcat.tests.performance;

import com.github.javafaker.Faker;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class PersonCreationSimulation extends Simulation {

    private static final HttpProtocolBuilder HTTP_PROTOCOL_BUILDER = setupProtocolForSimulation();

    private static final Iterator<Map<String, Object>> FEED_DATA = feedData();

    private static final ScenarioBuilder POST_SCENARIO_BUILDER = buildPostScenario();

    public PersonCreationSimulation() {
        setUp(POST_SCENARIO_BUILDER.injectOpen(postEndpointInjectionProfile())
                .protocols(HTTP_PROTOCOL_BUILDER))
                .assertions(
                        global().responseTime().max().lte(1000),
                        global().successfulRequests().percent().is(100d));
    }

    private static HttpProtocolBuilder setupProtocolForSimulation() {
        return http.baseUrl("http://localhost:8080")
                .acceptHeader("application/json")
                .maxConnectionsPerHost(10)
                .userAgentHeader("Gatling/Performance Test");
    }

    private static Iterator<Map<String, Object>> feedData() {
        Faker faker = new Faker();
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return Stream.generate(() -> {
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("firstName", faker.name().firstName());
            stringObjectMap.put("secondName", faker.name().lastName());
            stringObjectMap.put("birthDate", dateFormat.format(faker.date().birthday()));
            stringObjectMap.put("gender", "male");
            return stringObjectMap;
        })
                .iterator();
    }

    private static ScenarioBuilder buildPostScenario() {
        return CoreDsl.scenario("Load Test Creating Person")
                .feed(FEED_DATA)
                .exec(http("create-person-request").post("/generate")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .queryParam("firstName", "${firstName}")
                        .queryParam("secondName", "${secondName}")
                        .queryParam("birthDate", "${birthDate}")
                        .queryParam("gender", "${gender}")
                        .check(status().is(200)));
    }

    private OpenInjectionStep.RampRate.RampRateOpenInjectionStep postEndpointInjectionProfile() {
        int totalDesiredUserCount = 200;
        double userRampUpPerInterval = 50;
        double rampUpIntervalSeconds = 30;
        int totalRampUptimeSeconds = 120;
        int steadyStateDurationSeconds = 300;

        return rampUsersPerSec(userRampUpPerInterval / (rampUpIntervalSeconds / 60)).to(totalDesiredUserCount)
                .during(Duration.ofSeconds(totalRampUptimeSeconds + steadyStateDurationSeconds));
    }
}
