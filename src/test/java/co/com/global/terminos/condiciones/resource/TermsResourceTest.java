package co.com.global.terminos.condiciones.resource;

import co.com.global.terminos.condiciones.dto.TermsConditionsDTO;
import co.com.global.terminos.condiciones.model.TermsConditions;
import co.com.global.terminos.condiciones.model.repository.TermsConditionsRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class TermsResourceTest {

    private TermsConditionsDTO terminos;
    private TermsConditions terminosCon;

    @InjectMock
    TermsConditionsRepository termsConditionsRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);//inicia todos los mocks

        LocalDate date = LocalDate.now();
        terminos = TermsConditionsDTO.builder()
                .texto("terminos prueba.....").
                version(1).
                fechaGeneracion(date)
                .build();

        terminosCon = TermsConditions.builder()
                .texto("terminos prueba.....").
                version(1).
                fechaGeneracion(date)
                .build();
    }

    @Test
     void testcreateTermsConditionsPost(){

        Mockito.when(termsConditionsRepository.findAllTerms()).thenReturn(Uni.createFrom().item(1));
        Mockito.when(termsConditionsRepository.persist(Mockito.any(TermsConditions.class))).thenReturn(Uni.createFrom().item(terminosCon));

       given().when().
                header("Content-Type", "application/json")
                .body(terminos)
                .post("/api/terms")
                .then()
                .statusCode(200);

    }

    @Test
    void testgetTermsConditios(){

        LocalDate date = LocalDate.now();

        List<TermsConditions> listTerms = new ArrayList<>();
        listTerms.add(new TermsConditions("test 1",1, date));
        listTerms.add(new TermsConditions("test 2",2, date));
        listTerms.add(new TermsConditions("test 3",3, date));

        Mockito.when(termsConditionsRepository.findList()).thenReturn(Uni.createFrom().item(listTerms));

        given().when()
                .header("Content-Type", "application/json")
                .get("/api/terms/consultar")
                .then()
                .statusCode(200)
                .body("texto", Is.is("test 3"))
                .body("version",Is.is(3));

    }

}