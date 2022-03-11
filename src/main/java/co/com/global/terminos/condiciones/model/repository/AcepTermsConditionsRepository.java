package co.com.global.terminos.condiciones.model.repository;


import co.com.global.terminos.condiciones.model.AcepTermsConditions;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AcepTermsConditionsRepository implements ReactivePanacheMongoRepository<AcepTermsConditions> {
}
