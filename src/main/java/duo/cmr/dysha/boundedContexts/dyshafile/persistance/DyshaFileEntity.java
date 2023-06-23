package duo.cmr.dysha.boundedContexts.dyshafile.persistance;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table("dysha_file")
public class DyshaFileEntity {

    @Id
    private Long id;
    private Long fromUserId;
    private Long forUserId;
    private String tableName;
    private String fileType;
    private String name;
    private byte[] data;


    public DyshaFileEntity(Long fromUserId, Long forUserId, String tableName, String fileType, String name, byte[] data) {
        this.fromUserId = fromUserId;
        this.forUserId = forUserId;
        this.tableName = tableName;
        this.fileType = fileType;
        this.name = name;
        this.data = data;
    }
}
