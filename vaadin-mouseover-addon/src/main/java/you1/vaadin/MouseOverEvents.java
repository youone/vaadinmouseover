package you1.vaadin;


import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import you1.vaadin.client.MouseOverEventsRPC;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MouseOverEvents extends AbstractExtension {

    private Set<MouseOverListener> mouseOverListeners = new HashSet<MouseOverListener>();
    private Set<MouseOutListener> mouseOutListeners = new HashSet<MouseOutListener>();


    public interface MouseOverListener extends Serializable {
        void mouseOver();
    }

    public interface MouseOutListener extends Serializable  {
        void mouseOut();
    }

    MouseOverEventsRPC rpc = new MouseOverEventsRPC() {
        @Override
        public void mouseOver() {
            fireMouseOverEvents();
        }

        @Override
        public void mouseOut() {
            fireMouseOutEvents();
        }
    };

    protected MouseOverEvents(AbstractClientConnector component) {
        registerRpc(rpc);
        extend(component);
    }

    public static MouseOverEvents enableFor(AbstractClientConnector component) {
        return new MouseOverEvents(component);
    }

    private void fireMouseOverEvents() {
        for (MouseOverListener listener : Collections.unmodifiableCollection(mouseOverListeners)) {
            listener.mouseOver();
        }
    }

    private void fireMouseOutEvents() {
        for (MouseOutListener listener : Collections.unmodifiableCollection(mouseOutListeners)) {
            listener.mouseOut();
        }
    }

    public void addMouseOverListener(MouseOverListener listener) {
        mouseOverListeners.add(listener);
    }

    public void removeMouseOverListener(MouseOverListener listener) {
        mouseOverListeners.remove(listener);
    }

    public void addMouseOutListener(MouseOutListener listener) {
        mouseOutListeners.add(listener);
    }

    public void removeMouseOutListener(MouseOutListener listener) {
        mouseOutListeners.remove(listener);
    }
}
