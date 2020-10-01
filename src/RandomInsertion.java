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

        while (!points.isEmpty()) {

            int indexBest = 0;
            double distMin = Double.POSITIVE_INFINITY;
            Point best = null;
            Point p = points.get(0);

            for (Point current : itinerary) {

                Point next = null;

                if (itinerary.size() != itinerary.indexOf(current) + 1) {
                    next = itinerary.get(itinerary.indexOf(current) + 1);
                } else {
                    next = itinerary.get(0);
                }

                double dist = Math.abs((current.distance(p) - next.distance(p)) - current.distance(next));

                if (dist < distMin) {
                    distMin = dist;
                    indexBest = itinerary.lastIndexOf(current) + 1;
                }
            }

            itinerary.add(indexBest, p);
            points.remove(p);
        }

        return itinerary;
    }
}
