package tn.esprit.spring.entities.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Voyage;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voyageurdto {

	private 	Long idVoyageur;
	private  String nomVoyageur;
	 private List<Voyage> mesvoyages;


}
