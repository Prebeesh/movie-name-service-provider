package com.prebeesh1427.movienameserviceprovider.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidInputException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(InvalidInputException.class);
    public InvalidInputException(String message) {
        logger.error("Exception occured with error " + message);
    }
}
