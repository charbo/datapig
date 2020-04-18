package gch.datapig.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gch.datapig.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

}
