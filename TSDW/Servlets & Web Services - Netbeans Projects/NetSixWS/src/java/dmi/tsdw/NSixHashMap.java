/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmi.tsdw;

import javax.jws.WebService;
import java.util.*;

/**
 *
 * @author longo
 */
@WebService(serviceName = "NSixHashMap")
public class NSixHashMap {
    public HashMap<String, Integer> serie = new HashMap<>();

    /**
     * Web service operation
     * @param nome_serie
     * @param n
     * @return 
     */
    public String getDisponibilita(final String nome_serie, final int n) {
        
        serie.put("The Witcher", 8);
        serie.put("Lupin", 8);
        serie.put("Stranger Things", 8);
        serie.put("Cobra Kai", 10);
        serie.put("Squid Game", 9);
        serie.put("Dark", 10);
        serie.put("Mr. Robot", 10);
        serie.put("Friends", 24);
        
        if(serie.get(nome_serie)!=null) {
            if(serie.get(nome_serie)>=n) return "Disponibile";
            else return "Coming soon";
        }
        return "Serie non disponibile";
    }
    
    
}
