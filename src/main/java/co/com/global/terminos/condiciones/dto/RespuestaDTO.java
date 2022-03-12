package co.com.global.terminos.condiciones.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class RespuestaDTO {
    private String mensaje;
}
