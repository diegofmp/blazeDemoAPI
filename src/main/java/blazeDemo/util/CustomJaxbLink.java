package blazeDemo.util;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.namespace.QName;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class CustomJaxbLink {

    private URI uri;
    private Map<QName, Object> params;


    public CustomJaxbLink() {
        this(null, null);
    }

    public CustomJaxbLink(URI uri) {
        this(uri, null);
    }

    public CustomJaxbLink(URI uri, Map<QName, Object> map) {

        this.uri = uri;
        this.params = map != null ? map : new HashMap<>();

    }

    @XmlAttribute(name = "href")
    public URI getUri() {
        return uri;
    }

    @XmlAnyAttribute
    public Map<QName, Object> getParams() {
        return params;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public void setParams(Map<QName, Object> params) {
        this.params = params;
    }

}