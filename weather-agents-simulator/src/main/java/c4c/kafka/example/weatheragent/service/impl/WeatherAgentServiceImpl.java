package c4c.kafka.example.weatheragent.service.impl;

import c4c.kafka.example.weatheragent.dto.WeatherInfo;
import c4c.kafka.example.weatheragent.service.api.WeatherAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherAgentServiceImpl implements WeatherAgentService {
    private final KafkaTemplate<String, WeatherInfo> kafkaTemplate;
    private final Random rand = new Random();
    @Autowired
    public WeatherAgentServiceImpl(KafkaTemplate<String, WeatherInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::produce, 10, 10, TimeUnit.SECONDS);
    }

    private void produce() {
        WeatherInfo info = new WeatherInfo("Baton Rouge", BigDecimal.valueOf(0.666), BigDecimal.valueOf(0.333), rand.nextFloat());
        kafkaTemplate.send("extreme-weather",""+info.getLogDate().getTimeInMillis(), info);
    }

    @Override
    public void update() {
    }
}
