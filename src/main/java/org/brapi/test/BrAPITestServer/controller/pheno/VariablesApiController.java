package org.brapi.test.BrAPITestServer.controller.pheno;

import io.swagger.model.Metadata;
import io.swagger.model.pheno.ObservationVariable;
import io.swagger.model.pheno.ObservationVariableListResponse;
import io.swagger.model.pheno.ObservationVariableListResponseResult;
import io.swagger.model.pheno.ObservationVariableSingleResponse;
import io.swagger.model.pheno.ObservationVariableNewRequest;
import io.swagger.model.pheno.ObservationVariableSearchRequest;
import io.swagger.api.pheno.VariablesApi;

import org.brapi.test.BrAPITestServer.controller.core.BrAPIController;
import org.brapi.test.BrAPITestServer.exceptions.BrAPIServerException;
import org.brapi.test.BrAPITestServer.service.pheno.ObservationVariableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-20T16:32:22.556Z[GMT]")
@Controller
public class VariablesApiController extends BrAPIController implements VariablesApi {

	private static final Logger log = LoggerFactory.getLogger(VariablesApiController.class);

	private final ObservationVariableService observationVariableService;

	private final HttpServletRequest request;

	@Autowired
	public VariablesApiController(ObservationVariableService observationVariableService, HttpServletRequest request) {
		this.observationVariableService = observationVariableService;
		this.request = request;
	}

	public ResponseEntity<ObservationVariableListResponse> variablesGet(
			@Valid @RequestParam(value = "observationVariableDbId", required = false) String observationVariableDbId,
			@Valid @RequestParam(value = "traitClass", required = false) String traitClass,
			@Valid @RequestParam(value = "studyDbId", required = false) String studyDbId,
			@Valid @RequestParam(value = "externalReferenceID", required = false) String externalReferenceID,
			@Valid @RequestParam(value = "externalReferenceSource", required = false) String externalReferenceSource,
			@Valid @RequestParam(value = "page", required = false) Integer page,
			@Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestHeader(value = "Authorization", required = false) String authorization) throws BrAPIServerException {

		log.debug("Request: " + request.getRequestURI());
		validateAcceptHeader(request);
		Metadata metadata = generateMetaDataTemplate(page, pageSize);
		List<ObservationVariable> data = observationVariableService.findObservationVariables(observationVariableDbId,
				traitClass, studyDbId, externalReferenceID, externalReferenceSource, metadata);
		return responseOK(new ObservationVariableListResponse(), new ObservationVariableListResponseResult(), data,
				metadata);
	}

	public ResponseEntity<ObservationVariableSingleResponse> variablesObservationVariableDbIdGet(
			@PathVariable("observationVariableDbId") String observationVariableDbId,
			@RequestHeader(value = "Authorization", required = false) String authorization)
			throws BrAPIServerException {

		log.debug("Request: " + request.getRequestURI());
		validateAcceptHeader(request);
		ObservationVariable data = observationVariableService.getObservationVariable(observationVariableDbId);
		return responseOK(new ObservationVariableSingleResponse(), data);
	}

	public ResponseEntity<ObservationVariableSingleResponse> variablesObservationVariableDbIdPut(
			@Valid @RequestBody ObservationVariableNewRequest body,
			@PathVariable("observationVariableDbId") String observationVariableDbId,
			@RequestHeader(value = "Authorization", required = false) String authorization)
			throws BrAPIServerException {

		log.debug("Request: " + request.getRequestURI());
		validateAcceptHeader(request);
		ObservationVariable data = observationVariableService.updateObservationVariable(observationVariableDbId, body);
		return responseOK(new ObservationVariableSingleResponse(), data);
	}

	public ResponseEntity<ObservationVariableListResponse> variablesPost(
			@Valid @RequestBody List<ObservationVariableNewRequest> body,
			@RequestHeader(value = "Authorization", required = false) String authorization)
			throws BrAPIServerException {

		log.debug("Request: " + request.getRequestURI());
		validateAcceptHeader(request);
		List<ObservationVariable> data = observationVariableService.saveObservationVariables(body);
		return responseOK(new ObservationVariableListResponse(), new ObservationVariableListResponseResult(), data);
	}

	public ResponseEntity<ObservationVariableListResponse> searchVariablesPost(
			@Valid @RequestBody ObservationVariableSearchRequest body,
			@RequestHeader(value = "Authorization", required = false) String authorization)
			throws BrAPIServerException {

		log.debug("Request: " + request.getRequestURI());
		validateAcceptHeader(request);
		Metadata metadata = generateMetaDataTemplate(body);
		List<ObservationVariable> data = observationVariableService.findObservationVariables(body, metadata);
		return responseOK(new ObservationVariableListResponse(), new ObservationVariableListResponseResult(), data,
				metadata);
	}

	public ResponseEntity<ObservationVariableListResponse> searchVariablesSearchResultsDbIdGet(
			@PathVariable("searchResultsDbId") String searchResultsDbId,
			@Valid @RequestParam(value = "page", required = false) Integer page,
			@Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestHeader(value = "Authorization", required = false) String authorization)
			throws BrAPIServerException {

		log.debug("Request: " + request.getRequestURI());
		validateAcceptHeader(request);
		return new ResponseEntity<ObservationVariableListResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

}
