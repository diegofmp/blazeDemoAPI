package blazeDemo.util;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

//ref: https://github.com/aruld/dropwizard-pagination/

public class CustomJaxbAdapter extends XmlAdapter<CustomJaxbLink, Link> {

    public CustomJaxbAdapter() {
    }

    public Link unmarshal(CustomJaxbLink  p1) {

        Link.Builder builder = Link.fromUri(p1.getUri());
        for (Map.Entry<QName, Object> entry : p1.getParams().entrySet()) {
            builder.param(entry.getKey().getLocalPart(), entry.getValue().toString());
        }
        return builder.build();
    }

    public CustomJaxbLink marshal(Link p1) {

        Map<QName, Object> params = new HashMap<>();
        for (Map.Entry<String, String> entry : p1.getParams().entrySet()) {
            params.put(new QName("", entry.getKey()), entry.getValue());
        }
        return new CustomJaxbLink(p1.getUri(), params);
    }
}