package org.brapi.test.BrAPITestServer.model.entity.germ;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.brapi.test.BrAPITestServer.model.entity.BrAPIBaseEntity;

@Entity
@Table(name="breeding_method")
public class BreedingMethodEntity extends BrAPIBaseEntity {
	@Column
    private String abbreviation;
    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy="breedingMethod")
    private List<GermplasmEntity> germplasm;
    
	public List<GermplasmEntity> getGermplasm() {
		return germplasm;
	}
	public void setGermplasm(List<GermplasmEntity> germplasm) {
		this.germplasm = germplasm;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
        
}