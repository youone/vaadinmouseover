package you1.vaadin.client;

import com.vaadin.shared.communication.ServerRpc;

public interface MouseOverEventsRPC extends ServerRpc {
    public void mouseOver();
    public void mouseOut();
}
