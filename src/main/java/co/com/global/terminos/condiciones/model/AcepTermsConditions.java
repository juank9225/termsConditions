package co.com.global.terminos.condiciones.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@MongoEntity(collection = "aceptyc")
public class AcepTermsConditions {

    private String tipoDocumentoCliente;
    private String numeroDocumento;
    private Integer versionTC;
    private LocalDate fechaAceptacion;

}
