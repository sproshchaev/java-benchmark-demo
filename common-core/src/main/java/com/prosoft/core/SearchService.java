package com.prosoft.core;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    public boolean searchInList(List<String> list, String target) {
        return list.contains(target);
    }

    public boolean searchInMap(Map<String, String> map, String key) {
        return map.containsKey(key);
    }

    public int linearSearch(List<String> list, String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }
}

