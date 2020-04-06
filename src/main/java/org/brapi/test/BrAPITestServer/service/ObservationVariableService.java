package org.brapi.test.BrAPITestServer.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.brapi.test.BrAPITestServer.exceptions.BrAPIServerException;
import org.brapi.test.BrAPITestServer.model.entity.GermplasmAttributeDefinitionEntity;
import org.brapi.test.BrAPITestServer.model.entity.ObservationVariableEntity;
import org.brapi.test.BrAPITestServer.model.entity.VariableBaseEntity;
import org.brapi.test.BrAPITestServer.repository.ObservationVariableRepository;
import org.brapi.test.BrAPITestServer.utility.DateUtility;
import org.brapi.test.BrAPITestServer.utility.PagingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.swagger.model.common.Metadata;
import io.swagger.model.germ.GermplasmAttributeNewRequest;
import io.swagger.model.pheno.Method;
import io.swagger.model.pheno.ObservationVariable;
import io.swagger.model.pheno.ObservationVariableSearchRequest;
import io.swagger.model.pheno.Scale;
import io.swagger.model.pheno.Trait;
import io.swagger.model.pheno.VariableBaseClass;

@Service
public class ObservationVariableService {
	private ObservationVariableRepository observationVariableRepository;
	private CropService cropService;
	private OntologyService ontologyService;

	@Autowired
	public ObservationVariableService(ObservationVariableRepository observationVariableRepository,
			OntologyService ontologyService, CropService cropService) {
		this.observationVariableRepository = observationVariableRepository;
		this.ontologyService = ontologyService;
		this.cropService = cropService;
	}

//	public List<String> getDataTypes(Metadata metaData) {
//		Pageable pageReq = PagingUtility.getPageRequest(metaData);
//
//		Page<String> dataTypesPage = observationVariableRepository.findDistinctScale_DatatypeAll(pageReq);
//
//		PagingUtility.calculateMetaData(metaData, dataTypesPage);
//		return dataTypesPage.getContent();
//	}
//
//	public List<ObservationVariable> getVariables(String traitClass, Metadata metaData) {
//		Pageable pageReq = PagingUtility.getPageRequest(metaData);
//
//		Page<ObservationVariableEntity> variablesPage;
//		if (traitClass == null) {
//			variablesPage = observationVariableRepository.findAll(pageReq);
//		} else {
//			variablesPage = observationVariableRepository.findAllByTrait_TraitClass(traitClass, pageReq);
//		}
//		List<ObservationVariable> variables = variablesPage.map(this::convertFromEntity).getContent();
//
//		PagingUtility.calculateMetaData(metaData, variablesPage);
//
//		return variables;
//	}

	public ObservationVariable getVariable(String observationVariableDbId) {
		Optional<ObservationVariableEntity> entityOption = observationVariableRepository
				.findById(observationVariableDbId);
		ObservationVariable var = null;
		if (entityOption.isPresent()) {
			var = convertFromEntity(entityOption.get());
		}
		return var;
	}

//	public List<ObservationVariable> getVariables(ObservationVariableSearchRequest request, Metadata metaData) {
//		Pageable pageReq = PagingUtility.getPageRequest(metaData);
//
//		ObservationVariableSearchRequest req = convertSearchRequest(request);
//		Page<ObservationVariableEntity> variablesPage = observationVariableRepository.findBySearch(req, pageReq);
//
//		PagingUtility.calculateMetaData(metaData, variablesPage);
//		return variablesPage.map(this::convertFromEntity).getContent();
//	}

	private ObservationVariableSearchRequest convertSearchRequest(ObservationVariableSearchRequest request) {
		ObservationVariableSearchRequest req = new ObservationVariableSearchRequest();
		req.setDataTypes(request.getDataTypes());
		req.setMethodDbIds(request.getMethodDbIds());
		req.setObservationVariableDbIds(request.getObservationVariableDbIds());
		// req.setObservationVariableXrefs(request.getObservationVariableXrefs());
		req.setOntologyDbIds(request.getOntologyDbIds());
		req.setScaleDbIds(request.getScaleDbIds());
		req.setTraitClasses(request.getTraitClasses());
		return req;
	}

	private ObservationVariable convertFromEntity(ObservationVariableEntity entity) {
		ObservationVariable var = new ObservationVariable();
		convertFromBaseEntity(entity, var);
		// var.setDate(DateUtility.toDateString(new Date()));
		// var.setName(entity.getName());
		var.setObservationVariableName(entity.getName());
		var.setObservationVariableDbId(entity.getId());

		return var;
	}

	public void convertFromBaseEntity(VariableBaseEntity entity, VariableBaseClass var) {
		var.setAdditionalInfo(entity.getAdditionalInfoMap());
		if (entity.getCrop() != null)
			var.setCommonCropName(entity.getCrop().getCropName());
		var.setContextOfUse(entity.getContextOfUse());
		var.setDefaultValue(entity.getDefaultValue());
		var.setDocumentationURL(entity.getDocumentationURL());
		var.setExternalReferences(entity.getExternalReferencesMap());
		var.setGrowthStage(entity.getGrowthStage());
		var.setInstitution(entity.getInstitution());
		var.setLanguage(entity.getLanguage());
		var.setMethod(ontologyService.convertFromEntity(entity.getMethod()));
		var.setOntologyReference(ontologyService.convertFromEntityToRef(entity.getOntology()));
		var.setScale(ontologyService.convertFromEntity(entity.getScale()));
		var.setScientist(entity.getScientist());
		var.setStatus(entity.getStatus());
		var.setSubmissionTimestamp(DateUtility.toOffsetDateTime(entity.getSubmissionTimestamp()));
		var.setSynonyms(entity.getSynonyms());
		var.setTrait(ontologyService.convertFromEntity(entity.getTrait()));
	}

	public void updateBaseEntity(VariableBaseEntity entity, @Valid VariableBaseClass request) throws BrAPIServerException {

		if (request.getAdditionalInfo() != null)
			entity.setAdditionalInfo(request.getAdditionalInfo());
		if (request.getCommonCropName() != null)
			entity.setCrop(cropService.getCropEntity(request.getCommonCropName()));
		if (request.getContextOfUse() != null)
			entity.setContextOfUse(request.getContextOfUse());
		if (request.getDefaultValue() != null)
			entity.setDefaultValue(request.getDefaultValue());
		if (request.getDocumentationURL() != null)
			entity.setDocumentationURL(request.getDocumentationURL());
		if (request.getExternalReferences() != null)
			entity.setExternalReferences(request.getExternalReferences());
		if (request.getGrowthStage() != null)
			entity.setGrowthStage(request.getGrowthStage());
		if (request.getInstitution() != null)
			entity.setInstitution(request.getInstitution());
		if (request.getLanguage() != null)
			entity.setLanguage(request.getLanguage());
		if (request.getMethod() != null)
			entity.setMethod(ontologyService.updateEntity(request.getMethod()));
		if (request.getOntologyReference() != null)
			entity.setOntology(ontologyService.updateEntityFromRef(request.getOntologyReference()));
		if (request.getScale() != null)
			entity.setScale(ontologyService.updateEntity(request.getScale()));
		if (request.getScientist() != null)
			entity.setScientist(request.getScientist());
		if (request.getStatus() != null)
			entity.setStatus(request.getStatus());
		if (request.getSubmissionTimestamp() != null)
			entity.setSubmissionTimestamp(DateUtility.toDate(request.getSubmissionTimestamp()));
		if (request.getSynonyms() != null)
			entity.setSynonyms(request.getSynonyms());
		if (request.getTrait() != null)
			entity.setTrait(ontologyService.updateEntity(request.getTrait()));

	}

//	public List<ObservationVariable> getVariablesForStudy(String studyDbId, Metadata metadata) {
//		Pageable pageReq = PagingUtility.getPageRequest(metadata);
//		Page<ObservationVariableEntity> page = observationVariableRepository.findAllForStudy(studyDbId, pageReq);
//		PagingUtility.calculateMetaData(metadata, page);
//		return page.map(this::convertFromEntity).getContent();
//	}
//
//	public ObservationVariableEntity getVariableEntity(String observationVariableDbId) {
//		return observationVariableRepository.findById(observationVariableDbId).orElse(null);
//	}
//
//	public List<ObservationVariable> search(ObservationVariableSearchRequest request, Metadata metadata)
//			throws BrAPIServerException {
//		Pageable pageReq = PagingUtility.getPageRequest(metadata);
//		Page<ObservationVariableEntity> page = observationVariableRepository.findBySearch(request, pageReq);
//		List<ObservationVariable> programs = page.map(this::convertFromEntity).getContent();
//		PagingUtility.calculateMetaData(metadata, page);
//		return programs;
//	}

}
