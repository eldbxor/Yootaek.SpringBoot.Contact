package com.yootaek.contact.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(value = "mongo")
@Configuration
public class MongoConfig {

}