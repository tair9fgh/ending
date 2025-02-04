package reprose2424.respro.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProperPassenger<T, V> extends HashMap<T, V>
        // HashMap класс лучше интерфейса Map
        implements IProperPassenger<T, V> {
    private Map<T, V> printpassengerorflight = new HashMap<>();

    public void filtermap_forowndefault(String firstNameLetter,
                                        String lastNameLetter, int age) {
        IProperPassenger.super.filtermap_db(firstNameLetter,
                lastNameLetter, age, printpassengerorflight);// 1 error
    }// использует готовый default метод для фильтрации пассажиров

    @Override
    public void filtermap_db(String firstNameLetter,
                             String lastNameLetter, int age) {
        Map<T, V> printpassengerdone = new HashMap<>();
        printpassengerorflight.entrySet().stream()
                .filter(entry -> {
                    Passenger pass1
                            = (Passenger) entry.getValue();
                    return pass1.getfirstName()
                            != null
                            &&
                            pass1.getage()
                                    != 0 &&
                            pass1.getfirstName()
                                    .equals(
                                            firstNameLetter)
                            &&
                            pass1.getage()
                                    > age;
                }).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));

        // .forEach(entry -> printpassengerdone.put(
        //         entry.getKey(),
        //         entry.getValue()
        // ));


        printpassengerdone.forEach(
                (key, value) -> System.out.println(
                        value));
        // использовал forEach() как в лерн через lambda
        // 2error
    }

    public void filtermap_dbforflight() {
        // тут он мне не разрешает писать что то так как это класс
        // сначало этот класс нужно унаследовать
    }


    @Override// 3 error
    public V put(T key, V value) {
        if (!printpassengerorflight.containsKey(key)) {
            return printpassengerorflight.put(key, value);
        } else {
            System.out.println("Object already exists");
            return null;
        }// я тут просто хотел понять как используются методы без унаследования Map<T, V>

    }


    // @Override //это надо использовать в другом файле
    // public void filtermap_dbforflight(String Fromdestin,
    //                                   String Destin,
    //                                   String DepTime,
    //                                   double priceofSeat, Map<T, V> printpassenger) {
    //     Map<T, V> printpassengerdone = printpassenger.entrySet().stream()
    //                                                  .filter(entry -> {
    //                                                      Flight flight
    //                                                              = (Flight) entry.getValue();// 5 error
    //                                                      return flight.getFromdestin()
    //                                                              != null &&
    //                                                              flight.getdestination()
    //                                                                      != null &&
    //                                                              flight.getdepartureTime()
    //                                                                      != null &&
    //                                                              flight.getFromdestin()
    //                                                                    .equals(Fromdestin)
    //                                                              &&
    //                                                              flight.getdestination()
    //                                                                    .equals(Destin)
    //                                                              &&
    //                                                              flight.getdepartureTime()
    //                                                                    .split(" ")[0].equals(
    //                                                                      DepTime.split(
    //                                                                              " ")[0])
    //                                                              &&
    //                                                              flight.getprice()
    //                                                                      <= priceofSeat;
    //                                                  })
    //                                                  .collect(Collectors.toMap(
    //                                                          Map.Entry::getKey,
    //                                                          Map.Entry::getValue));
    //     printpassengerdone.forEach(
    //             (key, value) -> System.out.println(
    //                     value));
    //
    // }


}

