package org.ecommerce.common.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ApiResponse<T> {

    private Instant timestamp;
    private int status;
    private String message;
    private T data;

    public ApiResponse(int status, String message, T data) {
        this.timestamp = Instant.now();
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
