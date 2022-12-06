package tn.esprit.spring.services;

import tn.esprit.spring.entities.Voyage;
import tn.esprit.spring.entities.dto.Voyagedto;

import java.util.List;

public interface IVoyageService {

	 void ajouterVoyage(Voyagedto v);
	 void modifierVoyage(Voyage v);
	 void affecterTrainAVoyage(Long idTrain, Long idVoyage);
	 List<Voyage> recupererAll();
	 Voyage recupererVoyageParId(long idVoyage);
	 void supprimerVoyage(Voyage v);

	
}
