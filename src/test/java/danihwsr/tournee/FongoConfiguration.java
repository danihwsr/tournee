package danihwsr.tournee;

import danihwsr.tournee.web.UserRepository;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(value = "danihwsr.tournee.web")
@ComponentScan(basePackageClasses = { UserRepository.class })
@Profile(value = "test")
public class FongoConfiguration extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "fongo-test";
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return new Fongo("fongo").getMongo();
    }
}
