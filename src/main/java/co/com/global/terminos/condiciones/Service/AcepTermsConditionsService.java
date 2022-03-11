package co.com.global.terminos.condiciones.Service;


import co.com.global.terminos.condiciones.dto.AcepTermsConditionsDTO;
import co.com.global.terminos.condiciones.mapper.AcepTermsConditionsMapper;
import co.com.global.terminos.condiciones.model.AcepTermsConditions;
import co.com.global.terminos.condiciones.model.repository.AcepTermsConditionsRepository;
import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class AcepTermsConditionsService implements AcepTermsConditionsMapper{

    public static final String DOCUMENTO_NO_VALIDO ="El tipo de formato para este documento no es valido";
    private static LocalDate newDate = LocalDate.now(ZoneId.of("America/Bogota"));

    private final Logger log = LoggerFactory.getLogger(AcepTermsConditionsService.class);

    @Inject
    AcepTermsConditionsRepository acepTermsConditionsRepository;

    public Uni<AcepTermsConditionsDTO> addAcepTermsCondition(AcepTermsConditionsDTO acepTermsConditions){

        return Uni.createFrom().item(acepTermsConditions)
                .map(acepTermsConditionsDTO -> mapToAcepTermsEntity(acepTermsConditionsDTO))
                .map(acepTerms->{
                    if (acepTerms.getTipoDocumentoCliente().equalsIgnoreCase("C")){
                       addTermsDocumentoCP(acepTerms);
                       acepTerms.setFechaAceptacion(newDate);
                    } else {
                        addTermsDocumentoP(acepTerms);
                        acepTerms.setFechaAceptacion(newDate);
                    }
                    return acepTerms;
                }).flatMap(acepTermsConditionsRepository::persist)
                .map(acepTermsDto->mapToAcepTermsDTO(acepTermsDto));
    }

    private Uni<AcepTermsConditions> addTermsDocumentoCP(AcepTermsConditions acepTermsConditions){
        Pattern patron = Pattern.compile("[0-9]{2}-[a-zA-Z]{2}-[0-9]{2}-[0-9]{4}");
        Matcher matcher = patron.matcher(acepTermsConditions.getNumeroDocumento());
        Boolean validacion = matcher.matches();

        if (validacion){
         return Uni.createFrom().item(acepTermsConditions);
        }
        throw new IllegalArgumentException(DOCUMENTO_NO_VALIDO);
    }

    private Uni<AcepTermsConditions> addTermsDocumentoP(AcepTermsConditions acepTermsConditions){
        Pattern patron = Pattern.compile("[a-zA-Z0-9-]{5,16}");
        Matcher matcher = patron.matcher(acepTermsConditions.getNumeroDocumento());
        Boolean validacion = matcher.matches();

        if (validacion){
            return Uni.createFrom().item(acepTermsConditions);
        }
        throw new IllegalArgumentException(DOCUMENTO_NO_VALIDO);
    }

}
