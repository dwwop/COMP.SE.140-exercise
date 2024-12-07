package fi.tuni.compse140.project.service;

import fi.tuni.compse140.project.constants.ExceptionConstants;
import fi.tuni.compse140.project.exception.InternalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger LOG = LogManager.getLogger();
    private static final String HTPASSWD_FILE = "/.htpasswd";

    private static Map<String, String> loadHtpasswd() throws IOException {
        Map<String, String> credentials = new HashMap<>();
        try (InputStream inputStream = AuthServiceImpl.class.getResourceAsStream(HTPASSWD_FILE)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        credentials.put(parts[0], parts[1]);
                    }
                }
            }
        }
        return credentials;
    }

    @Override
    public boolean verify(String username, String password) {
        try {
            String hashedPassword = loadHtpasswd().get(username);
            return hashedPassword != null && BCrypt.checkpw(password, hashedPassword);
        } catch (IOException e) {
            LOG.error(String.valueOf(e));
            throw new InternalException(ExceptionConstants.UNABLE_TO_VERIFY_CREDENTIALS);
        }
    }
}
