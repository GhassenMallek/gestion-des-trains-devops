package tn.esprit.spring.entities;





import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voyage  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVoyage;
	

	long codeVoyage;
	
	@Enumerated(EnumType.STRING)
	private Ville gareDepart;
	
	
	@Enumerated(EnumType.STRING)
	private Ville gareArrivee;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateDepart;
	@Temporal(TemporalType.DATE)
	private Date dateArrivee;
	
	private double heureDepart;
	
	private double heureArrivee;
	
	@ManyToOne
	Train train;
	
	
	@ManyToMany
	private  List<Voyageur> mesVoyageurs;


	





	
	


    


	
	
}
