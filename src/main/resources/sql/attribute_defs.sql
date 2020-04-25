
INSERT INTO ontology(id, authors, copyright, description, licence, ontology_name, version, documentationurl) VALUES ('ontology_attribute1', 'Bob', '2017 Ontology.org', 'Ontology.org', 'Apache', 'Ontology.org', '17', 'https://ontology.org');

INSERT INTO scale (id, data_type, decimal_places, scale_name, valid_value_min, valid_value_max, ontology_id) VALUES ('scale_attribute1', 4, 1, 'Centimeter', '0', '99999', 'ontology_attribute1');
INSERT INTO method(id, description, formula, method_class, name, reference, ontology_id) VALUES ('method_attribute1', 'Standard rolled measuring tape', 'a^2 + b^2 = c^2', 'Numeric', 'Tape Measure', 'google.com', 'ontology_attribute1');
INSERT INTO trait (id, attribute, entity, main_abbreviation, trait_description, trait_name, status, trait_class, ontology_id) VALUES ('trait_attribute1', 'height', 'stalk', 'H', 'plant height', 'Plant Height', 'active', 'Numeric', 'ontology_attribute1');

INSERT INTO germplasm_attribute_definition (id, default_value, documentationurl, growth_stage, institution, "language", scientist, status, submission_timestamp, crop_id, method_id, ontology_id, scale_id, trait_id, attribute_category, code, "datatype", description, "name", uri) VALUES ('attribute1', '10', 'https://brapi.org', '1', 'Cornell', 'English', 'Bob', 'active', '2011-06-14T22:12:51Z', '1', 'method_attribute1', 'ontology_attribute1', 'scale_attribute1', 'trait_attribute1', 'Morphological', 'RHT', 'Categorical', 'Allele of marker 11_4769', 'Rht-B1b', 'http://www.brapi.org/ontology/MO_123:1000001');

INSERT INTO variable_base_entity_context_of_use (variable_base_entity_id, context_of_use) VALUES('attribute1', 'Trial evaluation');
INSERT INTO variable_base_entity_context_of_use (variable_base_entity_id, context_of_use) VALUES('attribute1', 'Nursery evaluation');
INSERT INTO variable_base_entity_synonyms (variable_base_entity_id, synonyms) VALUES('attribute1', 'Maize Height');
INSERT INTO variable_base_entity_synonyms (variable_base_entity_id, synonyms) VALUES('attribute1', 'Stalk Height');
INSERT INTO variable_base_entity_synonyms (variable_base_entity_id, synonyms) VALUES('attribute1', 'Corn Height');

INSERT INTO additional_info(id, key, value) VALUES ('attribute_ai_1', 'dummyData', 'True');
INSERT INTO variable_base_entity_additional_info(variable_base_entity_id, additional_info_id) VALUES ('attribute1', 'attribute_ai_1');
INSERT INTO external_reference(id, external_reference_id, external_reference_source) VALUES ('attribute_er_1', 'https://brapi.org/specification', 'BrAPI Doc');
INSERT INTO variable_base_entity_external_references(variable_base_entity_id, external_references_id) VALUES ('attribute1', 'attribute_er_1');