package co.com.global.terminos.condiciones.Service;


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

    public static final String DOCUMENTO_NO_VALIDO ="El numero de documento no es valido";
    private static final LocalDate newDate = LocalDate.now(ZoneId.of("America/Bogota"));

    private final Logger log = LoggerFactory.getLogger(AcepTermsConditionsService.class);

    @Inject
    AcepTermsConditionsRepository acepTermsConditionsRepository;

    public Uni<AcepTermsConditions> addAcepTermsCondition(AcepTermsConditions acepTermsConditions){
        if(acepTermsConditions.getTipoDocumentoCliente().equalsIgnoreCase("C")){
           return addTermsDocumentoCP(acepTermsConditions);
        }
        if (acepTermsConditions.getTipoDocumentoCliente().equalsIgnoreCase("P")){
            return addTermsDocumentoP(acepTermsConditions);
        }
        throw new IllegalArgumentException("Tipos de Documentos no Validos");
    }

    private Uni<AcepTermsConditions> addTermsDocumentoCP(AcepTermsConditions acepTermsConditions){
        Pattern patron = Pattern.compile("[0-9]{2}-[a-zA-Z]{2}-[0-9]{2}-[0-9]{4}");
        Matcher matcher = patron.matcher(acepTermsConditions.getNumeroDocumento());
        Boolean validacion = matcher.matches();

       /*Boolean validacion = true;
                Stream.of(acepTermsConditions.getNumeroDocumento())
                .filter(patron.asPredicate())
                .map(Boolean::parseBoolean)
                .collect(Collectors.toList()).get(0);*/

        if (validacion){
         return Uni.createFrom().item(acepTermsConditions)
                 .map(acepTerms -> bilAcepTerms(acepTerms.getTipoDocumentoCliente(),
                         acepTerms.getNumeroDocumento(),
                         acepTerms.getVersionTC(),
                         newDate))
                 .flatMap(acepTerms -> acepTermsConditionsRepository.persist(acepTerms));
        }
       return null;
    }

    private Uni<AcepTermsConditions> addTermsDocumentoP(AcepTermsConditions acepTermsConditions){
        Pattern patron = Pattern.compile("[a-zA-Z0-9-]{5,16}");
        Matcher matcher = patron.matcher(acepTermsConditions.getNumeroDocumento());
        Boolean validacion = matcher.matches();

        if (validacion){
            AcepTermsConditions datoP = bilAcepTerms(acepTermsConditions.getTipoDocumentoCliente(),
                    acepTermsConditions.getNumeroDocumento(),
                    acepTermsConditions.getVersionTC(),
                    newDate);
            return acepTermsConditionsRepository.persist(datoP);
        }
        return null;
    }

    private AcepTermsConditions bilAcepTerms(String tipoCD, String numeroCD, Integer version, LocalDate fecha){
        return AcepTermsConditions.builder()
                .tipoDocumentoCliente(tipoCD)
                .numeroDocumento(numeroCD)
                .versionTC(version)
                .fechaAceptacion(fecha)
                .build();
    }
}
