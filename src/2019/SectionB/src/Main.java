import domain.MarketPlace;
import domain.MarketPlaceImpl;
import domain.agents.ChemicalPlant;
import domain.agents.Consumer;
import domain.agents.Manufacturer;
import domain.agents.Agent;
import domain.agents.RecycleCenter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    //Example usage: if there are no deadlocks, the program will continue to execute regularly until
    // interrupted. Do not forget to set MarketPlaceImpl.DEBUG_MESSAGES to true if you want to
    // see some simple debug messages to visually inspect if the process is running as expected.

    //Configuration parameters
    final int numChemicalPlants = 5;
    final int numManufacturers = 10;
    final int numConsumers = 20;
    final int numRecycleCenters = 10;

    final int chemicalPlantsThinkingTime = 500;
    final int manufacturersThinkingTime = 100;
    final int consumersThinkingTime = 300;
    final int recycleCentersThinkingTime = 200;

    final int manufacturersNumBatchesMin = 2;
    final int manufacturersNumBatchesMax = 8;

    // Creation of the market place and of all the threads.
    MarketPlace marketPlace = new MarketPlaceImpl();

    Set<Agent> agents = new HashSet<>();
    Random random = new Random(123);

    IntStream.range(0, numChemicalPlants)
        .forEach(i -> agents.add(new ChemicalPlant(chemicalPlantsThinkingTime, marketPlace)));
    IntStream.range(0, numManufacturers)
        .forEach(i -> agents.add(
            new Manufacturer(
                random.nextInt(manufacturersNumBatchesMax - manufacturersNumBatchesMin)
                + manufacturersNumBatchesMin, manufacturersThinkingTime, marketPlace)));
    IntStream.range(0, numConsumers)
        .forEach(i -> agents.add(new Consumer(consumersThinkingTime, marketPlace)));
    IntStream.range(0, numRecycleCenters)
        .forEach(i -> agents.add(new RecycleCenter(recycleCentersThinkingTime, marketPlace)));

    // Start all threads and then wait for their termination. If the program gets stuck, there may
    // be a deadlock
  }


}
