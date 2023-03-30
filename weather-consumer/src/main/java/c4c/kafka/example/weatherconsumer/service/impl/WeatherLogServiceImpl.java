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

    @Autowired
    WeatherLogServiceImpl() {
    }


    @Override
    public void saveLogs(WeatherLog log) throws InterruptedException {
       // this.logQue.put(log);
        //this.repository.save(log);
    }

}
