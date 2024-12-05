package fi.tuni.compse140.project.api.endpoint;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import jakarta.ws.rs.core.Application;


@OpenAPIDefinition(
        info = @Info(title = "State service",
                description = "State service handles state information and update, request count information and start of system information",
                version = "1.0",
                contact = @Contact(name = "Dávid Laurovič", email = "david.laurovic@tuni.fi"),
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
