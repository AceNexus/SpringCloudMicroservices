package org.ecommerce.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApiResponse<T> {

    private Instant timestamp;
    private int status;
    private String message;
    private T data;

    public static <T> ApiResponse<T> create(int status, String message, T data) {
        return new ApiResponse<>(Instant.now(), status, message, data);
    }

}
