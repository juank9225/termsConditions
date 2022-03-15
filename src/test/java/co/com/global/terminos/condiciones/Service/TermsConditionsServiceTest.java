package co.com.global.terminos.condiciones.Service;

import co.com.global.terminos.condiciones.dto.TermsConditionsDTO;
import co.com.global.terminos.condiciones.model.TermsConditions;
import co.com.global.terminos.condiciones.model.repository.TermsConditionsRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

@QuarkusTest
class TermsConditionsServiceTest {

    @Mock
    TermsConditionsService termsConditionsService;

    @Mock
    TermsConditionsRepository termsConditionsRepository;

    private TermsConditions terms;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        LocalDate fecha = LocalDate.now();
        terms = TermsConditions.builder()
                .texto("test")
                .version(1)
                .fechaGeneracion(fecha)
                .build();
    }

    @Test
    void addTermsConditionsTest(){
        LocalDate fecha = LocalDate.now();

        var termsDTO = TermsConditionsDTO.builder()
                .texto("test")
                .version(1)
                .fechaGeneracion(fecha)
                .build();

        Mockito.when(termsConditionsRepository.findAllTerms()).thenReturn(Uni.createFrom().item(1));
        Mockito.when(termsConditionsRepository.persist(terms)).thenReturn(Uni.createFrom().item(terms));

        termsConditionsService.addTermsConditions(termsDTO).subscribe().with(termsConditionsDTO -> {
           Assertions.assertEquals(fecha,termsConditionsDTO.getFechaGeneracion());
           Assertions.assertEquals("test",termsConditionsDTO.getTexto());
       });

    }

}