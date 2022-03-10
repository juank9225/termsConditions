package co.com.global.terminos.condiciones;

import co.com.global.terminos.condiciones.Service.TermsConditionsService;
import co.com.global.terminos.condiciones.dto.TermsConditionsDTO;
import co.com.global.terminos.condiciones.model.TermsConditions;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Produces(MediaType.APPLICATION_JSON)
@Path("/terms")
public class TermsResource {

    @Inject
    TermsConditionsService termsConditionsService;

    @POST
    @Path("/create")
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

}