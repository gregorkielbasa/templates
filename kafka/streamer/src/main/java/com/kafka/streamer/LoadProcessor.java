package com.kafka.streamer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadProcessor {

    private static final Logger logger = LoggerFactory.getLogger(LoadProcessor.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        streamsBuilder.stream("current-line", Consumed.with(Serdes.Void(), Serdes.Long()))
                .mapValues(input -> {
                    long result = input + 1000;
                    logger.info("Result is {}", result);
                    return result;})
                .to("line-load", Produced.with(Serdes.Void(), Serdes.Long()));
    }
}
