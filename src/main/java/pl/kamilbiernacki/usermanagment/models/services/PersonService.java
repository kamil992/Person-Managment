package pl.kamilbiernacki.usermanagment.models.services;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kamilbiernacki.usermanagment.models.PersonModel;
import pl.kamilbiernacki.usermanagment.models.repositories.PersonRepository;

@Service
public class PersonService {

    @Getter
    @Setter
    private boolean isLogin;

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void savePerson(PersonModel personModel){
        personRepository.save(personModel);
    }

    public void deletePerson(int personId){
        personRepository.delete(personId);
    }

}
