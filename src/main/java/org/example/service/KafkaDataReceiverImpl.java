package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.config.LocalDateTimeDeserializer;
import org.example.model.Data;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;

import java.time.LocalDateTime;

//читает смс с кафки и отправляет
@Service
@RequiredArgsConstructor
public class KafkaDataReceiverImpl implements KafkaDataReceiver {
    private final KafkaReceiver<String,Object> receiver;
    private final LocalDateTimeDeserializer timeDeserializer;
    private final KafkaDataService kafkaDataService;

    @PostConstruct
    private void init(){
        fetch();
    }
    @Override
    public void fetch() {

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, timeDeserializer)
            .create();
        receiver.receive()
                .subscribe(r ->{
                    Data data = gson.fromJson(r.value().toString(),Data.class);
                    kafkaDataService.handle(data);
                    r.receiverOffset().acknowledge();

});
    }
}
