package tn.esprit.spring.entities.dto;

import java.util.Date;
import java.util.List;

import lombok.*;
import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.Ville;
import tn.esprit.spring.entities.Voyageur;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voyagedto {

private Long idVoyage;
	

	long codeVoyage;
	
	private Ville gareDepart;
	
	
	private Ville gareArrivee;
	
	
	private Date dateDepart;
	private Date dateArrivee;
	
	private double heureDepart;
	
	private double heureArrivee;
	
	Train train;
	
	
	private  List<Voyageur> mesVoyageurs;
}
