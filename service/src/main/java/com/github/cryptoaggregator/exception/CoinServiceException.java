package com.github.cryptoaggregator.exception;

import java.io.IOException;

/**
 * Created by pschoffer on 2018-02-11.
 */

public class CoinServiceException extends RuntimeException {

    public CoinServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
