/*
This program is to exercise Hash Map's in java
Methods: Put(), get(), remove(), clear(), size(), keySet()
*/
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //Create a hash map called map
        HashMap<String, String> map = new HashMap<>();

        //Add key values (Country, City)
        map.put("England", "London");
        map.put("Germany", "Berlin");
        map.put("Norway", "Oslo");
        map.put("USA", "Washington DC");
        System.out.println(map);

        //Remove an item by the Country or key
        map.remove("England");
        System.out.println(map);

        //Iterating through a hash map
        for(String i: map.keySet()) {
            System.out.println(i);
        }

        //HashMap size
        int size = map.size();
        System.out.println(size);

        //Clearing the map
        map.clear();
        System.out.println(map);
    }
}
