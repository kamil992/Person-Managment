package pl.kamilbiernacki.usermanagment.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kamilbiernacki.usermanagment.models.PersonModel;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<PersonModel, Integer> {
    List<PersonModel> findAllByOrderByIdAsc();
    PersonModel findById(int id);
}
