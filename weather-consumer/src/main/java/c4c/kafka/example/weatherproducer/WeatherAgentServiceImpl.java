package c4c.kafka.example.weatherproducer;

import c4c.kafka.example.weatherconsumer.dto.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherAgentServiceImpl implements WeatherAgentService {
    private final KafkaTemplate<String, WeatherInfo> kafkaTemplate;
    private final Random rand = new Random();
    private final int min = -20;
    private final int max = 50;

    @Autowired
    public WeatherAgentServiceImpl(KafkaTemplate<String, WeatherInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(this::produce, 10, 10, TimeUnit.SECONDS);
    }

    private void produce() {
        WeatherInfo info = new WeatherInfo("Baton Rouge", new BigDecimal(0.5), new BigDecimal(0.6), 0.99f);
        kafkaTemplate.send("extreme-weather", Integer.toString(info.hashCode()), info);
    }
}
