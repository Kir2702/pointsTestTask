package assignment.cosy.demoAPI;

import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public final class ResponseWriter {

    public static  List<PointsRepository>  response(String json) throws IOException {
        JsonNode node = Json.parse(json);
        List<PointsRepository> responseList = new ArrayList<>();



        for (int j=0; j<node.size(); j++){
            //Map<String, String> responseMap = new HashMap<>();
            PointsRepository point = new PointsRepository();

            String displayName = null;
            JsonNode points = null;


            displayName = String.valueOf(node.get(j).get("display_name"));
            points = node.get(0).get("geojson").get("coordinates");

            //System.out.println(displayName);

            List<Float> allCoordinates;

            allCoordinates = getListPoints(points);
            //System.out.println(allCoordinates);


            List<Float> XCoordinates = new ArrayList<>();
            List<Float> YCoordinates = new ArrayList<>();

            for (int i=0; i<allCoordinates.size(); i++){
                if (i % 2 == 0){
                    XCoordinates.add(allCoordinates.get(i));
                }
                if (i % 2 == 1){
                    YCoordinates.add(allCoordinates.get(i));
                }
            }

            //System.out.println(displayName);
            //System.out.println(getAverage(XCoordinates));
            //System.out.println(getAverage(YCoordinates));

            //responseMap.put("location name", displayName);
            //responseMap.put("X coordinate", getAverage(XCoordinates).toString());
            //responseMap.put("Y coordinate", getAverage(YCoordinates).toString());

            point.setLocationName(displayName);
            point.setXcoordinate(getAverage(XCoordinates).toString());
            point.setYcoordinate(getAverage(YCoordinates).toString());




            responseList.add(point);


        }
        return responseList;
    }


    private static List<Float> getListPoints(JsonNode points) {
        List<Float> result = new ArrayList<>();


        for (JsonNode element : points) {
            if (element.isArray()) {
                result.addAll(getListPoints(element));
            } else {
                result.add(Float.valueOf(element.toString()));
            }
        }
        return result;
    }

    private static Float getAverage (List<Float> list){
        float sum = 0;
        for (float number : list) {
            sum += number;
        }
        return sum / list.size();
    }
}

