import io.jbotsim.core.Point;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbor extends Algorithm {
    
    public List<Point> getItinerary(){
        // Compute itinerary HERE

        List<Point> itinerary = new ArrayList<>();
        Point current = points.get(0); 
        itinerary.add(current);
        points.remove(current);
        
        while (!points.isEmpty()) {
            double distMin = Double.POSITIVE_INFINITY;
            Point best = null;
            for (Point p : points){
                double dist = current.distance(p);
                if (dist < distMin){
                    best = p;
                    distMin = dist;
                }
            }
            
            itinerary.add(best);
            points.remove(best);
            current = best;
        }
        return itinerary;
    }
}
