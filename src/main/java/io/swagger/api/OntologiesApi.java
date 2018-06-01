/**
 * NOTE: This class is auto generated by the swagger code generator program (1.0.12-1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.OntologiesResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-01T19:24:22.162Z")

@Api(value = "ontologies", description = "the ontologies API")
public interface OntologiesApi {

    @ApiOperation(value = "Variable ontology list", nickname = "ontologiesGet", notes = "Call to retrieve a list of observation variable ontologies available in the system. <br> <strong>Scope:</strong> CORE.  <strong>Status:</strong> ACCEPTED.", response = OntologiesResponse.class, tags={ "Observation Variables", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OntologiesResponse.class) })
    @RequestMapping(value = "/ontologies",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<OntologiesResponse> ontologiesGet(@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page);

}