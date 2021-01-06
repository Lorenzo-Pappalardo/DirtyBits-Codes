/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author loren
 */
public class Cities {
    private static Cities instance;
    public List<City> cities = new ArrayList<>();

    private Cities() {
        cities.add(new City("Siracusa", 12f));
        cities.add(new City("Acireale", 11f));
        cities.add(new City("Augusta", 14f));
        cities.add(new City("Catania", 13f));
        cities.add(new City("Palermo", 15f));
    }

    public static Cities getInstance() {
        if (instance == null) {
            instance = new Cities();
            return instance;
        }
        return instance;
    }
}
