package com.jit.websecurity.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MensajeResponse {
    private String mensaje;
    private Object objeto;
}
