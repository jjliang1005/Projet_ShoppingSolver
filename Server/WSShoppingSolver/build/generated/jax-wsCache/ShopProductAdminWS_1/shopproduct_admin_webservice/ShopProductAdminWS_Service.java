
package shopproduct_admin_webservice;

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
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ShopProductAdminWS", targetNamespace = "http://shopproduct_admin_webservice/", wsdlLocation = "http://jjliang1005.whelastic.net/ShopProductAdminWS/ShopProductAdminWS?WSDL")
public class ShopProductAdminWS_Service
    extends Service
{

    private final static URL SHOPPRODUCTADMINWS_WSDL_LOCATION;
    private final static WebServiceException SHOPPRODUCTADMINWS_EXCEPTION;
    private final static QName SHOPPRODUCTADMINWS_QNAME = new QName("http://shopproduct_admin_webservice/", "ShopProductAdminWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://jjliang1005.whelastic.net/ShopProductAdminWS/ShopProductAdminWS?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SHOPPRODUCTADMINWS_WSDL_LOCATION = url;
        SHOPPRODUCTADMINWS_EXCEPTION = e;
    }

    public ShopProductAdminWS_Service() {
        super(__getWsdlLocation(), SHOPPRODUCTADMINWS_QNAME);
    }

    public ShopProductAdminWS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SHOPPRODUCTADMINWS_QNAME, features);
    }

    public ShopProductAdminWS_Service(URL wsdlLocation) {
        super(wsdlLocation, SHOPPRODUCTADMINWS_QNAME);
    }

    public ShopProductAdminWS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SHOPPRODUCTADMINWS_QNAME, features);
    }

    public ShopProductAdminWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ShopProductAdminWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ShopProductAdminWS
     */
    @WebEndpoint(name = "ShopProductAdminWSPort")
    public ShopProductAdminWS getShopProductAdminWSPort() {
        return super.getPort(new QName("http://shopproduct_admin_webservice/", "ShopProductAdminWSPort"), ShopProductAdminWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ShopProductAdminWS
     */
    @WebEndpoint(name = "ShopProductAdminWSPort")
    public ShopProductAdminWS getShopProductAdminWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://shopproduct_admin_webservice/", "ShopProductAdminWSPort"), ShopProductAdminWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SHOPPRODUCTADMINWS_EXCEPTION!= null) {
            throw SHOPPRODUCTADMINWS_EXCEPTION;
        }
        return SHOPPRODUCTADMINWS_WSDL_LOCATION;
    }

}
