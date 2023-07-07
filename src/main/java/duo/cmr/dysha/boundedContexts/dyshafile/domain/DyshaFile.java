package duo.cmr.dysha.boundedContexts.dyshafile.domain;

import lombok.*;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class DyshaFile {
    private Long id;
    private Long fromUserId;
    private Long forUserId;
    private String tableName;
    private String fileType;
    private String name;
    private byte[] data;

    public DyshaFile(Long fromUserId, Long forUserId, String tableName, String fileType, String name, byte[] data) {
        this.fromUserId = fromUserId;
        this.forUserId = forUserId;
        this.tableName = tableName;
        this.fileType = fileType;
        this.name = name;
        this.data = data;
    }

    public DyshaFile() {
    }

    public HttpHeaders buildHeadersFor(String userName) {
        MediaType mediaType = switch (fileType) {
            case "image/*" -> MediaType.IMAGE_JPEG;
            case "application/pdf" -> MediaType.APPLICATION_PDF;
            case "mp3/audio" -> MediaType.parseMediaType("audio/mpeg");
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(getFilenameFor(userName)).build());
        headers.setContentLength(data.length);

        return headers;
    }

    private String getFilenameFor(String userName) {
        String extention = ".pdf";
        if (fileType.equalsIgnoreCase("image/*")) {
            extention = ".png";
        } else if (fileType.equalsIgnoreCase("mp3/audio")) {
            extention = ".mp3";
        }
        return tableName + '_' + "of_" + userName + extention;
    }

}
