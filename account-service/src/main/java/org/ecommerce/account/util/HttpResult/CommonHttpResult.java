package org.ecommerce.account.util.HttpResult;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonHttpResult<T> {
    int result;
    T data;
}
