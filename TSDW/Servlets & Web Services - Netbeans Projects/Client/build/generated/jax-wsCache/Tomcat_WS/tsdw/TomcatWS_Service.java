
package tsdw;

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
@WebServiceClient(name = "Tomcat_WS", targetNamespace = "http://tsdw/", wsdlLocation = "http://localhost:8081/Tomcat_Server/Tomcat_WS?wsdl")
public class TomcatWS_Service
    extends Service
{

    private final static URL TOMCATWS_WSDL_LOCATION;
    private final static WebServiceException TOMCATWS_EXCEPTION;
    private final static QName TOMCATWS_QNAME = new QName("http://tsdw/", "Tomcat_WS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/Tomcat_Server/Tomcat_WS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TOMCATWS_WSDL_LOCATION = url;
        TOMCATWS_EXCEPTION = e;
    }

    public TomcatWS_Service() {
        super(__getWsdlLocation(), TOMCATWS_QNAME);
    }

    public TomcatWS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), TOMCATWS_QNAME, features);
    }

    public TomcatWS_Service(URL wsdlLocation) {
        super(wsdlLocation, TOMCATWS_QNAME);
    }

    public TomcatWS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TOMCATWS_QNAME, features);
    }

    public TomcatWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TomcatWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TomcatWS
     */
    @WebEndpoint(name = "Tomcat_WSPort")
    public TomcatWS getTomcatWSPort() {
        return super.getPort(new QName("http://tsdw/", "Tomcat_WSPort"), TomcatWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TomcatWS
     */
    @WebEndpoint(name = "Tomcat_WSPort")
    public TomcatWS getTomcatWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tsdw/", "Tomcat_WSPort"), TomcatWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TOMCATWS_EXCEPTION!= null) {
            throw TOMCATWS_EXCEPTION;
        }
        return TOMCATWS_WSDL_LOCATION;
    }

}