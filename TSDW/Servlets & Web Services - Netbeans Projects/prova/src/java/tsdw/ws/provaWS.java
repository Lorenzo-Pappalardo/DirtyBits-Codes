/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsdw.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author LORENZOPAPPALARDO
 */
@WebService(serviceName = "provaWS")
public class provaWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "multiply")
    public Float multiply(@WebParam(name = "num1") Float num1, @WebParam(name = "num2") Float num2) {
        return num1 * num2;
    }
}
