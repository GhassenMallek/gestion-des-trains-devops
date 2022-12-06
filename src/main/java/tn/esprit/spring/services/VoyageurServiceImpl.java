package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Voyageur;
import tn.esprit.spring.entities.dto.Voyageurdto;
import tn.esprit.spring.repository.VoyageurRepository;


@Service
public class VoyageurServiceImpl implements IVoyageurService{

	@Autowired
	VoyageurRepository voyageurRepository;

	public void ajouterVoyageur(Voyageurdto voyageur) {
		voyageurRepository.save(Voyageur.builder()
				.idVoyageur(voyageur.getIdVoyageur())
				.nomVoyageur(voyageur.getNomVoyageur())
				.mesvoyages(voyageur.getMesvoyages())
				.build()
				
				);
		
    }

	@Override
	public void modifierVoyageur(Voyageur voyageur) {
		voyageurRepository.save(voyageur);
	}

	@Override
	public List<Voyageur> recupererAll() {
		return (List<Voyageur>) voyageurRepository.findAll();
	}

	@Override
	public Voyageur recupererVoyageParId(long idVoyageur) {
		return voyageurRepository.findById(idVoyageur).orElse(null) ;
	}

	@Override
	public void supprimerVoyageur(Voyageur v) {
		voyageurRepository.delete(v);
	}

}
