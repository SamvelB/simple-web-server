package ru.samvel.jetty;

import java.util.HashMap;
import java.util.Map;


public class BayList {

    private String name;
    private String amount;

    public BayList(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }


    public Map<String, Object> createBayList() {
        Map<String, Object> bayList = new HashMap<>();
        bayList.put("name", "Text-0");
        bayList.put("1", "Text-1");
        bayList.put("2", "Text-2");
        bayList.put("3", "Text-3");
        return bayList;
    }

}
