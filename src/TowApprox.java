import io.jbotsim.core.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TowApprox extends Algorithm {
    public List<Point> getItinerary() {
        List<Point> itinerary = new ArrayList<>();

        Vector<Vector<Integer>>  mst = this.kruskalMST(points);

        return itinerary;
    }

    public Vector<Vector<Integer>> kruskalMST(List<Point> points) {
        //Vector<Vector<Integer>>  result= new Vector<Vector<Integer>>();

        Pair<Point, Point> s[] = null;

        for (int i=0; i<points.size()-1; i++) {
            for (int j=1; j<points.size(); j++) {

            }
        }



        return result;
    }
}
