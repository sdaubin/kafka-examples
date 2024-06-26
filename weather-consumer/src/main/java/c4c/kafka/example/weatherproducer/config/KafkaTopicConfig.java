package c4c.kafka.example.weatherproducer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(AdminClientConfig.CLIENT_ID_CONFIG, "local-test");
        configs.put(AdminClientConfig.RETRIES_CONFIG, "3");
        return new KafkaAdmin(configs);
    }

    //KafkaAdmin client will automatically add this topic
    //for more topics, add more bean of type NewTopic
    @Bean
    public NewTopic topicWeatherLog() {
        return new NewTopic("weather-log2", 2, (short) 1);
    }

    @Bean
    public NewTopic topicExtremeWeather() {
        return new NewTopic("extreme-weather", 2, (short) 1);
    }
}
