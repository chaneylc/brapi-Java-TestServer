/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.0).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;
import org.brapi.test.BrAPITestServer.exceptions.BrAPIServerException;

import io.swagger.model.Body;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-06T17:40:52.157-05:00[America/New_York]")

@Api(value = "login", description = "the login API")
public interface LoginApi {

    @ApiOperation(value = "Login", nickname = "loginPost", notes = "Implemented by: Tripal Brapi module, Cassavabase, Germinate, BMS  Used by: Flapjack, BMS  Response data types Exception: the result is not embeded in a \"result\" structure in order to be (one day) OAuth2 compliant. It's also why the anwser mixes snake_case and camelCase. For login, returns a hash with the user name and the token as the value. A metadata key is also present (but usually set to null, unless an error condition occurs).  For logout, returns an empty resource. A token to remove could be provided (amdin interface) but it is not required. By default, current user token will be removed.  |Variable|Datatype|Description|Required|   |------|------|------|:-----:| | userDisplayName| string| the display name of the user | Y | | access_token | string | the access token for the session | Y | | expires_in | integer | The lifetime in seconds of the access token | Y | ", authorizations = {
        @Authorization(value = "AuthorizationToken")
    }, tags={ "Authentication","Deprecated", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created") })
    @RequestMapping(value = "/login",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<String> loginPost(@ApiParam(value = ""  )  @Valid @RequestBody Body body) throws BrAPIServerException;

}