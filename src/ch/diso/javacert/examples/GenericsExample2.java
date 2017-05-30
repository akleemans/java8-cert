package ch.diso.javacert.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class GenericsExample2 {

    public static void main(String[] args) {
        // Preparing a taxable container for beers.
        TaxableContainer<Beer> beerContainer = new TaxableContainer<>();
        beerContainer.addTaxables(new Beer());
        beerContainer.addTaxables(new Beer());
        beerContainer.addTaxables(new Beer());
        System.out.println("Total tax rate for beer container:" + beerContainer.getTotal());

        // We can re-use our TaxableContainer class for tobacco:
        TaxableContainer<Tobacco> tobaccoContainer = new TaxableContainer<>();
        tobaccoContainer.addTaxables(new Tobacco());
        System.out.println("Total tax rate for tobacco container:" + tobaccoContainer.getTotal());

        // Other approach: one container for both types, lets to it with Generics! Yay!
        List<? super Taxable> mixedTaxables = new ArrayList<>();
        // (For this example, List<Taxable> would also work...)

        // Now we can both add Beer, Tobacco or other future implementations of Taxable.
        mixedTaxables.add(new Beer());
        mixedTaxables.add(new Tobacco());

        System.out.println("Items in mixedTaxables:" + mixedTaxables.size());

        // Below, we have a class hierachy: Beer -> PaleAle -> IPA
        // Some more fun with wildcards: List<?>, List<? extends type>, List<? super type>

        // Unbounded wildcards: anything is fine
        List<?> allTypesList1 = new ArrayList<Beer>();
        List<?> allTypesList2 = new ArrayList<PaleAle>();
        List<?> allTypesList3 = new ArrayList<IPA>();

        // Upper-Bounded wildcards
        //List<? extends PaleAle> extendList1 = new ArrayList<Beer>(); - Beer does not extend PaleAle
        List<? extends PaleAle> extendList2 = new ArrayList<PaleAle>();
        List<? extends PaleAle> extendList3 = new ArrayList<IPA>();

        // Lower-Bounded wildcards
        List<? super PaleAle> superList1 = new ArrayList<Beer>();
        List<? super PaleAle> superList2 = new ArrayList<PaleAle>();
        //List<? super PaleAle> superList3 = new ArrayList<IPA>(); - IPA is not super of PaleAle


        // Problem when working with **upper bounds** or **unbounded** wildcards:
        // The list becomes immutable!
        List<? extends Number> listWithArbitraryChildren = new ArrayList<>();
        //listWithArbitraryChildren.add(42L);
        // As this could be a List<Long> or any subtype, Java "protects" us by not allowing to modify it.
        // Lower-Bounded lists on the other side are still mutable, as we already saw with our Taxable interface.

        // Another one with Number. With List<? extends Number> the list would be immutable,
        // so let's just declare it with List<Number>, without Generics.
        List<Number> numberList;
        numberList = new ArrayList<>();
        numberList.add(42L);

        // Define a Consumer which will work with any type of List<Number>
        Consumer<Number> numberConsumer = n -> System.out.println("Value: " + n.longValue());
        numberList.forEach(numberConsumer);

        // Add a float
        System.out.println("Adding float (which is also a Number) and printing the numbers again.");
        numberList.add(5.4F);
        numberList.forEach(numberConsumer);
    }
}

class TaxableContainer<T> {

    private List<T> taxables;

    public TaxableContainer() {
        taxables = new ArrayList<>();
    }

    public void addTaxables(T taxable) {
        taxables.add(taxable);
    }

    public long getTotal() {
        // Sum up taxables, if they implement the Taxable interface, else ignore.
        return taxables.stream()
                .map(t -> {
                    if (t instanceof Taxable) {
                        return ((Taxable) t).getRate();
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull).reduce((a, b) -> a + b)
                .orElse(0L);
    }
}

interface Taxable {
    long getRate();
}

class Beer implements Taxable {
    private static long rate = 2L;

    @Override
    public String toString() {
        return "Beer has a tax rate of " + rate;
    }

    @Override
    public long getRate() {
        return rate;
    }
}

class Tobacco implements Taxable {
    private static long rate = 5L;

    @Override
    public String toString() {
        return "Tobacco has a tax rate of " + rate;
    }

    @Override
    public long getRate() {
        return rate;
    }
}

class PaleAle extends Beer {
}

class IPA extends PaleAle {
}