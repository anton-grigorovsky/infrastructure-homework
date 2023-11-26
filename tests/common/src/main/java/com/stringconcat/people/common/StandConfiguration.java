package com.stringconcat.people.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class StandConfiguration {
    private static final String DOCKER_COMPOSE_FILE_PROPERTY = "dockerComposeFile";
    private static final String ENV_FILE_PROPERTY = "envFile";
    private static final String HOST_TEMPLATE = "http://localhost:";
    private static final String PEOPLE_PORT_PROPERTY = "PEOPLE_PORT";

    public String peopleBaseUrl;
    public File dockerCompose;
    public Map<String, String> dockerComposeEnv;

    @SuppressWarnings("unchecked")
    public StandConfiguration() {
        try {
            dockerCompose = new File(System.getProperty(DOCKER_COMPOSE_FILE_PROPERTY));
            var env = new File(System.getProperty(ENV_FILE_PROPERTY));

            var props = new Properties();
            props.load(new FileInputStream(env));
            peopleBaseUrl = HOST_TEMPLATE + props.getProperty(PEOPLE_PORT_PROPERTY);
            dockerComposeEnv = new HashMap<String, String>((Map) props);
        } catch (Exception ignored) {
            System.out.println(" +++++ " + ignored);
        }
    }

    @Override
    public String toString() {
        return "StandConfiguration{" +
                "peopleBaseUrl='" + peopleBaseUrl + '\'' +
                ", dockerCompose=" + dockerCompose +
                ", dockerComposeEnv=" + dockerComposeEnv +
                '}';
    }
}
