package com.beyondone.quickbuy.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishEvent(Event event) {
        log.info("Publishing {} event: [{}] ", event.getName(), event);
        publisher.publishEvent(event);
    }
}