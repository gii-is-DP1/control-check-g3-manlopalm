package org.springframework.samples.petclinic.recoveryroom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecoveryRoom extends BaseEntity {
    
	@NotNull
	@Size(min=3, max=50)
    private String name;
	
	@NotNull
	@PositiveOrZero
    private double size;
	
	@NotNull
    private boolean secure;
	
    @ManyToOne(optional=false)
    @JoinColumn(name="recovery_room_type_id")
    private RecoveryRoomType roomType;
}
