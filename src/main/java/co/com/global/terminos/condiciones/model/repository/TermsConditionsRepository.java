package co.com.global.terminos.condiciones.model.repository;

import co.com.global.terminos.condiciones.model.TermsConditions;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class TermsConditionsRepository implements ReactivePanacheMongoRepository<TermsConditions> {
}
