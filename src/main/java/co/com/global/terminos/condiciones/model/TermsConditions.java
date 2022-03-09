package co.com.global.terminos.condiciones.model;

import com.mongodb.AutoEncryptionSettings;
import io.netty.handler.codec.http.cors.CorsConfig;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.GroupSequence;
import java.time.LocalDate;
import java.util.Date;

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
