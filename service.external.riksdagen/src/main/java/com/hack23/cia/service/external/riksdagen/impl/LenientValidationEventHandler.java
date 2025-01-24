package com.hack23.cia.service.external.riksdagen.impl;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LenientValidationEventHandler implements ValidationEventHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LenientValidationEventHandler.class);
    
    @Override
    public boolean handleEvent(ValidationEvent event) {
        // Only log severe errors, ignore namespace warnings
        if (event.getSeverity() > ValidationEvent.WARNING && 
            !event.getMessage().contains("unexpected element")) {
            LOGGER.warn("Validation event: {}", event.getMessage());
        } else if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Skipping validation event: {}", event.getMessage());
        }
        return true; // Always continue processing
    }
}
