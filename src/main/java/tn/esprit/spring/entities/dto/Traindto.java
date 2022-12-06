package tn.esprit.spring.entities.dto;

import lombok.*;
import tn.esprit.spring.entities.etatTrain;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Traindto {
	 long idTrain;


	    long codeTrain;


	    private etatTrain etat;

	    private int nbPlaceLibre;
}
