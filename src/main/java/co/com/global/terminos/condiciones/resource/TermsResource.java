package co.com.global.terminos.condiciones.resource;

import co.com.global.terminos.condiciones.Service.AcepTermsConditionsService;
import co.com.global.terminos.condiciones.Service.TermsConditionsService;
import co.com.global.terminos.condiciones.dto.AcepTermsConditionsDTO;
import co.com.global.terminos.condiciones.dto.TermsConditionsDTO;

import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;

@Path("/api/terms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TermsResource {

    @Inject
    TermsConditionsService termsConditionsService;

    @Inject
    AcepTermsConditionsService acepTermsConditionsService;

    @POST
    public Uni<Response> createTermsConditions(TermsConditionsDTO termsConditionsDTO) {
        return termsConditionsService.addTermsConditions(termsConditionsDTO)
                .map(termsConditions ->Response.ok(termsConditions).build());
    }

    @GET
    @Path("/consultar")
    @Produces(APPLICATION_JSON)
    public Uni<Response> getTermsConditios(){
        return termsConditionsService.getLatestTermsCondition()
                .map(termsConditions -> Response.ok(termsConditions).build());
    }

    @POST
    @Path("/agree")
    @Produces(APPLICATION_JSON)
    public Uni<Response> createAcepTermsConditions(AcepTermsConditionsDTO acepTermsConditionsDTO) {
        if(acepTermsConditionsDTO.getTipoDocumentoCliente().equalsIgnoreCase("C")||
                acepTermsConditionsDTO.getTipoDocumentoCliente().equalsIgnoreCase("P")){

            return acepTermsConditionsService.addAcepTermsCondition(acepTermsConditionsDTO)
                    .map(acepTermsC -> Response.ok(acepTermsC).build())
                    .onFailure().
                    recoverWithItem(() -> Response.status(NOT_ACCEPTABLE).build());
        }
        return Uni.createFrom().item(Response.status(NOT_ACCEPTABLE).build());
    }

}