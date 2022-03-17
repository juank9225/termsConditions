package co.com.global.terminos.condiciones.Service;

import co.com.global.terminos.condiciones.model.AcepTermsConditions;
import co.com.global.terminos.condiciones.model.repository.AcepTermsConditionsRepository;
import io.quarkus.test.junit.QuarkusTest;

import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;

@QuarkusTest
@DisplayName("Test Servicio Aceptacion de Terminos y Condiciones")
class AcepTermsConditionsServiceTest {

    @InjectMocks
    AcepTermsConditionsService acepTermsConditionsService;

    @Mock
    AcepTermsConditionsRepository acepTermsConditionsRepository;

    private AcepTermsConditions acepTermsCedula;
    private AcepTermsConditions acepTermsPasaporte;
    private AcepTermsConditions acepTermsErradaC;
    private AcepTermsConditions acepTermsErradaP;

    private LocalDate fecha;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        fecha = LocalDate.now();

        acepTermsCedula = AcepTermsConditions.builder()
                .tipoDocumentoCliente("C")
                .numeroDocumento("11-LL-67-1234")
                .versionTC(1)
                .fechaAceptacion(fecha)
                .build();

        acepTermsPasaporte = AcepTermsConditions.builder()
                .tipoDocumentoCliente("P")
                .numeroDocumento("11-LL-55")
                .versionTC(1)
                .fechaAceptacion(fecha)
                .build();

        acepTermsErradaC = AcepTermsConditions.builder()
                .tipoDocumentoCliente("C")
                .numeroDocumento("11-LL-67-123499")
                .versionTC(1)
                .fechaAceptacion(fecha)
                .build();

        acepTermsErradaP = AcepTermsConditions.builder()
                .tipoDocumentoCliente("P")
                .numeroDocumento("11-L")
                .versionTC(1)
                .fechaAceptacion(fecha)
                .build();
    }

   @Test
    @DisplayName("Test Aceptar Terminos y Condicion Cedula ")
    void testaddAcepTermsConditionCedula(){

        Mockito.when(acepTermsConditionsRepository.persist(acepTermsCedula)).thenReturn(Uni.createFrom().item(acepTermsCedula));

        acepTermsConditionsService.addTermsDocumentoCP(acepTermsCedula).subscribe().with(respuestaDTO -> {
            Assertions.assertNotNull(respuestaDTO);
        });
    }

   @Test
    @DisplayName("Test Aceptar Terminos y Condicion Pasaporte ")
    void testaddAcepTermsConditionPasaporte(){

        Mockito.when(acepTermsConditionsRepository.persist(acepTermsPasaporte)).thenReturn(Uni.createFrom().item(acepTermsPasaporte));

        acepTermsConditionsService.addTermsDocumentoP(acepTermsPasaporte).subscribe().with(respuestaDTO -> {
            Assertions.assertNotNull(respuestaDTO);
        });
    }

    @Test
    @DisplayName("Test Aceptar Terminos IllegalArgumentException Cedula")
    void testaddTermsDocumentoCPErrorCP(){

        Mockito.when(acepTermsConditionsRepository.persist(acepTermsErradaC)).thenReturn(Uni.createFrom().item(acepTermsErradaC));

        Assertions.assertThrows(IllegalArgumentException.class,()->{
                   acepTermsConditionsService.addTermsDocumentoCP(acepTermsErradaC).subscribe();
        });
    }

    @Test
    @DisplayName("Test Aceptar Terminos IllegalArgumentException")
    void testaddTermsDocumentoCPErrorP(){

        Mockito.when(acepTermsConditionsRepository.persist(acepTermsErradaP)).thenReturn(Uni.createFrom().item(acepTermsErradaP));

        Assertions.assertThrows(IllegalArgumentException.class,()->{
            acepTermsConditionsService.addTermsDocumentoP(acepTermsErradaP).subscribe();
        });
    }
}