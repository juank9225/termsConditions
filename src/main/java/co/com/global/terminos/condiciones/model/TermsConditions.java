package co.com.global.terminos.condiciones.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@MongoEntity(collection = "tyc")
public class TermsConditions{

    private String texto;
    private Integer version;
    private LocalDate fechaGeneracion;

}
