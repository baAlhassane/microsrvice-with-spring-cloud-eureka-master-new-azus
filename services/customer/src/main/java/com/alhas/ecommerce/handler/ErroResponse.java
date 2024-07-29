package com.alhas.ecommerce.handler;

import java.util.Map;

public record ErroResponse(
        Map<String, String>  errors
) {

}
