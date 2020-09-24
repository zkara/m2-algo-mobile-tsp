import io.jbotsim.core.Point;

import java.util.ArrayList;
import java.util.List;

public class RandomInsertion extends Algorithm {
    public List<Point> getItinerary(){
        // Compute itinerary HERE
        List<Point> itinerary = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            itinerary.add(points.get(i));
            points.remove(points.get(i));
        }





        return itinerary;
    }
}
