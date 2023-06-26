package java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class java8_1 {

    static class Employee implements Comparable<Employee>{
        int id; String name; String city; long salary;

        public Employee(int id, String name, String city,long sal) {
            super();
            this.id = id;
            this.name = name;
            this.city = city;
            this.salary = sal;
        }

        @Override
        public String toString() {
            return "Employee [id=" + id + ", name=" + name + ", city=" + city + ", "+salary+" ]";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public long getSalary() {
            return salary;
        }

        public void setSalary(long salary) {
            this.salary = salary;
        }

        @Override
        public int compareTo(Employee o) {

            if (this.id > o.id) {
                return 1;
            } else if (this.id < o.id) {
                return -1;
            } else {
                return 0;
            }

            //	return this.name.compareTo(o.name);
        }
    }

    public static List<Employee> employeeList = new ArrayList<>();

    public static void createEmployeeList() {
        Employee e1 = new Employee(1,"Aman","Delhi",10L);
        Employee e2 = new Employee(2,"Abhi","Banglore",20L);
        Employee e3 = new Employee(3,"Ravi","Pune",30L);
        Employee e4 = new Employee(4,"Azhar","Pune",5L);
        Employee e5 = new Employee(5,"Armaan","Goa",3L);
//        employeeList.add(e3);
//        employeeList.add(e2);
//        employeeList.add(e1);
//        employeeList.add(e4);
//        employeeList.add(e5);
    }

    public static void main(String[] args) {
        createEmployeeList();
      //  printData(employeeList);
        System.out.println("*************");
      //  updateSalaryBy5K(employeeList);
     //   FindSecondLargestNumberInAnArraysUsingJava8Stream();
     //   removeDuplicateInStrings();
     //   removeCharactesInString();
      //convertMapListintoMap();
        // convertMapListintoMapWithDuplicate();
       // convertListintoMap();
      //  printVowlesfromString();
     //   countthelettersInString();
        sort2DArray();
    }

    private static void sort2DArray() {
        int [][] costs = {{10,20},{30,200},{400,50},{30,20}};
        //Arrays.sort(costs, (a, b) -> -(Math.abs(a[0] - a[1]) - Math.abs(b[0] - b[1])));
        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
        for (int[] a:costs) {
            for (int b:a) {
                System.out.print(b+" ");
            }
            System.out.println();
        }
        /*
        30 200 -170
        10 20  -10
        30 20  10
        400 50 350
        negative value come first before positive value
         */
        System.out.println("Happy!");
    }

    private static void countthelettersInString() {
        //count the letter present in strings
        String str="Jyoti Kashyap";
        Map<String, Long> charCountInmap = str.chars().mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(charCountInmap); //{p=1,  =1, a=2, s=1, t=1, h=1, i=1, y=2, J=1, K=1, o=1}
    }

    private static void printVowlesfromString() {
        String str="Jyoti Kashyap";
        Stream<Integer> l = str.chars().boxed();
      //  l.forEach(System.out::println); //106,121,,
        //print the vowels
        //str = str.toLowerCase();
        str.chars().mapToObj(item->(char)item).
                filter(item->item.equals('a') || item.equals('i') || item.equals('o') || item.equals('e') || item.equals('u'))
                .forEach(System.out::println);
        //count the vowles
        long vowelCount = str.chars().mapToObj(item->(char)item).
                filter(item->item.equals('a') || item.equals('i') || item.equals('o') || item.equals('e') || item.equals('u')).count();
        System.out.println("Totals vowels are:"+vowelCount);

        long consonutCount = str.chars().mapToObj(item->(char)item).
                filter(item->((item>='a' && item<='z') || (item>='A' && item<='Z'))).count();
        System.out.println("Totals consonuts are:"+consonutCount);
        System.out.println("***********");

    }

    private static void convertListintoMap() {
        Map<String, String> NameCityMap = employeeList.stream()
                .collect(Collectors.toMap(Employee::getName, Employee::getCity, (oldValue, newValue) -> newValue));

        System.out.println("Map <Name, Location>" + NameCityMap);

        System.out.println("***********************");
        // Map - Id, Name
        Map<Integer, String> idNameMap = employeeList.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getName, (oldValue, newValue) -> newValue));

       // idNameMap.entrySet().stream().forEach(System.out::println);
        System.out.println("Map <Id, Name>" + idNameMap);
        System.out.println("***********************");
        // Map - Id, Sal by 10K
        Map<Integer, Long> idSalaryMap = employeeList.stream().map(emp->{
                        emp.setSalary(emp.getSalary()+10); return emp;
                })
                .collect(Collectors.toMap(Employee::getId, Employee::getSalary, (oldValue, newValue) -> newValue));

        // idNameMap.entrySet().stream().forEach(System.out::println);
        System.out.println("Map <Id, Salary>" + idSalaryMap);
    }

    private static void convertMapListintoMapWithDuplicate() {
        //input {1->["Delhi","Hydrabad"],2->["Goa","Nasik"],3->["Shimla","Gawlior"],4->["Nanitaal","Rishikesh"]}
        //Output {Delhi->1,Hydrabad->1,Goa->2....}

        Map<String,List<String>> mainMap = new HashMap<>();
        mainMap.put("1",Arrays.asList("Delhi","Hydrabad"));
        mainMap.put("2",Arrays.asList("Goa","Nasik"));
        mainMap.put("3",Arrays.asList("Shimla","Gawlior"));
        mainMap.put("4",Arrays.asList("Delhi","Rishikesh"));

        Map<String,String> resMap = mainMap.entrySet().stream()
                .flatMap(e->e.getValue().stream().map(v -> new AbstractMap.SimpleEntry<>(v, e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldv,newv)->newv));

        resMap.entrySet().stream().forEach(System.out::println);

    }

    private static void convertMapListintoMap() {
        //input {1->["Delhi","Hydrabad"],2->["Goa","Nasik"],3->["Shimla","Gawlior"],4->["Nanitaal","Rishikesh"]}
        //Output {Delhi->1,Hydrabad->1,Goa->2....}
        //NOTE: List have don't have any duplicate value.
        Map<String,List<String>> mainMap = new HashMap<>();
        mainMap.put("1",Arrays.asList("Delhi","Hydrabad"));
        mainMap.put("2",Arrays.asList("Goa","Nasik"));
        mainMap.put("3",Arrays.asList("Shimla","Gawlior"));
        mainMap.put("4",Arrays.asList("Nanitaal","Rishikesh"));

        Map<String,String> resMap = mainMap.entrySet().stream()
                .flatMap(e->e.getValue().stream().map(v -> new AbstractMap.SimpleEntry<>(v, e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        resMap.entrySet().stream().forEach(System.out::println);
    }

    private static void removeCharactesInString() {
        String string = "cbacdcbc";

      System.out.println("***** print  non duplicate characters *****");
      Set<Character> l = string.chars()
                .mapToObj(item -> (char) item)
                .collect(Collectors.toSet());
        l.stream().forEach(System.out::println); //print only duplicate element
        // difference in retrun type
        Set<Character> set = new HashSet<>();
        List<Character> res = string.chars()
                .mapToObj(item->(char)item).
                filter(e->set.add(e)).collect(Collectors.toList());
        res.stream().forEach(System.out::println);

    }

    private static void removeDuplicateInStrings() {
        String orignalString = "world world";

        String output = Arrays.asList(orignalString.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());

        System.out.println("Original String : " + orignalString);
        System.out.println("After removing the duplicates : " + output);

    }

    private static void FindSecondLargestNumberInAnArraysUsingJava8Stream() {
        // random numbers Array
        int[] numbers = {5, 9, 11, 2, 8, 21, 1,21}; //[1,2,5,8,9,11,21,21]

        // print to console
        System.out.println("Numbers in an Arrays : "+ Arrays.toString(numbers));
        int n = 2;
        // sort in descending-order and get 2nd largest element
        int secondLargestNumber = Arrays
                .stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(n-1)
                .findFirst()
                .get();
        System.out.println(secondLargestNumber);
       // Stream<Integer> res= Arrays.stream(numbers).boxed(); Array ko stream<Integer> convert by boxed()
       // System.out.println(res);

        //By using limit()
        List<Integer> numbersList = Arrays.asList(5, 9, 11, 2, 8, 21, 1);
        int secondLargestNumber2 = numbersList
                .stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .limit(n)
                .skip(n-1)
                .findFirst()
                .get();
        System.out.println(secondLargestNumber2);

    }

    private static void printData(List<Employee> employeeList) {
        employeeList.stream().forEach(System.out::println);
    }

    private static void updateSalaryBy5K(List<Employee> employeeList) {
        long sal = 10L;
       List<Employee> filetredList = employeeList.stream().filter(emp->emp.getSalary()>sal).collect(Collectors.toList());

        long incsal = 5L;
       /* //return only updated list 
       List<Employee> filetredList2 = employeeList.stream().filter(emp->emp.getSalary()<sal)
                        .map(emp->{
                                emp.setSalary(emp.getSalary()+incsal);
                            return emp;
                        }
                        ).collect(Collectors.toList());

        */
        //return all list
        List<Employee> allList = employeeList.stream()
                .map(emp->{
                            if(emp.getSalary()<sal)
                                emp.setSalary(emp.getSalary()+incsal);
                            return emp;
                        }
                ).collect(Collectors.toList());
        printData(allList);
    }

}
