package pl.kamilbiernacki.usermanagment.models;


import lombok.Data;
import lombok.NoArgsConstructor;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import pl.kamilbiernacki.usermanagment.models.forms.PersonForm;

import javax.persistence.*;



@Table(name = "user_manage")
@Entity
@Data
@NoArgsConstructor
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstname;

    private String lastname;

    private int age;

    private String gender;

    @Email
    private String email;

    private String profession;


    public PersonModel(PersonForm personForm){
        this.firstname = personForm.getFirstname();
        this.lastname = personForm.getLastname();
        this.age = personForm.getAge();
        this.gender = personForm.getGender();
        this.email = personForm.getEmail();
        this.profession = personForm.getProfession();
    }
}
