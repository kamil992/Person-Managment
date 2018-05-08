package pl.kamilbiernacki.usermanagment.models.services;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Getter
    @Setter
    private boolean isLogin;
}
