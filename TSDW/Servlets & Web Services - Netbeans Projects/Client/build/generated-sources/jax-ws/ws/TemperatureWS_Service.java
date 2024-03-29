
package ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TemperatureWS", targetNamespace = "http://ws/", wsdlLocation = "http://localhost:8081/TemperatureWS_Server/TemperatureWS?wsdl")
public class TemperatureWS_Service
    extends Service
{

    private final static URL TEMPERATUREWS_WSDL_LOCATION;
    private final static WebServiceException TEMPERATUREWS_EXCEPTION;
    private final static QName TEMPERATUREWS_QNAME = new QName("http://ws/", "TemperatureWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/TemperatureWS_Server/TemperatureWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TEMPERATUREWS_WSDL_LOCATION = url;
        TEMPERATUREWS_EXCEPTION = e;
    }

    public TemperatureWS_Service() {
        super(__getWsdlLocation(), TEMPERATUREWS_QNAME);
    }

    public TemperatureWS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), TEMPERATUREWS_QNAME, features);
    }

    public TemperatureWS_Service(URL wsdlLocation) {
        super(wsdlLocation, TEMPERATUREWS_QNAME);
    }

    public TemperatureWS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TEMPERATUREWS_QNAME, features);
    }

    public TemperatureWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TemperatureWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TemperatureWS
     */
    @WebEndpoint(name = "TemperatureWSPort")
    public TemperatureWS getTemperatureWSPort() {
        return super.getPort(new QName("http://ws/", "TemperatureWSPort"), TemperatureWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TemperatureWS
     */
    @WebEndpoint(name = "TemperatureWSPort")
    public TemperatureWS getTemperatureWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws/", "TemperatureWSPort"), TemperatureWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TEMPERATUREWS_EXCEPTION!= null) {
            throw TEMPERATUREWS_EXCEPTION;
        }
        return TEMPERATUREWS_WSDL_LOCATION;
    }

}
