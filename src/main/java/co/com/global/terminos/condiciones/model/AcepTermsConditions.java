package co.com.global.terminos.condiciones.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
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
