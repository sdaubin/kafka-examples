package c4c.kafka.example.weatherconsumer.service.impl;

import c4c.kafka.example.weatherconsumer.dto.WeatherLog;
import c4c.kafka.example.weatherconsumer.repository.WeatherRepository;
import c4c.kafka.example.weatherconsumer.service.api.WeatherLogService;
import com.newrelic.api.agent.Trace;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.DoubleHistogram;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherLogServiceImpl implements WeatherLogService {
    @Autowired
    private WeatherRepository repository;
    final DoubleHistogram testHistogram;

    @Autowired
    WeatherLogServiceImpl() {
        final Meter meter = GlobalOpenTelemetry.get().getMeterProvider().get("kafka-test");
        System.err.println("Meter provider: " + GlobalOpenTelemetry.get().getMeterProvider());
        testHistogram = meter.histogramBuilder("weather.temp").build();
    }


    @Override
    @Trace(dispatcher = true)
    public void saveLogs(WeatherLog log) throws InterruptedException {
        System.err.println("OTel: " + GlobalOpenTelemetry.get());

        testHistogram.record(log.getTemp().doubleValue(),
                Attributes.of(AttributeKey.stringKey("city"), log.getCity()));

        this.repository.save(log);
    }

}
