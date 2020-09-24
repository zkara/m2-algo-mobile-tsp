import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import io.jbotsim.core.Topology;
import io.jbotsim.core.event.CommandListener;
import io.jbotsim.ui.JTopology;
import io.jbotsim.ui.JViewer;
import io.jbotsim.ui.painting.BackgroundPainter;
import io.jbotsim.ui.painting.UIComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller implements CommandListener, BackgroundPainter {

    public static final String COMMAND_COMPUTE_TSP = "Compute TSP";

    private Topology topology;
    private JTopology jTopology;

    private List<Point> points = new ArrayList<>();

    public Controller() {
        topology = new Topology();
        topology.setDefaultNodeModel(Target.class);

        jTopology = new JTopology(topology);
        jTopology.addBackgroundPainter(this);

        topology.addNode(58.0, 128.0);
        topology.addNode(157.0, 51.0);
        topology.addNode(224.0, 150.0);
        topology.addNode(339.0, 68.0);
        topology.addNode(460.0, 39.0);
        topology.addNode(537.0, 150.0);
        topology.addNode(568.0, 358.0);
        topology.addNode(458.0, 306.0);
        topology.addNode(222.0, 353.0);
        topology.addNode(113.0, 289.0);
        topology.addNode(437.0, 119.0);
        topology.addNode(360.0, 280.0);
        topology.addNode(128.0, 219.0);
        topology.addNode(271.0, 81.0);
        topology.addNode(504.0, 222.0);
        
        topology.setTimeUnit(5);
        new JViewer(jTopology);
        topology.start();
        // Adding the custom command after the creation of
        // the JViewer makes sure it is displayed last.
        topology.addCommand(COMMAND_COMPUTE_TSP);
        topology.addCommandListener(this);
    }

    @Override
    public void onCommand(String command) {
        if (command.equals(COMMAND_COMPUTE_TSP)) {
            points.clear();
            for (Node node : topology.getNodes())
                if (node instanceof Target)
                    points.add(node.getLocation());

            List<Point> itinerary = computeItinerary();

            points = itinerary; // For drawing purposes

            assignToNode(itinerary);
        }
    }

    protected List<Point> computeItinerary() {

        // TODO: call TSP algorithms here
        
        NearestNeighbor algorithm = new NearestNeighbor();
        algorithm.setPoints(points);
        List<Point> itinerary = algorithm.getItinerary();

        double dist = 0;
        for (int i = 0; i < itinerary.size() - 1; i++){
            dist += itinerary.get(i).distance(itinerary.get(i+1));
        }

        System.out.println(dist);
        
        return itinerary;
    }

    @Override
    public void paintBackground(UIComponent c, Topology tp) {
        Graphics2D g2d = (Graphics2D) c.getComponent();
        for (int i = 0; i < points.size(); i++) {
            Point from = points.get(i);
            Point to = points.get((i + 1) % points.size());
            g2d.drawLine((int) from.getX(), (int) from.getY(),
                    (int) to.getX(), (int) to.getY());
        }
    }

    protected void assignToNode(List<Point> itinerary) {
        if (!itinerary.isEmpty()) {
            WaypointNode node = new WaypointNode();
            node.setLocation(itinerary.get(0));
            for (int i = 1; i < itinerary.size() + 1; i++)
                node.addDestination(itinerary.get(i % itinerary.size()));
            topology.addNode(node);
        }
    }
}