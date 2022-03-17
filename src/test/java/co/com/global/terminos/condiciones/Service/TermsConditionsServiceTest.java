package co.com.global.terminos.condiciones.Service;

import co.com.global.terminos.condiciones.dto.TermsConditionsDTO;
import co.com.global.terminos.condiciones.model.TermsConditions;
import co.com.global.terminos.condiciones.model.repository.TermsConditionsRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@QuarkusTest
@DisplayName("Test Servicio Terminos y Condiciones")
class TermsConditionsServiceTest {

    @InjectMocks
    TermsConditionsService termsConditionsService;

    @Mock
    TermsConditionsRepository termsConditionsRepository;

    private TermsConditions terms;
    private LocalDate fecha;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        fecha = LocalDate.now();

        terms = TermsConditions.builder()
                .texto("test")
                .version(1)
                .fechaGeneracion(fecha)
                .build();
    }

    @Test
    void addTermsConditionsTest(){

        var termsDTO = TermsConditionsDTO.builder()
                .texto("test")
                .version(1)
                .fechaGeneracion(fecha)
                .build();

        Mockito.when(termsConditionsRepository.findAllTerms()).thenReturn(Uni.createFrom().item(1));
        Mockito.when(termsConditionsRepository.persist(terms)).thenReturn(Uni.createFrom().item(terms));

        termsConditionsService.addTermsConditions(termsDTO).subscribe().with(termsConditionsDTO -> {
           Assertions.assertEquals("test",termsConditionsDTO.getTexto());
       });

    }

    @Test
    void getLatestTermsConditionTest(){

        LocalDate date = LocalDate.now();

        List<TermsConditions> listTerms = new ArrayList<>();
        listTerms.add(new TermsConditions("test 1",1, date));
        listTerms.add(new TermsConditions("test 2",2, date));
        listTerms.add(new TermsConditions("test 3",3, date));

        Mockito.when(termsConditionsRepository.findList()).thenReturn(Uni.createFrom().item(listTerms));

        termsConditionsService.getLatestTermsCondition().subscribe().with(termsConditionsDTO -> {
            Assertions.assertEquals(1,termsConditionsDTO.getVersion());
        });
    }

}