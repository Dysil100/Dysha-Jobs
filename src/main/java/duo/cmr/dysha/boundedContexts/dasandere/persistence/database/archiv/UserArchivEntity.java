package duo.cmr.dysha.boundedContexts.dasandere.persistence.database.archiv;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@EqualsAndHashCode
@Table("archiv")
public class UserArchivEntity {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;

    public UserArchivEntity(String firstName, String lastName, String email, String telephone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }
}
