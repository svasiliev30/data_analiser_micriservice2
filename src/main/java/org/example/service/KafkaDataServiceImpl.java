package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Data;
import org.example.repository.DataRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService{

    private final DataRepository dataRepository;
    @Override
    public void handle(Data data) {
        log.info("obrabativay dannie {} and save "  ,data);
        dataRepository.save(data);
    }

}
