public class Main {

  public static void main(String[] args) {
    // write your code here
    System.out.println("Program Starting");
    Sanctuary s = new Sanctuary(32, 5, 2, 50);

    System.out.println("Animal Created");
    Animal a = new Animal();
    Animal b = new Animal();
    Animal c = new Animal();

    System.out.println("Animal Recieved");
    s.receiveAnimal(a);
    s.receiveAnimal(b);
    s.receiveAnimal(c);

    System.out.println("Medical Help Received");
    s.giveMedicalHelp(0, "Abhishek", Species.HOWLER, 22, 40, 5, Food.FRUITS, Sex.MALE);
    s.giveMedicalHelp(2, "Ketaki", Species.HOWLER, 22, 40, 5, Food.NUTS, Sex.FEMALE);

    System.out.println("Animals Sent to Enclosure");
    s.sendAllAnimal();
    s.giveMedicalHelp(1, "Test", Species.GUEREZA, 22, 40, 5, Food.FRUITS, Sex.MALE);

    System.out.println("Species Reported");
    s.reportSpecies();

    System.out.println("Species House Based on Species Called");
    s.getSpeciesHouse(Species.HOWLER);

    System.out.println("Sign Produced");
    s.produceSign(1);

    System.out.println("Shopping List Produced");
    s.produceShoppingList();

    System.out.println("Names Produced");
    s.produceNames();

    System.out.println("Isolation Size Increased");
    s.increaseIsolationSize(5);

    System.out.println("Enclosure Size Increased");
    s.increaseEnclosureSize(3);

    Sanctuary s1 = new Sanctuary(2, 3, 4, 20);

    System.out.println("Moved to Different Sanctuary");
    s.moveAnimal(1, s1);
    System.out.println("Program Finished");
  }
}
