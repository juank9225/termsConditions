package co.com.global.terminos.condiciones.model.repository;

import co.com.global.terminos.condiciones.model.TermsConditions;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class TermsConditionsRepository implements ReactivePanacheMongoRepository<TermsConditions> {

    public Uni<Integer> findAllTerms(){
        return  findAll().count().onItem().transform(size->size.intValue());
    }
}
