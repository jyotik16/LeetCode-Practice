package thread;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class demo {

    public static void main(String[] args) {
     //   trywith();
      //  test();
        test2();
        LocalDate date = LocalDate.now();
        System.out.println(date);

    }

    private static void test2() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("First", 10);
        map.put("Second", 20);
        map.put("Third", 30);
        map.put("Fourth", 40);

        Iterator<String> iterator = map.keySet().iterator();
        boolean flag = true;
        while (iterator.hasNext()) {
            String key = iterator.next();
            map.put("Fifth", 50);
            if(flag){
                map.put("Third",60); flag = false;
            }
            System.out.println(map.hashCode());
            System.out.println(key+" "+map.get(key));
        }
    }


    private static int trywith() {

        try{
            int a = 10/2;
            return a;
        }catch(Exception e){
            System.out.println("opps");
        }
        return 0;
    }

    private static void test(){
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

      /*  for(int i = 0;i<list.size();i++) {
            if (i==1)
                list.add("C");
            System.out.println(list.get(i));
        }
       */
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String number = iterator.next();
            list.add("D");
            System.out.println(number);
        }


    }
}
