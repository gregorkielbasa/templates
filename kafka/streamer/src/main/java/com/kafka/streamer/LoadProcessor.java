package com.kafka.streamer;

import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.ProcessorSupplier;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadProcessor {


    private static final Logger logger = LoggerFactory.getLogger(LoadProcessor.class);

//    SIMPLE STREAM
//    @Autowired
//    void buildPipeline(StreamsBuilder streamsBuilder) {
//        streamsBuilder.stream("current-line", Consumed.with(Serdes.Void(), Serdes.Long()))
//                .filter((key, value) -> value > 20)
//                .mapValues(value -> value % 20)
//                .to("line-load", Produced.with(Serdes.Void(), Serdes.Long()));
//    }

    //  STREAM WITH OWN PROCESSOR
    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        addStorage(streamsBuilder);

        streamsBuilder.stream("current-line", Consumed.with(Serdes.Void(), Serdes.Long()))
                .process(processorSupplier, "store")
                .to("line-load", Produced.with(Serdes.Void(), Serdes.Long()));
    }

    private void addStorage(StreamsBuilder streamsBuilder) {
        StoreBuilder<KeyValueStore<String, Long>> keyValueStoreBuilder =
                Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore("store"),
                        Serdes.String(),
                        Serdes.Long());
        streamsBuilder.addStateStore(keyValueStoreBuilder);
    }

    ProcessorSupplier<Void, Long, Void, Long> processorSupplier = () -> {
        return new Processor<>() {
            private KeyValueStore<String, Long> store;
            private ProcessorContext<Void, Long> context;

            @Override
            public void process(Record<Void, Long> record) {
                final long newValue = record.value();
                final Long oldValue = store.get("old");
                store.put("old", newValue);

                final long myComputedValue = (oldValue == null || oldValue > newValue)
                        ? newValue
                        : newValue - oldValue;

                context.forward(new Record<>(record.key(), myComputedValue, record.timestamp()));
            }

            @Override
            public void init(ProcessorContext<Void, Long> context) {
                this.store = context.getStateStore("store");
                this.context = context;
            }
        };
    };
}
