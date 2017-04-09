package you1.vaadin.client;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import you1.vaadin.MouseOverEvents;

@Connect(MouseOverEvents.class)
public class MouseOverEventsConnector extends AbstractExtensionConnector implements MouseOverHandler, MouseOutHandler
{

    @Override
    protected void extend(ServerConnector serverConnector)
    {
        Widget target = ((ComponentConnector) serverConnector).getWidget();
        target.sinkEvents(Event.ONMOUSEOVER | Event.ONMOUSEOUT);
        target.addHandler(this, MouseOverEvent.getType());
        target.addHandler(this, MouseOutEvent.getType());
    }

    @Override
    public void onMouseOver(MouseOverEvent mouseOverEvent)
    {
        console("MouseOver event!!");
        getRpcProxy(MouseOverEventsRPC.class).mouseOver();
    }

    @Override
    public void onMouseOut(MouseOutEvent mouseOutEvent)
    {
        console("MouseOut event!!");
        getRpcProxy(MouseOverEventsRPC.class).mouseOut();
    }

    static native void console(String text)
    /*-{
        console.log(text);
    }-*/;
}
