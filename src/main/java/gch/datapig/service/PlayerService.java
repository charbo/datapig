package gch.datapig.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gch.datapig.model.Player;
import gch.datapig.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gch.datapig.exception.RecordNotFoundException;

@Service
public class PlayerService {


    private PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> getAllEmployees() {
        List<Player> result = (List<Player>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Player>();
        }
    }

    public Player getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<Player> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public Player createOrUpdateEmployee(Player entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);

            return entity;
        } else {
            Optional<Player> employee = repository.findById(entity.getId());

            if (employee.isPresent()) {
                Player newEntity = employee.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<Player> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}