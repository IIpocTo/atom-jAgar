package replication.leaderboard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class MasterTestLeaderServerConfiguration {

    @NotNull
    private final static Logger log = LogManager.getLogger(replication.json.MasterTestServerConfiguration.class);
    public static int ACCOUNT_PORT = 0;
    public static int CLIENT_PORT = 0;
    public static Class[] SERVICES_ARRAY = null;

    static {

        Properties props = new Properties();

        try (InputStream input = new FileInputStream(
                Paths.get("", "target", "test-classes", "test_replication_leaderboard_config.ini")
                        .toAbsolutePath()
                        .toFile()
        )) {

            props.load(input);
            ACCOUNT_PORT = Integer.valueOf(props.getProperty("accountServerPort"));
            CLIENT_PORT = Integer.valueOf(props.getProperty("clientConnectionPort"));

            final String[] services = props.getProperty("services").split(",");
            SERVICES_ARRAY = new Class[services.length];
            for (int i = 0; i < SERVICES_ARRAY.length; i++) {
                try {
                    SERVICES_ARRAY[i] = Class.forName(props.getProperty(services[i]));
                } catch (ClassNotFoundException e) {
                    log.error("Returned not existing class");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("File test_replication_config.ini not found");
        }

    }

}
