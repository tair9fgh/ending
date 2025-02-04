package reprose2424.respro.entities;

import java.util.Map;
import java.util.stream.Collectors;

public interface IProperPassenger<T, V> extends Map<T, V> {

    // для чего вообще default methods это для того когда есть несколько идей написать функцию а она уже готова в интерфейсе
    // и когда не нужно унаследовать функции от другого класса не меняя классы
    // и когда (думал что могу использовать причину использования уже существующего интерфейса но)
    // один класс не может унаследовать 2 класса что делает интерфейс полезным
    // один класс пишет фильтерб другой класс пишет метод и переписывает хотя все готово может быть
    // 1 error
    V put(T key, V value);// вообще можно было бы использовать list<T> и но в обеих случаях
    // мы используем конструкторы внутри
    // использовал фильтрацию generics так как бывают случаи когда бд имеет более сложные фильтрации

    void filtermap_db(String firstNameLetter, String lastNameLetter,
                      int age);// 4 error

    default void filtermap_db(String firstNameLetter, String lastNameLetter,
                              int age,
                              Map<T, V> printpassenger) {
        Map<T, V> printpassengerdone = printpassenger.entrySet().stream()
                .filter(entry -> {
                    Passenger pass1
                            = (Passenger) entry.getValue();// 5 error
                    return pass1.getlastName()
                            != null &&
                            pass1.getfirstName()
                                    != null &&
                            pass1.getlastName()
                                    .startsWith(
                                            lastNameLetter.substring(
                                                    0,
                                                    1))
                            &&
                            pass1.getfirstName()
                                    .startsWith(
                                            firstNameLetter.substring(
                                                    0,
                                                    1));
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
        printpassengerdone.forEach(
                (key, value) -> System.out.println(
                        value));
    }

    abstract public void filtermap_dbforflight();


    // default void filtermap_dbforflight(String Fromdestin,
    //                                    String Destin,
    //                                    String DepTime,
    //                                    double priceofSeat, Map<T, V> printpassenger) {
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
