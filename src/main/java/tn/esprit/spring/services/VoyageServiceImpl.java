package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Train;
import tn.esprit.spring.entities.Voyage;
import tn.esprit.spring.entities.dto.Voyagedto;
import tn.esprit.spring.repository.TrainRepository;
import tn.esprit.spring.repository.VoyageRepository;

@Service
public class VoyageServiceImpl implements IVoyageService {
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    TrainRepository trainRepository;
    @Override
    public void ajouterVoyage(Voyagedto v) {
        voyageRepository.save(Voyage.builder()
        		.idVoyage(v.getIdVoyage())
        		.codeVoyage(v.getCodeVoyage())
        		.dateArrivee(v.getDateArrivee())
        		.dateDepart(v.getDateDepart())
        		.gareArrivee(v.getGareArrivee())
        		.gareDepart(v.getGareDepart())
        		.heureArrivee(v.getHeureArrivee())
        		.heureDepart(v.getHeureDepart())
        		.mesVoyageurs(v.getMesVoyageurs())
        		.train(v.getTrain())
        		.build()
        		);
    }

    @Override
    public void modifierVoyage(Voyage v) {
		voyageRepository.save(v);
    }


    public void affecterTrainAVoyage(Long idTrain, Long idVoyage) {

    	Optional<Voyage>  voyage = voyageRepository.findById(idVoyage);
    	Optional<Train>  train = trainRepository.findById(idTrain);
    	if (train.isPresent() && voyage.isPresent()) {
        Train t = train.get();
        Voyage v = voyage.get();
        v.setTrain(t);
        voyageRepository.save(v);
    }}

    @Override
    public List<Voyage> recupererAll() {
        return (List<Voyage>) voyageRepository.findAll();
    }

    @Override
    public Voyage recupererVoyageParId(long idVoyage) {
        return voyageRepository.findById(idVoyage).orElse(null);
    }

    @Override
    public void supprimerVoyage(Voyage v) {
    	voyageRepository.deleteById(v.getIdVoyage());
    }

}
