package assignment.cosy.demoAPI;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Repository
public class PointsRepository {
    @Getter
    @Setter
    private String locationName;
    @Getter
    @Setter
    private String Xcoordinate;
    @Getter
    @Setter
    private String Ycoordinate;

    public void pointsRepository(String locationName, String Xcoordinate, String Ycoordinate){
        this.locationName = locationName;
        this.Xcoordinate = Xcoordinate;
        this.Ycoordinate = Ycoordinate;
    }

    @Override
    public String toString(){
        String s = "{location name="+locationName+", " +
                   "X coordinate="+Xcoordinate+"," +
                   "Y coordinate="+Ycoordinate+"}";
    return s;
    }
}
