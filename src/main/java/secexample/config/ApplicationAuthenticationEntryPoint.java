package secexample.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    private final ObjectMapper mapper = new ObjectMapper(); // mapper is used to write the response that is a POJO cause it must be standard for every response

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("Authentication error: {}", authException.getMessage());

        //TODO create an object to trace the  errorResponse

        //TODO extracts info from request object and log the event for audit purposes

        //TODO track login failure for existing user so we can filter requests coming from the same IP address for a bit of time

        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //TODO response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }
}
