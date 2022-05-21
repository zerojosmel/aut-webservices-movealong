package Configuracion;
import java.util.Optional;

public class ConfVariables {

    public static String getHost() {
        return Optional.ofNullable(System.getenv("host"))
                .orElse((String) ApplicationProperties.getInstance().get("host"));
    }

    public static String getToken() {
        return Optional.ofNullable(System.getenv("token"))
                .orElse((String) ApplicationProperties.getInstance()
                        .get("token"));
    }

    public static String getTicket() {
        return Optional.ofNullable(System.getenv("ticket"))
                .orElse((String) ApplicationProperties.getInstance()
                        .get("ticket"));
    }

    public static String getPath() {
        return Optional.ofNullable(System.getenv("path"))
                .orElse((String) ApplicationProperties.getInstance()
                        .get("path"));
    }
}
