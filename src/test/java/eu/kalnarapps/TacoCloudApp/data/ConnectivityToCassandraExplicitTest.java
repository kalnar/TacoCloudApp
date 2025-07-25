package eu.kalnarapps.TacoCloudApp.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.datastax.oss.driver.api.core.CqlSession;

import java.net.InetSocketAddress;

/**
 * Connecticity to ASTRA
 */
@SpringBootTest
@TestPropertySource(properties = {
        "spring.data.cassandra.keyspace-name=tacocloud",
        "spring.data.cassandra.contact-points=127.0.0.1",
        "spring.data.cassandra.port=9042",
        "spring.data.cassandra.local-datacenter=datacenter1",
        "spring.data.cassandra.schema-action=CREATE_IF_NOT_EXISTS",
        "spring.data.cassandra.initialize-schema=ALWAYS"
})
public class ConnectivityToCassandraExplicitTest {

    /**
     * Logger for the class.
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ConnectivityToCassandraExplicitTest.class);

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Test
    @DisplayName("Test connectivity to Astra explicit values")
    public void should_connect_to_Astra() {

        // Given interface is properly populated
        // When connecting to ASTRA
        System.out.println(keyspace);
        try (
                CqlSession cqlSession = CqlSession.builder()
                        .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                        .withKeyspace(keyspace)
                        .build()
        ) {

            // Then connection is successfull
            LOGGER.info(" + [OK] - Connection Established to Astra with Keyspace {}",
                    cqlSession.getKeyspace().get());
        }
    }
}
