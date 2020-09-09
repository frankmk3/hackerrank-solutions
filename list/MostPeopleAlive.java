package list;


import java.util.*;
import java.util.stream.Collectors;

public class MostPeopleAlive {

    public static void main(String[] args) {
        new MostPeopleAlive().run();
    }

    /*
     up
     1900 -> 1

     1910 -> 2


     down
     1990 -> 1
     */
    private int mostPeopleAliveYear(Person[] persons) {
        if (persons == null || persons.length == 0) {
            return 0;
        }
        Map<Integer, Integer> peopleCount = new HashMap<>();
//        Map<Integer, Integer> downCount = new HashMap<>();
        int minYear = persons[0].birth;
        int maxYear = persons[0].death;
        Set<Integer> years = new HashSet<>();
        for (Person person : persons) {
            if (person.birth != person.death) {//this is only ok if we need to check at the end of the year
                int countUp = peopleCount.getOrDefault(person.birth, 0) + 1;
                int countDown = peopleCount.getOrDefault(person.death, 0) - 1;

                if (person.birth < minYear) {
                    minYear = person.birth;
                }
                if (person.birth > maxYear) {
                    maxYear = person.birth;
                }
                peopleCount.put(person.birth, countUp);
                peopleCount.put(person.death, countDown);

                years.add(person.birth);//only if we consider that sorting the existing years will improve performance
                years.add(person.death);
            }
        }

        int yearLength = maxYear - minYear + 1;
        int count = 0;
        int maxYearCount = 0;
        int year = 0;

        //sorting
//        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
//        for (Integer currentYear : sortedYears) {
//
//            if(peopleCount.containsKey(currentYear)){
//                count += peopleCount.get(currentYear);
//            }
//            if(maxYearCount < count){
//                maxYearCount = count;
//                year = currentYear;
//            }
//        }
        //no sorting
        for (int i = 0; i < yearLength; i++) {
            int currentYear = minYear + i;
            if (peopleCount.containsKey(currentYear)) {
                count += peopleCount.get(currentYear);
            }
            if (maxYearCount < count) {
                maxYearCount = count;
                year = currentYear;
            }
        }

        return year;
    }

    public void run() {

        System.out.println(mostPeopleAliveYear(new Person[]{
                new Person(1, 10),
                new Person(1, 11),
                new Person(6, 12),
                new Person(12, 20),
                new Person(11, 19),
                new Person(10, 21),
        }) == 6);

        System.out.println(mostPeopleAliveYear(new Person[]{
                new Person(1, 10),
                new Person(1, 11),
                new Person(6, 12),
                new Person(12, 20),
                new Person(11, 19),
                new Person(10, 21),
        }) == 6);
    }
}

class Person {

    public final int birth;
    public final int death;

    public Person(int birth, int death) {
        this.birth = birth;
        this.death = death;
    }
}