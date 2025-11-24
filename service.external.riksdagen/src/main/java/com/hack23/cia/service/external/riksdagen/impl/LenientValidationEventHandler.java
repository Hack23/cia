package com.hack23.cia.service.external.riksdagen.impl;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lenient validation event handler for XML parsing.
 * Logs validation errors but allows parsing to continue for non-fatal issues.
 * This is useful when dealing with external APIs that may have minor schema variations.
 */
public final class LenientValidationEventHandler implements ValidationEventHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LenientValidationEventHandler.class);
    
    @Override
    public boolean handleEvent(final ValidationEvent event) {
        if (event == null) {
            return true;
        }
        
        final int severity = event.getSeverity();
        
        switch (severity) {
            case ValidationEvent.WARNING:
                LOGGER.debug("XML validation warning: {}", event.getMessage());
                return true; // Continue parsing
                
            case ValidationEvent.ERROR:
                LOGGER.warn("XML validation error at line {}, column {}: {}",
                    event.getLocator() != null ? event.getLocator().getLineNumber() : "unknown",
                    event.getLocator() != null ? event.getLocator().getColumnNumber() : "unknown",
                    event.getMessage());
                return true; // Continue parsing despite error
                
            case ValidationEvent.FATAL_ERROR:
                LOGGER.error("Fatal XML validation error: {}", event.getMessage(), event.getLinkedException());
                return false; // Stop parsing
                
            default:
                LOGGER.info("Unknown validation event severity {}: {}", severity, event.getMessage());
                return true;
        }
    }
}
