package secexample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class ApplicationAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper=new ObjectMapper(); // mapper is used to write the response that is a POJO cause it must be standard for every response

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException {
        log.error("Authorization error: {}", ex.getMessage());

        //TODO create an object to trace the  errorResponse
        //TODO track the access denied event in audit logs

        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        //TODO response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }

    }
