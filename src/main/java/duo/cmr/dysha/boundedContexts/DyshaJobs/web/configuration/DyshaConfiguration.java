package duo.cmr.dysha.boundedContexts.DyshaJobs.web.configuration;


import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.dyshafile.persistance.FileTypeService;
import duo.cmr.dysha.boundedContexts.generalhelpers.generalresearch.MyGeneralSearcher;
import duo.cmr.dysha.boundedContexts.generalhelpers.matchers.MyMatchValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@org.springframework.context.annotation.Configuration
@AllArgsConstructor
@EnableWebSecurity
public class DyshaConfiguration {

    @Bean
    public MyGeneralSearcher<DyshaJob> getJoSearcher(){
        return new MyGeneralSearcher<DyshaJob>();
    }

    @Bean
    public MyMatchValidator<DyshaJob> getJobValidator(){
        return new MyMatchValidator<DyshaJob>();
    }

    @Bean
    public MyMatchValidator<DyshaWorker> getWorkerValidator(){
        return new MyMatchValidator<DyshaWorker>();
    }

    @Bean
    public FileTypeService getFileTypeService(){
        return new FileTypeService();
    }

}
