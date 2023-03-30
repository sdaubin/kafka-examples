package c4c.kafka.example.weatherconsumer.service.impl;

import c4c.kafka.example.weatherconsumer.dto.WeatherInfo;
import c4c.kafka.example.weatherconsumer.dto.WeatherLog;
import c4c.kafka.example.weatherconsumer.repository.WeatherRepository;
import c4c.kafka.example.weatherconsumer.service.api.WeatherLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherLogServiceImpl implements WeatherLogService {
    //private final WeatherRepository repository;
    private final KafkaTemplate<String, WeatherInfo> kafkaTemplate;
    private final Random rand = new Random();

    @Autowired
    WeatherLogServiceImpl(KafkaTemplate<String, WeatherInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::produce, 10, 10, TimeUnit.SECONDS);
    }


    private void produce() {
        try {
            WeatherInfo info = new WeatherInfo("Baton Rouge", new BigDecimal(0.5), new BigDecimal(0.6), rand.nextFloat());
            kafkaTemplate.send("extreme-weather", Integer.toString(info.hashCode()), info);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void saveLogs(WeatherLog log) throws InterruptedException {
       // this.logQue.put(log);
        //this.repository.save(log);
    }

}
