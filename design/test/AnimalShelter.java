
public abstract class Animal {
    private int number;
    public Animal(int n) {number = n;}
    public void setNumber(int n) {}
    public int getNumber(){}
}

public class Dog extends Animal {
    public Dog(int n) {super(n);}
}

public class Cat extends Animal {
    public Cat(int n) {super(n);}
}

public class AnimalShelter {
    private LinkedList<Dog> dogs = new LinkedList<>();
    private LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enqueue(Animal a) {
        a.setNumber(order++);
        if (a instanceof Dog) dogs.addLast((Dog) a);
        else cats.addLast((Cat) a);
    }

    public Animal dequeueAny() {
        if (dogs.size()==0 && cats.size()!=0) return cats.poll();
        else if (dog.size()!=0 && cats.size()==0) return dogs.poll();
        else if (dog.size()==0 && cats.size()==0) return null;

        if (dogs.peek().getNumber() < cats.peek().getNumber()){
            return dogs.poll();
        }
        else{
            return cats.poll();
        }
    }
}
