package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Voyageur;
import tn.esprit.spring.entities.dto.Voyageurdto;


public interface IVoyageurService {
    void ajouterVoyageur(Voyageurdto voyageur);
    //Modifier le voyageur
    void modifierVoyageur(Voyageur voyageur);
    List<Voyageur> recupererAll();
    Voyageur recupererVoyageParId(long idVoyageur);
    void supprimerVoyageur(Voyageur v);
}
