package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.Ville;
import tn.esprit.spring.entities.Voyage;
import tn.esprit.spring.entities.Voyageur;
import tn.esprit.spring.entities.etatTrain;
import tn.esprit.spring.entities.dto.Traindto;
import tn.esprit.spring.repository.TrainRepository;
import tn.esprit.spring.repository.VoyageRepository;
import tn.esprit.spring.repository.VoyageurRepository;

@Slf4j
@Service
public class TrainServiceImpl implements ITrainService {


    @Autowired
    VoyageurRepository voyageurRepository;


    @Autowired
    TrainRepository trainRepository;

    @Autowired
    VoyageRepository voyageRepository;


    public void ajouterTrain(Traindto t) {

        trainRepository.save(Train.builder()
        		.idTrain(t.getIdTrain())
        		.codeTrain(t.getCodeTrain())
        		.etat(t.getEtat())
        		.nbPlaceLibre(t.getNbPlaceLibre())
        		.build()
        		);
    }

    public int trainPlacesLibres(Ville nomGareDepart) {
        int cpt = 0;
        int occ = 0;
        List<Voyage> listvoyage = (List<Voyage>) voyageRepository.findAll();

        for (int i = 0; i < listvoyage.size(); i++) {
            if (listvoyage.get(i).getGareDepart() == nomGareDepart) {
                cpt = cpt + listvoyage.get(i).getTrain().getNbPlaceLibre();
                occ = occ + 1;
            } 
        }
        if (occ != 0 ) {
        return cpt / occ;
        } else 
        	return 0;
    }


    public List<Train> listerTrainsIndirects(Ville nomGareDepart, Ville nomGareArrivee) {

        List<Train> lestrainsRes = new ArrayList<>();
        List<Voyage> lesvoyage ;
        lesvoyage = (List<Voyage>) voyageRepository.findAll();
        for (int i = 0; i < lesvoyage.size(); i++) {
            if (lesvoyage.get(i).getGareDepart() == nomGareDepart) {
                for (int j = 0; j < lesvoyage.size(); j++) {
                    if (lesvoyage.get(i).getGareArrivee() == lesvoyage.get(j).getGareDepart() && lesvoyage.get(j).getGareArrivee() == nomGareArrivee) {
                        lestrainsRes.add(lesvoyage.get(i).getTrain());
                        lestrainsRes.add(lesvoyage.get(j).getTrain());

                    }
                }
            }
        }


        return lestrainsRes;
        //
    }


    @Transactional
    public void affecterTainAVoyageur(Long idVoyageur, Ville nomGareDepart, Ville nomGareArrivee, double heureDepart) {


		Optional<Voyageur>  lesvoyageur = voyageurRepository.findById(idVoyageur);
        List<Voyage> lesvoyages ;
        lesvoyages = voyageRepository.rechercheVoyage(nomGareDepart, nomGareDepart, heureDepart);
        for (int i = 0; i < lesvoyages.size(); i++) {
            if (lesvoyages.get(i).getTrain().getNbPlaceLibre() != 0) {
            	if( lesvoyageur.isPresent()) {
                lesvoyages.get(i).getMesVoyageurs().add(lesvoyageur.get());
            	}
                lesvoyages.get(i).getTrain().setNbPlaceLibre(lesvoyages.get(i).getTrain().getNbPlaceLibre() - 1);
            
            	}
            voyageRepository.save(lesvoyages.get(i));
        }
    }

    @Override
    public void desaffecterVoyageursTrain(Ville nomGareDepart, Ville nomGareArrivee, double heureDepart) {
       int j = 0;
       int i = 0;
    	List<Voyage> lesvoyages = voyageRepository.rechercheVoyage(nomGareDepart, nomGareArrivee, heureDepart);
        List<Voyageur> voyageur = lesvoyages.get(i).getMesVoyageurs();
        
        for ( i = 0; i < lesvoyages.size(); i++) {
            while (j < voyageur.size()) {
            if(	voyageur.isEmpty()) {
            	voyageur.remove(j);
            j--;
            }else {j++;}
            	
            }
            
            lesvoyages.get(i).getTrain().setNbPlaceLibre(lesvoyages.get(i).getTrain().getNbPlaceLibre() + 1);
            lesvoyages.get(i).getTrain().setEtat(etatTrain.PREVU);
            voyageRepository.save(lesvoyages.get(i));
            trainRepository.save(lesvoyages.get(i).getTrain());
        }
    }

    @Scheduled(fixedRate = 2000)
    public void trainsEnGare() {
        List<Voyage> lesvoyages ;
        lesvoyages = (List<Voyage>) voyageRepository.findAll();

        Date date = new Date();
        for (int i = 0; i < lesvoyages.size(); i++) {
            if (lesvoyages.get(i).getDateArrivee().before(date)) {
            	log.info("les trains sont " + lesvoyages.get(i).getTrain().getCodeTrain());
            }
           
        }
    }


}

    
