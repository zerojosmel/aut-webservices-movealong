package Configuracion;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;

public class ApplicationProperties {

    private static Properties instance = null;

    private static final String APPLICATION_PREFIX = "back";
    private static final String APPLICATION_SUFFIX = "properties";
    private static final Logger logger = LogManager.getLogger(ApplicationProperties.class);

    public static synchronized Properties getInstance() {
        if (instance == null) {
            instance = loadPropertiesFile();
        }
        return instance;
    }

    private ApplicationProperties(){
    }

    private static Properties loadPropertiesFile() {

        String environment = Optional.ofNullable(System.getenv("env"))
                .orElse("antiguo");

        String fileName = String.format("%s-%s.%s", APPLICATION_PREFIX, environment, APPLICATION_SUFFIX);

        logger.info("Property file to read {}", fileName);

        Properties prop = new Properties();

        try {
            prop.load(getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Unable to load the file {}", fileName);
        }

        return prop;
    }
}
