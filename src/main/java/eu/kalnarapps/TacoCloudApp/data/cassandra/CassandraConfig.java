package eu.kalnarapps.TacoCloudApp.data.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.cassandra.SessionFactory;
import org.springframework.data.cassandra.core.cql.session.init.ResourceKeyspacePopulator;
import org.springframework.data.cassandra.core.cql.session.init.SessionFactoryInitializer;

@Configuration
public class CassandraConfig {

    @Bean
    public SessionFactoryInitializer sessionFactoryInitializer(SessionFactory sessionFactory) {
        SessionFactoryInitializer initializer = new SessionFactoryInitializer();
        initializer.setKeyspacePopulator(keyspacePopulator());
        initializer.setSessionFactory(sessionFactory);
        return initializer;
    }

    private ResourceKeyspacePopulator keyspacePopulator() {
        ResourceKeyspacePopulator populator = new ResourceKeyspacePopulator();
        populator.addScript(new ClassPathResource("data.cql")); // Your script location
        return populator;
    }
}