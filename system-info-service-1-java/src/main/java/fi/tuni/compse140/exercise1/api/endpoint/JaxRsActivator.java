package fi.tuni.compse140.exercise1.api.endpoint;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import jakarta.ws.rs.core.Application;


@OpenAPIDefinition(
        info = @Info(title = "System Info Service 1 - Java",
                description = "Service1 acts as an accessible HTTP server, responding to external client requests by collecting system information from both itself and Service2. It communicates with Service2 to retrieve its IP address, running processes, available disk space, and uptime, and returns the combined data to the client.",
                version = "1.0",
                contact = @Contact(name = "Dávid Laurovič", email = "david.laurovic@tuni.fi"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        servers = @Server(url = "{scheme}://{server}:{port}", variables = {
                @ServerVariable(name = "scheme", allowableValues = {"http", "https"}, defaultValue = "http"),
                @ServerVariable(name = "server", defaultValue = "localhost"),
                @ServerVariable(name = "port", defaultValue = "8199"),
        })
)
public class JaxRsActivator extends Application {
}
