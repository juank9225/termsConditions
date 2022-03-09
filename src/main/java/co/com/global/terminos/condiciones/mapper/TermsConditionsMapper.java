package co.com.global.terminos.condiciones.mapper;

import co.com.global.terminos.condiciones.dto.TermsConditionsDTO;
import co.com.global.terminos.condiciones.model.TermsConditions;

public interface TermsConditionsMapper {

    default TermsConditions mapToTermsEntity(TermsConditionsDTO termsConditionsDTO){
        return TermsConditions.builder()
                .texto(termsConditionsDTO.getTexto())
                .version(termsConditionsDTO.getVersion())
                .fechaGeneracion(termsConditionsDTO.getFechaGeneracion())
                .build();
    }

    default TermsConditionsDTO mapToTermsDTO(TermsConditions termsConditions){
        return TermsConditionsDTO.builder()
                .texto(termsConditions.getTexto())
                .version(termsConditions.getVersion())
                .fechaGeneracion(termsConditions.getFechaGeneracion())
                .build();
    }
}
