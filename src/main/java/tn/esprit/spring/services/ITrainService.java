package tn.esprit.spring.services;
import java.util.List;

import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.Ville;
import tn.esprit.spring.entities.dto.Traindto;

public interface ITrainService {
     void ajouterTrain(Traindto t);
     void affecterTainAVoyageur(Long   idVoyageur, Ville nomGareDepart, Ville nomGareArrivee,  double heureDepart);
     int trainPlacesLibres(Ville nomGareDepart);
     List<Train> listerTrainsIndirects(Ville nomGareDepart, Ville nomGareArrivee);
     void desaffecterVoyageursTrain(Ville nomGareDepart, Ville nomGareArrivee, double heureDepart);
     void trainsEnGare();
}
