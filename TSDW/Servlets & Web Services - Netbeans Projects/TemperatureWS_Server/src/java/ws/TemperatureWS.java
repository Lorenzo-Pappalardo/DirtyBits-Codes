/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author loren
 */
@WebService(serviceName = "TemperatureWS")
public class TemperatureWS {

    /**
     * Web service operation
     *
     * @param city
     * @return
     */
    @WebMethod(operationName = "getTemperature")
    public String getTemperature(@WebParam(name = "city") String city) {
        Cities cities = Cities.getInstance();
        for (City tmp : cities.cities) {
            if (city.equalsIgnoreCase(tmp.name)) {
                return tmp.toString();
            }
        }
        return "Not Found!";
    }

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "getAllTemperatures")
    public String getAllTemperatures() {
        Cities cities = Cities.getInstance();
        return cities.cities.stream().map(city -> city.toString()).reduce("", String::concat);
    }
}
