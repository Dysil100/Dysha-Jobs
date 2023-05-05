package duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.security.configuration;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.security.ChatDiscussionHash;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscussionConfiguration {


    @Bean
    public ChatDiscussionHash chatDiscussionHash(){
        return new ChatDiscussionHash();
    }
}
