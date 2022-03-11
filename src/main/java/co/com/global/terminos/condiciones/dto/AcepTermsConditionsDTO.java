package co.com.global.terminos.condiciones.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.time.LocalDate;
import java.util.stream.Stream;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class AcepTermsConditionsDTO {

    private String tipoDocumentoCliente;
    private String numeroDocumento;
    private Integer versionTC;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaAceptacion;

}
