/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.18).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api.germ;

import io.swagger.model.common.Model202AcceptedSearchResponse;
import io.swagger.model.germ.GermplasmListResponse;
import io.swagger.model.germ.GermplasmMCPDResponse;
import io.swagger.model.germ.GermplasmNewRequest;
import io.swagger.model.germ.GermplasmPedigreeResponse;
import io.swagger.model.germ.GermplasmSearchRequest;
import io.swagger.model.germ.GermplasmSingleResponse;
import io.swagger.model.germ.ProgenyResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-20T16:33:36.513Z[GMT]")
@Api(value = "germplasm", description = "the germplasm API")
public interface GermplasmApi {

	@ApiOperation(value = "Get the details of a specific Germplasm", nickname = "germplasmGermplasmDbIdGet", notes = "Germplasm Details by germplasmDbId was merged with Germplasm Multi Crop Passport Data. The MCPD fields are optional and marked with the prefix [MCPD].", response = GermplasmSingleResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GermplasmSingleResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@RequestMapping(value = "/germplasm/{germplasmDbId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<GermplasmSingleResponse> germplasmGermplasmDbIdGet(
			@ApiParam(value = "The internal id of the germplasm", required = true) @PathVariable("germplasmDbId") String germplasmDbId,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

	@ApiOperation(value = "Get the details of a specific Germplasm in MCPD format", nickname = "germplasmGermplasmDbIdMcpdGet", notes = "Get all MCPD details of a germplasm  <a target=\"_blank\" href=\"https://www.bioversityInternational.org/fileadmin/user_upload/online_library/publications/pdfs/FAOBIOVERSITY_MULTI-CROP_PASSPORT_DESCRIPTORS_V.2.1_2015_2020.pdf\"> MCPD v2.1 spec can be found here </a>  Implementation Notes  - When the MCPD spec identifies a field which can have multiple values returned, the JSON response should be an array instead of a semi-colon separated string.", response = GermplasmMCPDResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GermplasmMCPDResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@RequestMapping(value = "/germplasm/{germplasmDbId}/mcpd", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<GermplasmMCPDResponse> germplasmGermplasmDbIdMcpdGet(
			@ApiParam(value = "the internal id of the germplasm", required = true) @PathVariable("germplasmDbId") String germplasmDbId,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

	@ApiOperation(value = "Get the pedigree details of a specific Germplasm", nickname = "germplasmGermplasmDbIdPedigreeGet", notes = "Get the parentage information of a specific Germplasm", response = GermplasmPedigreeResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GermplasmPedigreeResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@RequestMapping(value = "/germplasm/{germplasmDbId}/pedigree", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<GermplasmPedigreeResponse> germplasmGermplasmDbIdPedigreeGet(
			@ApiParam(value = "the internal id of the germplasm", required = true) @PathVariable("germplasmDbId") String germplasmDbId,
			@ApiParam(value = "text representation of the pedigree") @Valid @RequestParam(value = "notation", required = false) String notation,
			@ApiParam(value = "include array of siblings in response") @Valid @RequestParam(value = "includeSiblings", required = false) Boolean includeSiblings,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

	@ApiOperation(value = "Get the progeny details of a specific Germplasm", nickname = "germplasmGermplasmDbIdProgenyGet", notes = "Get the germplasmDbIds for all the Progeny of a particular germplasm.  Implementation Notes  - Regarding the ''parentType'' field in the progeny object. Given a germplasm A having a progeny B and C, ''parentType'' for progeny B refers to the ''parentType'' of A toward B.", response = ProgenyResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProgenyResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@RequestMapping(value = "/germplasm/{germplasmDbId}/progeny", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<ProgenyResponse> germplasmGermplasmDbIdProgenyGet(
			@ApiParam(value = "the internal id of the germplasm", required = true) @PathVariable("germplasmDbId") String germplasmDbId,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

	@ApiOperation(value = "Update the details of an existing Germplasm", nickname = "germplasmGermplasmDbIdPut", notes = "Germplasm Details by germplasmDbId was merged with Germplasm Multi Crop Passport Data. The MCPD fields are optional and marked with the prefix [MCPD].", response = GermplasmSingleResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GermplasmSingleResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@RequestMapping(value = "/germplasm/{germplasmDbId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<GermplasmSingleResponse> germplasmGermplasmDbIdPut(
			@ApiParam(value = "The internal id of the germplasm", required = true) @PathVariable("germplasmDbId") String germplasmDbId,
			@ApiParam(value = "") @Valid @RequestBody GermplasmNewRequest body,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

	@ApiOperation(value = "Get a filtered list of Germplasm", nickname = "germplasmGet", notes = "Addresses these needs  - General germplasm search mechanism that accepts POST for complex queries   - Possibility to search germplasm by more parameters than those allowed by the existing germplasm search   - Possibility to get MCPD details by PUID rather than dbId", response = GermplasmListResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GermplasmListResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class) })
	@RequestMapping(value = "/germplasm", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<GermplasmListResponse> germplasmGet(
			@ApiParam(value = "Permanent unique identifier (DOI, URI, etc.)") @Valid @RequestParam(value = "germplasmPUI", required = false) String germplasmPUI,
			@ApiParam(value = "Internal database identifier") @Valid @RequestParam(value = "germplasmDbId", required = false) String germplasmDbId,
			@ApiParam(value = "Name of the germplasm") @Valid @RequestParam(value = "germplasmName", required = false) String germplasmName,
			@ApiParam(value = "The common crop name related to this germplasm") @Valid @RequestParam(value = "commonCropName", required = false) String commonCropName,
			@ApiParam(value = "Unique identifiers for accessions within a genebank") @Valid @RequestParam(value = "accessionNumber", required = false) String accessionNumber,
			@ApiParam(value = "A specific panel/collection/population name this germplasm belongs to.") @Valid @RequestParam(value = "collection", required = false) String collection,
			@ApiParam(value = "Genus name to identify germplasm") @Valid @RequestParam(value = "genus", required = false) String genus,
			@ApiParam(value = "Species name to identify germplasm") @Valid @RequestParam(value = "species", required = false) String species,
			@ApiParam(value = "Search for Germplasm that are associated with a particular Study") @Valid @RequestParam(value = "studyDbId", required = false) String studyDbId,
			@ApiParam(value = "Alternative name or ID used to reference this germplasm") @Valid @RequestParam(value = "synonym", required = false) String synonym,
			@ApiParam(value = "Search for Germplasm with this parent") @Valid @RequestParam(value = "parentDbId", required = false) String parentDbId,
			@ApiParam(value = "Search for Germplasm with this child") @Valid @RequestParam(value = "progenyDbId", required = false) String progenyDbId,
			@ApiParam(value = "Search for Germplasm by an external reference") @Valid @RequestParam(value = "externalReferenceID", required = false) String externalReferenceID,
			@ApiParam(value = "Search for Germplasm by an external reference") @Valid @RequestParam(value = "externalReferenceSource", required = false) String externalReferenceSource,
			@ApiParam(value = "Used to request a specific page of data to be returned.  The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,
			@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

	@ApiOperation(value = "Create new Germplasm entities on this server", nickname = "germplasmPost", notes = "Create new Germplasm entities on this server", response = GermplasmListResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GermplasmListResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class) })
	@RequestMapping(value = "/germplasm", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<GermplasmListResponse> germplasmPost(
			@ApiParam(value = "") @Valid @RequestBody List<GermplasmNewRequest> body,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

	@ApiOperation(value = "Submit a search request for Germplasm", nickname = "searchGermplasmPost", notes = "Search for a set of germplasm based on some criteria  Addresses these needs   - General germplasm search mechanism that accepts POST for complex queries   - Possibility to search germplasm by more parameters than those allowed by the existing germplasm search   - Possibility to get MCPD details by PUID rather than dbId  See Search Services for additional implementation details.", response = GermplasmListResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GermplasmListResponse.class),
			@ApiResponse(code = 202, message = "Accepted", response = Model202AcceptedSearchResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class) })
	@RequestMapping(value = "/search/germplasm", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<GermplasmListResponse> searchGermplasmPost(
			@ApiParam(value = "") @Valid @RequestBody GermplasmSearchRequest body,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

	@ApiOperation(value = "Get the results of a Germplasm search request", nickname = "searchGermplasmSearchResultsDbIdGet", notes = "See Search Services for additional implementation details.  Addresses these needs:   1. General germplasm search mechanism that accepts POST for complex queries   2. possibility to search germplasm by more parameters than those allowed by the existing germplasm search   3. possibility to get MCPD details by PUID rather than dbId", response = GermplasmListResponse.class, authorizations = {
			@Authorization(value = "AuthorizationToken") }, tags = { "Germplasm", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GermplasmListResponse.class),
			@ApiResponse(code = 202, message = "Accepted", response = Model202AcceptedSearchResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class) })
	@RequestMapping(value = "/search/germplasm/{searchResultsDbId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<GermplasmListResponse> searchGermplasmSearchResultsDbIdGet(
			@ApiParam(value = "Permanent unique identifier which references the search results", required = true) @PathVariable("searchResultsDbId") String searchResultsDbId,
			@ApiParam(value = "Used to request a specific page of data to be returned.  The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,
			@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
			@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>") @RequestHeader(value = "Authorization", required = false) String authorization);

}
