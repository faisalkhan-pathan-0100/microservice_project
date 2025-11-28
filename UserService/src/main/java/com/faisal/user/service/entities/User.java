package com.faisal.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name ="micro_users")
public class User {

	@Id
	@Column(name ="ID")
	private String userId;
	
	@Column(name ="NAME" ,length =30)
	private String name;
	
	@Column(name ="EMAIL",unique= true, nullable =false)
	private String email;
	
	@Column(name ="ABOUT")
	private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
