import io.jbotsim.core.Node;
import io.jbotsim.ui.icons.Icons;

public class Target extends Node {
    public Target() {
        disableWireless();
        setIcon(Icons.PLUS);
    }
}
