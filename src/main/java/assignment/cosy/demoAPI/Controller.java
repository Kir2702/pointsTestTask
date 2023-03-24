package assignment.cosy.demoAPI;

import assignment.cosy.demoAPI.exceptions.BadRequestException;
import assignment.cosy.demoAPI.query.Request;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.NotFoundException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
@Slf4j
@RestController
public class Controller {

    @GetMapping(value = "/")
    //@RequestMapping(value="/",method=RequestMethod.GET)
    public String help() {
        return "http://127.0.0.1:8080/point?type=area&country=russia&state=Самарская%20область";
    }



    @GetMapping(value = "/point")
    @Cacheable("response")
    //@RequestMapping(value="/",method=RequestMethod.GET)
    public String point(@RequestParam("country") String country,
                        @RequestParam("state") String state,
                        @RequestParam("type") String type){

        Map<String, String> queryParam = new HashMap<>();

        queryParam.put("country", country);
        queryParam.put("state", state);
        queryParam.put("type", type);

        String nominatimData = Request.getData(queryParam);
        String response;


        try {
            response = ResponseWriter.response(nominatimData).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //String r = "[Запрос на получение точки [country: " + country + "] [state: " + state + "] [type: " + type + "]]" + response;
        return response;
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(BadRequestException badRequestException){
        log.error(badRequestException.getMessage(), badRequestException);
        return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

