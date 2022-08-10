package catHouse.entities.houses;

import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.Collection;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT;
import static catHouse.common.ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY;

public abstract class BaseHouse implements House{

    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public int sumSoftness() {
        long count = toys.stream().map(Toy::getSoftness).count();
        return (int)count;
    }

    @Override
    public void addCat(Cat cat) {
        if(cats.size() == capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public void feeding() {
        cats.stream().forEach(Cat::eating);
    }

    @Override
    public String getStatistics() {
    //"{houseName} {houseType}:
    //Cats: {catName1} {catName2} {catName3} ... / Cats: none
    //Toys: {toysCount} Softness: {sumSoftness}"

        return name + " " + getClass().getSimpleName() + ":" + System.lineSeparator() +
                "Cats: " + (cats.size() == 0 ? "none" : cats.stream().map(Cat::getName).collect(Collectors.joining(" "))) + System.lineSeparator() +
                "Toys: " + toys.size() + " " + "Softness: " + sumSoftness();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
    }

    @Override
    public Collection<Cat> getCats() {
        return cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return toys;
    }
}
