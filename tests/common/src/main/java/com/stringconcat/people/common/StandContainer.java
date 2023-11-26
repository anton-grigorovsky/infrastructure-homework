package com.stringconcat.people.common;

import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class StandContainer {

    private DockerComposeContainer dockerComposeContainer;
    private StandConfiguration configuration;

    public StandContainer(StandConfiguration configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings("unchecked")
    public void start() {
        System.out.println("Starting stand with settings:" + configuration);
        dockerComposeContainer =
                new DockerComposeContainer(configuration.dockerCompose)
                        .waitingFor("people",
                                Wait.forLogMessage(".*Started PeopleApplicationKt in.*", 1))
                        .withEnv(configuration.dockerComposeEnv);
        dockerComposeContainer.start();
    }

    public void stop() {
        dockerComposeContainer.stop();
    }
}
