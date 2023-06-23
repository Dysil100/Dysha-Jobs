package duo.cmr.dysha.boundedContexts.dyshafile.web.configuration;

import duo.cmr.dysha.boundedContexts.dyshafile.persistance.FileTypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFiles {

    @Bean
    public FileTypeService getFileTypeService(){
        return new FileTypeService();
    }
}
