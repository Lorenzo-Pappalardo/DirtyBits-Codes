/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverse;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author loren
 */
@WebService(serviceName = "WSReverse")
public class WSReverse {


    /**
     * Web service operation
     */
    @WebMethod(operationName = "reverse")
    public String reverse(@WebParam(name = "word") String word) {
        String reversed = "";
        for (int i=0; i<word.length(); i++)
            reversed += word.charAt(word.length() - 1 -i);
        return reversed;
    }
}
