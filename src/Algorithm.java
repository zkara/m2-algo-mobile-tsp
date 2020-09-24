import io.jbotsim.core.Point;

import java.util.List;

public class Algorithm {

    protected List<Point> points;

    public void setPoints(List<Point> points){
        this.points = points;
    }

    public List<Point> getItinerary(){
        // Compute itinerary HERE
        return points;
    }
}