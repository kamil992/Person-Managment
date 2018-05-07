package pl.kamilbiernacki.usermanagment.models.forms;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;


@Data
@NoArgsConstructor
public class PersonForm {
    private int id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @Min(18)
    private int age;
    @NotEmpty
    private String gender;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String profession;
}
