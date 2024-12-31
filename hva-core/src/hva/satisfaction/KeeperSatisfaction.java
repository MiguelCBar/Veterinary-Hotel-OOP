package hva.satisfaction;

import hva.habitats.Habitat;
import java.io.Serializable;
import java.util.ArrayList;

public class KeeperSatisfaction implements Satisfaction, Serializable{

    private ArrayList<Habitat> _habitats;


    public KeeperSatisfaction(ArrayList<Habitat> habitats) {
        _habitats = habitats;
    }

    public double habitat_work(Habitat habitat) {
        return (habitat.getArea() + 3 * habitat.getPopulation() +
            habitat.getTotalCleaningEffort());
    }

    @Override
    public double calculateSatisfaction() {
        double satisfaction = 0;
        for(Habitat habitat: _habitats)
            satisfaction += (habitat_work(habitat)/habitat.countKeepers());

        satisfaction = 300 - satisfaction;

        return satisfaction;
    }
}
