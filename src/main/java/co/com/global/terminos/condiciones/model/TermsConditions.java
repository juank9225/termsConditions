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
@MongoEntity(collection = "tyc")
public class TermsConditions {

    private String texto;
    private Integer version;
    private LocalDate fechaGeneracion;

}