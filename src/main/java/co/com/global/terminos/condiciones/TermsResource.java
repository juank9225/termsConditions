package co.com.global.terminos.condiciones;

import co.com.global.terminos.condiciones.Service.AcepTermsConditionsService;
import co.com.global.terminos.condiciones.Service.TermsConditionsService;
import co.com.global.terminos.condiciones.dto.AcepTermsConditionsDTO;
import co.com.global.terminos.condiciones.dto.TermsConditionsDTO;
import co.com.global.terminos.condiciones.model.AcepTermsConditions;
import co.com.global.terminos.condiciones.model.TermsConditions;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.Optional;
import java.util.function.Predicate;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;


@Produces(MediaType.APPLICATION_JSON)
@Path("/terms")
public class TermsResource {

    @Inject
    TermsConditionsService termsConditionsService;

    @Inject
    AcepTermsConditionsService acepTermsConditionsService;

    @POST
    @Path("/crear")
    @Produces(APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "The URI of the terms and conditions created", content = @Content(mediaType = APPLICATION_JSON,schema = @Schema(implementation = URI.class)))
    public Uni<TermsConditionsDTO> createTermsConditions(TermsConditionsDTO termsConditionsDTO) {
        return termsConditionsService.addTermsConditions(termsConditionsDTO);
    }

    @GET
    @Path("/consultar")
    @Produces(APPLICATION_JSON)
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = TermsConditions.class)))
    @APIResponse(responseCode = "204", description = "No Terms and Conditions")
    public Uni<TermsConditionsDTO> getTermsConditios(){
        return termsConditionsService.getLatestTermsCondition();
    }

    @POST
    @Path("/agree")
    @Produces(APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "The URI of the terms and conditions created", content = @Content(mediaType = APPLICATION_JSON,schema = @Schema(implementation = URI.class)))
    public Uni<Response> createAcepTermsConditions(AcepTermsConditionsDTO acepTermsConditionsDTO) {
        if(acepTermsConditionsDTO.getTipoDocumentoCliente().equalsIgnoreCase("C")||
                acepTermsConditionsDTO.getTipoDocumentoCliente().equalsIgnoreCase("P")){

            return acepTermsConditionsService.addAcepTermsCondition(acepTermsConditionsDTO)
                    .map(acepTermsC -> Response.ok(acepTermsC).build());
        }
        return Uni.createFrom().item(Response.status(NOT_ACCEPTABLE).build());
    }

}