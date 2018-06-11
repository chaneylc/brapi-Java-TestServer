package org.brapi.test.BrAPITestServer.service;

import java.util.List;
import java.util.Optional;

import org.brapi.test.BrAPITestServer.model.entity.ObservationUnitEntity;
import org.brapi.test.BrAPITestServer.model.entity.PlateEntity;
import org.brapi.test.BrAPITestServer.model.entity.SampleEntity;
import org.brapi.test.BrAPITestServer.repository.ObservationUnitRepository;
import org.brapi.test.BrAPITestServer.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.swagger.model.Metadata;
import io.swagger.model.NewSampleDbIdResult;
import io.swagger.model.Sample;
import io.swagger.model.SampleSearchRequest;

@Service
public class SampleService {
	private SampleRepository sampleRepository;
	private ObservationUnitRepository observationUnitRepository;

	@Autowired
	public SampleService(SampleRepository sampleRepository, ObservationUnitRepository observationUnitRepository) {
		this.sampleRepository = sampleRepository;
		this.observationUnitRepository = observationUnitRepository;
	}

	public NewSampleDbIdResult saveSample(Sample sample) {
		Optional<ObservationUnitEntity> unitOpt = this.observationUnitRepository.findById(sample.getObservationUnitDbId());
		NewSampleDbIdResult id = new NewSampleDbIdResult();
		if (validateInput(sample, unitOpt)) {
			ObservationUnitEntity unit = unitOpt.get();
			SampleEntity entity = new SampleEntity();
			entity.setNotes(sample.getNotes());
			entity.setObservationUnit(unit);
			entity.setPlate(new PlateEntity());
			entity.getPlate().setId(sample.getPlateDbId());
			entity.setPlateIndex(sample.getPlateIndex());
			entity.setSampleTimestamp(DateUtility.toDate(sample.getSampleTimestamp()));
			entity.setSampleType(sample.getSampleType());
			entity.setTakenBy(sample.getTakenBy());
			entity.setTissueType(sample.getTissueType());

			entity = sampleRepository.save(entity);

			id.setSampleId(entity.getId());
			id.setSampleDbId(entity.getId());
		}
		return id;

	}

	private boolean validateInput(Sample sample, Optional<ObservationUnitEntity> unitOpt) {
		boolean valid = unitOpt.isPresent();
		if(valid) {
			ObservationUnitEntity unit = unitOpt.get();
			valid = valid && isNullEmptyOrEqual(sample.getGermplasmDbId(), unit.getGermplasm().getId());
			valid = valid && isNullEmptyOrEqual(sample.getStudyDbId(), unit.getStudy().getId());
			valid = valid && isNullEmptyOrEqual(sample.getPlotDbId(), String.valueOf(unit.getPlotNumber()));
			valid = valid && isNullEmptyOrEqual(sample.getPlantDbId(), String.valueOf(unit.getPlantNumber()));
		}
		return valid;
	}

	private boolean isNullEmptyOrEqual(String actual, String expected) {
		return actual == null || actual.equalsIgnoreCase("") || actual.equals(expected);
	}

	public Sample getSample(String sampleId) {

		Optional<SampleEntity> detailOptional = sampleRepository.findById(sampleId);
		Sample sample = null;

		if (detailOptional.isPresent()) {
			sample = convertFromEntity(detailOptional.get());
		}
		return sample;
	}

	private Sample convertFromEntity(SampleEntity entity) {
		Sample sample = new Sample();
		sample.setGermplasmDbId(entity.getObservationUnit().getGermplasm().getId());
		sample.setNotes(entity.getNotes());
		sample.setObservationUnitDbId(entity.getObservationUnit().getId());
		sample.setPlateDbId(entity.getPlate().getId());
		sample.setPlateIndex(entity.getPlateIndex());
		sample.setPlantDbId(String.valueOf(entity.getObservationUnit().getPlantNumber()));
		sample.setPlotDbId(String.valueOf(entity.getObservationUnit().getPlotNumber()));
		sample.setSampleDbId(entity.getId());
		sample.setSampleTimestamp(DateUtility.toOffsetDateTime(entity.getSampleTimestamp()));
		sample.setSampleType(entity.getSampleType());
		sample.setStudyDbId(entity.getObservationUnit().getStudy().getId());
		sample.setTakenBy(entity.getTakenBy());
		sample.setTissueType(entity.getTissueType());
		
		return sample;
	}
	
	public List<Sample> getSampleSearch(String sampleDbId, String observationUnitDbId, String plateDbId,
			String germplasmDbId, Metadata metaData) {
		return searchSamples(SearchUtility.buildSearchParam(sampleDbId),
				SearchUtility.buildSearchParam(observationUnitDbId),
				SearchUtility.buildSearchParam(plateDbId),
				SearchUtility.buildSearchParam(germplasmDbId),
				metaData);
	}

	public List<Sample> getSampleSearch(SampleSearchRequest request, Metadata metaData) {
		return searchSamples(SearchUtility.buildSearchParam(request.getSampleDbId()),
				SearchUtility.buildSearchParam(request.getObservationUnitDbId()),
				SearchUtility.buildSearchParam(request.getPlateDbId()),
				SearchUtility.buildSearchParam(request.getGermplasmDbId()),
				metaData);
	}

	private List<Sample> searchSamples(List<String> sampleDbIds, List<String> observationUnitDbIds,
			List<String> plateDbIds, List<String> germplasmDbIds, Metadata metaData) {
		
		Pageable pageReq = PagingUtility.getPageRequest(metaData);
		Page<SampleEntity> entitiesPage = sampleRepository.findBySearch(sampleDbIds, observationUnitDbIds, plateDbIds, germplasmDbIds, pageReq);
		
		PagingUtility.calculateMetaData(metaData, entitiesPage);
		
		return entitiesPage.map(this :: convertFromEntity).getContent();
	}

}
