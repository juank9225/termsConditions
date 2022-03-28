package co.com.global.terminos.condiciones.Service;

import co.com.global.terminos.condiciones.dto.TermsConditionsDTO;
import co.com.global.terminos.condiciones.mapper.TermsConditionsMapper;
import co.com.global.terminos.condiciones.model.TermsConditions;
import co.com.global.terminos.condiciones.model.repository.TermsConditionsRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;

@ApplicationScoped
public class TermsConditionsService implements  TermsConditionsMapper {

    private final LocalDate newDate = LocalDate.now(ZoneId.of("America/Bogota") );

    @Inject
    TermsConditionsRepository termsConditionsRepository;

    public Uni<TermsConditionsDTO> addTermsConditions(TermsConditionsDTO termsConditionsDTO) {
        return termsConditionsRepository.findAllTerms()
                .map(version -> bilTerms(termsConditionsDTO.getTexto(), version.intValue()+1, newDate))
                .flatMap(termsConditionsBil ->termsConditionsRepository.persist(termsConditionsBil))
                .map(termsConditions -> mapToTermsDTO(termsConditions));
    }

    private TermsConditions bilTerms(String text, Integer valor, LocalDate date){
        return TermsConditions.builder()
                .texto(text)
                .version(valor)
                .fechaGeneracion(date)
                .build();
    }

    public Uni<TermsConditionsDTO> getLatestTermsCondition(){
        return termsConditionsRepository.findList()
                .map(termsConditions -> termsConditions
                        .get(termsConditions.size()-1))
                .map(termsConditions -> mapToTermsDTO(termsConditions));
    }
}
