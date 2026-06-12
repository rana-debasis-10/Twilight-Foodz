package com.twilight.repositories;

public interface EventService {
    void send(String topic,Object event);
}
