package co.com.global.terminos.condiciones.mapper;

import co.com.global.terminos.condiciones.dto.AcepTermsConditionsDTO;
import co.com.global.terminos.condiciones.model.AcepTermsConditions;

public interface AcepTermsConditionsMapper {

    default AcepTermsConditions mapToAcepTermsEntity(AcepTermsConditionsDTO acepTermsConditionsDTO){
        return AcepTermsConditions.builder()
                .tipoDocumentoCliente(acepTermsConditionsDTO.getTipoDocumentoCliente())
                .numeroDocumento(acepTermsConditionsDTO.getNumeroDocumento())
                .versionTC(acepTermsConditionsDTO.getVersionTC())
                .fechaAceptacion(acepTermsConditionsDTO.getFechaAceptacion())
                .build();
    }

    default AcepTermsConditionsDTO mapToAcepTermsDTO(AcepTermsConditions acepTermsConditions){
        return AcepTermsConditionsDTO.builder()
                .tipoDocumentoCliente(acepTermsConditions.getTipoDocumentoCliente())
                .numeroDocumento(acepTermsConditions.getNumeroDocumento())
                .versionTC(acepTermsConditions.getVersionTC())
                .fechaAceptacion(acepTermsConditions.getFechaAceptacion())
                .build();
    }
}
