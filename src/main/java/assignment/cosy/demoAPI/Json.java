package assignment.cosy.demoAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {

    private static ObjectMapper objectMapper = getDefaultObjectMatter();
    private static ObjectMapper getDefaultObjectMatter(){
        ObjectMapper defoultObjectMapper = new ObjectMapper();

        return defoultObjectMapper;
    }

    public static JsonNode parse(String jsonStr) throws IOException{
        //String displayName = objectMapper.readValue(jsonStr, String.class);


        return objectMapper.readTree(jsonStr);
    }

}
