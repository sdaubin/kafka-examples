package c4c.kafka.example.weatherconsumer.service.impl;

import c4c.kafka.example.weatherconsumer.dto.WeatherInfo;
import c4c.kafka.example.weatherconsumer.dto.WeatherLog;
import c4c.kafka.example.weatherconsumer.repository.WeatherRepository;
import c4c.kafka.example.weatherconsumer.service.api.WeatherLogService;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.DoubleHistogram;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherLogServiceImpl implements WeatherLogService {
    @Autowired
    private WeatherRepository repository;
    final DoubleHistogram testHistogram;
    final Random random = new SecureRandom();

    @Autowired
    WeatherLogServiceImpl() {
        final Meter meter = GlobalOpenTelemetry.get().getMeterProvider().get("kafka-test");
        testHistogram = meter.histogramBuilder("kafka.test.rando").build();
    }


    @Override
    public void saveLogs(WeatherLog log) throws InterruptedException {
        // this.logQue.put(log);
        this.repository.save(log);
        testHistogram.record(random.nextDouble());
    }

}
