package duo.cmr.dysha.boundedContexts.MySpotify.domain.dyshafiles;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class DyshaFiles {
    private Long id;
    private String name;
    private byte[] data;

    public DyshaFiles(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}
