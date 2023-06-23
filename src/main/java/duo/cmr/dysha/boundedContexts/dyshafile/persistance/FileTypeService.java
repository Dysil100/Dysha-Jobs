package duo.cmr.dysha.boundedContexts.dyshafile.persistance;

import io.micronaut.context.annotation.Bean;

import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;


public class FileTypeService {
    public FileTypeService() {
    }
    private  final Map<String, String> tableTypes = new HashMap<>(Map.of("CV_document", "pdf", "Contrat_document", "pdf", "Last_Diplome_document", "pdf","ID_Card_document",  "image", "Job_photo_image", "image", "Profil_photo_image", "image", "DyshaJobs_Logo", "image"));
    public  String defineFiletypeByTybleName(String tablename) {
        return tableTypes.getOrDefault(tablename, "Unknown: Table not found");
    }
    public  String determineFileTypeByFirstBytes(byte[] fileBytes) {
        if (fileBytes.length >= 3 && fileBytes[0] == (byte) 0xFF && fileBytes[1] == (byte) 0xD8 && fileBytes[2] == (byte) 0xFF) {
            return "image";
        }
        if (fileBytes.length >= 4 && fileBytes[0] == (byte) 0x25 && fileBytes[1] == (byte) 0x50 && fileBytes[2] == (byte) 0x44 && fileBytes[3] == (byte) 0x46) {
            return "pdf";
        }
        if (fileBytes.length >= 4 && fileBytes[0] == (byte) 0x66 && fileBytes[1] == (byte) 0x74 && fileBytes[2] == (byte) 0x79 && fileBytes[3] == (byte) 0x70) {
            if (fileBytes.length >= 8 && fileBytes[4] == (byte) 0x6D && fileBytes[5] == (byte) 0x70 && fileBytes[6] == (byte) 0x34 && fileBytes[7] == (byte) 0x32) {
                return "video/mp4";
            }
        }
        if (fileBytes.length >= 3 && fileBytes[0] == (byte) 0x49 && fileBytes[1] == (byte) 0x44 && fileBytes[2] == (byte) 0x33) {
            return "audio/mp3";
        }
        return "unknown";
    }

 public  String determineFileType(byte[] fileBytes) {
        if (isImage(fileBytes)) {
            return "image";
        } else if (isPdf(fileBytes)) {
            return "pdf";
        } else if (isAudio(fileBytes)) {
            return "mp3/audio";
        } else if (isVideo(fileBytes)) {
            return "mp4/video";
        } else {
            return "unknown";
        }
    }

    private  boolean isImage(byte[] fileBytes) {
        // Formats d'image courants avec leur en-tête correspondant
        Map<String, byte[]> imageFormats = new HashMap<>();
        imageFormats.put("jpg", new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0});
        imageFormats.put("png", new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47});
        imageFormats.put("gif", new byte[]{0x47, 0x49, 0x46, 0x38});

        // Vérifier l'en-tête du fichier pour les formats d'image
        for (Map.Entry<String, byte[]> entry : imageFormats.entrySet()) {
            byte[] formatHeader = entry.getValue();
            if (startsWith(fileBytes, formatHeader)) {
                return true;
            }
        }
        return false;
    }

    private  boolean isPdf(byte[] fileBytes) {
        byte[] pdfHeader = new byte[]{0x25, 0x50, 0x44, 0x46}; // En-tête du format PDF
        return startsWith(fileBytes, pdfHeader);
    }

    private  boolean isAudio(byte[] fileBytes) {
        // Formats audio courants avec leur en-tête correspondant
        Map<String, byte[]> audioFormats = new HashMap<>();
        audioFormats.put("mp3", new byte[]{0x49, 0x44, 0x33});
        audioFormats.put("wav", new byte[]{0x52, 0x49, 0x46, 0x46});

        // Vérifier l'en-tête du fichier pour les formats audio
        for (Map.Entry<String, byte[]> entry : audioFormats.entrySet()) {
            byte[] formatHeader = entry.getValue();
            if (startsWith(fileBytes, formatHeader)) {
                return true;
            }
        }
        return false;
    }

    private  boolean isVideo(byte[] fileBytes) {
        // Formats vidéo courants avec leur en-tête correspondant
        Map<String, byte[]> videoFormats = new HashMap<>();
        videoFormats.put("mp4", new byte[]{0x00, 0x00, 0x00, 0x18, 0x66, 0x74, 0x79, 0x70});
        videoFormats.put("avi", new byte[]{0x52, 0x49, 0x46, 0x46});

        // Vérifier l'en-tête du fichier pour les formats vidéo
        for (Map.Entry<String, byte[]> entry : videoFormats.entrySet()) {
            byte[] formatHeader = entry.getValue();
            if (startsWith(fileBytes, formatHeader)) {
                return true;
            }
        }
        return false;
    }

    private  boolean startsWith(byte[] array, byte[] prefix) {
        if (array.length < prefix.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (array[i] != prefix[i]) {
                return false;
            }
        }
        return true;
    }

    public  byte[] getDataBytes(Part file) {
        byte[] filesDataBytes = new byte[(int) file.getSize()];
        try (InputStream is = file.getInputStream()) {
            int read = is.read(filesDataBytes);
            while(read != -1){
                read = is.read(filesDataBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesDataBytes;
    }

    public  byte[] getCompressedDataBytes(Part file) throws IOException {
        return compressBytes(getDataBytes(file));
    }

    // TODO: 18.05.2023 Ameliorer cette methode qui compresse mais detruit le fichier qui nest plus telchargeable.
    //  car on en aura besoin pour les fichier plus lourds
    public  byte[] compressBytes(byte[] inputBytes) throws IOException {
        // Créer un flux de sortie pour les données compressées
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, new Deflater());
        // Écrire les données d'entrée compressées dans le flux de sortie
        deflaterOutputStream.write(inputBytes);
        deflaterOutputStream.finish();
        deflaterOutputStream.close();
        // Récupérer les données compressées sous forme de tableau de bytes
        byte[] compressedBytes = outputStream.toByteArray();
        // Fermer le flux de sortie
        outputStream.close();
        return compressedBytes;
    }

}
