package org.example.service;
//обрабатывавет данные


import org.example.model.Data;

public interface KafkaDataService {
    void handle (Data data);
}
