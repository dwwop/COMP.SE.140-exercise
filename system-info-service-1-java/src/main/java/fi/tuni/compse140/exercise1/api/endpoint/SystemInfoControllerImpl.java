package fi.tuni.compse140.exercise1.api.endpoint;

import fi.tuni.compse140.exercise1.api.dto.SystemInfoDTO;
import fi.tuni.compse140.exercise1.facade.SystemInfoFacade;
import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemInfoControllerImpl implements SystemInfoController {
    SystemInfoFacade facade;

    public SystemInfoControllerImpl(SystemInfoFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<List<SystemInfoDTO>> getInformation() {
        return ResponseEntity.ok(facade.getSystemInfo());
    }

    @Override
    public Response shutdown() {
        facade.shutdown();
        return Response.noContent().build();
    }
}
