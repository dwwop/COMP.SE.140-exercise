package fi.tuni.compse140.exercise1.api.endpoint;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import jakarta.ws.rs.core.Application;


@OpenAPIDefinition(
        info = @Info(title = "Java service",
                version = "1.1",
                contact = @Contact(name = "Dávid Laurovič", email = "553651@muni.cz"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        servers = @Server(url = "{scheme}://{server}:{port}", variables = {
                @ServerVariable(name = "scheme", allowableValues = {"http", "https"}, defaultValue = "http"),
                @ServerVariable(name = "server", defaultValue = "localhost"),
                @ServerVariable(name = "port", defaultValue = "8080"),
        })
)
public class JaxRsActivator extends Application {
}
