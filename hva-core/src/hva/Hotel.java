package hva;


import hva.animals.*;
import hva.employees.*;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.DuplicateHabitatException;
import hva.exceptions.DuplicateSpeciesNameException;
import hva.exceptions.DuplicateTreeException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.ImportFileException;
import hva.exceptions.NoResponsabilityException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.UnknownTreeException;
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.UnknownVeterinarianException;
import hva.exceptions.UnrecognizedEntryException;
import hva.exceptions.VaccineNotAdequateException;
import hva.exceptions.VeterinarianNotAuthException;
import hva.habitats.*;
import hva.habitats.deciduous.Deciduous;
import hva.habitats.evergreen.Evergreen;
import hva.searchpredicate.SearchPredicate;
import hva.vaccines.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private String _filename = "";

    private Season _currentSeason = Season.SPRING;

    /** 
     * Whether there are unsaved changes 
     */
    private boolean _changed = false;

    private Comparator<String> _caseInsensitiveComparator = 
                                        String.CASE_INSENSITIVE_ORDER;

    private Map<String, Animal> _animals = 
                    new TreeMap<String, Animal>(_caseInsensitiveComparator);
    private Map<String, Species> _species = 
                    new TreeMap<String, Species>(_caseInsensitiveComparator);
    private Map<String, Habitat> _habitats = 
                    new TreeMap<String, Habitat>(_caseInsensitiveComparator);
    private Map<String, Vaccine> _vaccines = 
                    new TreeMap<String, Vaccine>(_caseInsensitiveComparator);
    private Map<String, Tree> _trees = 
                    new TreeMap<String, Tree>(_caseInsensitiveComparator);
    private Map<String, Employee> _employees = 
                    new TreeMap<String, Employee>(_caseInsensitiveComparator);
    private List<Vaccination> _vaccinations =
                    new ArrayList<Vaccination>();

    /**
     * There are unsaved changes.
     */
    public void changed() {
        _changed = true;
    }

    /**
     * There are no unsaved changes.
     */
    public void notChanged() {
        _changed = false;
    }

    /**
     * @return whether there are unsaved changes.
     */
    public boolean getChanged() {
        return _changed;
    }

    public String getFilename() {return _filename;}

    public void setFilename(String filename) {_filename = filename;}

    public void setSeason(Season season) {
        _currentSeason = season;
    }

    public Season getSeason() {return _currentSeason;}

    public Season advanceSeason() {
        _currentSeason = nextSeason(_currentSeason);
        updateTreeSeasonalCycle();
        return _currentSeason;
    }

    private Season nextSeason(Season currentSeason) {
        int nextInOrder = (currentSeason.ordinal() + 1) % Season.values().length;
        return Season.values()[nextInOrder];
    }

    public void updateTreeSeasonalCycle() {
        for (Tree tree : _trees.values()) {
            tree.updateCycle();
        }
    }

    public int calculateGlobalSatisfaction() {
        double total = 0;
        for(Animal animal: _animals.values()) {
            total += animal.calculateSatisfaction();
        }
        for(Employee employee: _employees.values()) {
            total += employee.calculateSatisfaction();
        }
        return (int) Math.round(total);
    }

    /**
     * Read text input file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    void importFile(String filename) throws ImportFileException {

        try (BufferedReader reader = new BufferedReader(
                                        new FileReader(filename))){
            String line;
            while((line = reader.readLine()) != null){
                String[] entry = line.split("\\|");
                try {
                    handleRegistration(entry);
                } catch (
                DuplicateHabitatException | DuplicateTreeException |
                DuplicateAnimalException | DuplicateEmployeeException |
                DuplicateVaccineException | UnknownSpeciesException |
                UnknownHabitatException | UnrecognizedEntryException |
                UnknownTreeException | DuplicateSpeciesNameException e2) {
                    throw new ImportFileException(filename, e2);
                }
            }
            changed();
        } catch (IOException e1) {
            throw new ImportFileException(filename, e1);
        }
    }

    /**
     * Choose the correct registration
     * 
     * @param entry input textline
     * @throws DuplicateSpeciesException
     * @throws DuplicateHabitatException
     * @throws DuplicateTreeException
     * @throws DuplicateAnimalException
     * @throws DuplicateEmployeeException
     * @throws DuplicateVaccineException
     * @throws UnknownSpeciesException
     * @throws UnknownHabitatException
     * @throws UnrecognizedEntryException
     * @throws UnknownTreeException 
     * @throws NumberFormatException 
    */
    public void handleRegistration(String[] entry) 
        throws DuplicateHabitatException, 
                DuplicateTreeException, DuplicateAnimalException,
                DuplicateEmployeeException, DuplicateVaccineException, 
                UnknownSpeciesException, DuplicateSpeciesNameException,
                UnknownHabitatException, UnrecognizedEntryException, 
                NumberFormatException, UnknownTreeException,
                DuplicateSpeciesNameException {

        switch (entry[0]) {
            case "ESPÉCIE" -> {
                registerSpecies(entry[1], entry[2]);
            }
            case "ÁRVORE" -> {
                registerTree(entry[1], entry[2], Integer.parseInt(entry[3]),
                    Integer.parseInt(entry[4]), entry[5]);
            }
            case "HABITAT" -> {
                if (entry.length > 4) {
                    registerHabitat(entry[1], entry[2], 
                        Integer.parseInt(entry[3]), entry[4]);
                } else {
                    registerHabitat(entry[1], entry[2], 
                        Integer.parseInt(entry[3]), "");
                }
            }
            case "ANIMAL" -> {
                registerAnimal(entry[1], entry[2], entry[3], entry[4]);
            }
            case "TRATADOR", "VETERINÁRIO" -> {
                if (entry.length > 3) {
                    registerEmployee(entry[0], entry[1], entry[2], entry[3]);
                } else {
                    registerEmployee(entry[0], entry[1], entry[2], "");
                }
            }
            case "VACINA" -> {
                if (entry.length > 3) {
                    registerVaccine(entry[1], entry[2], entry[3]);
                } else {
                    registerVaccine(entry[1], entry[2], "");
                }
            }
        }
    }

    /**
     * Register species
     * 
     * @param speciesKey species Key Identifier
     * @param speciesName species name
     * @throws DuplicateSpeciesException
     */
    public void registerSpecies(String speciesKey, String speciesName) 
        throws DuplicateSpeciesNameException{
        for (Species species : _species.values()) {
            if (species.getName().equalsIgnoreCase(speciesName)) {
                throw new DuplicateSpeciesNameException(speciesName);
            }
        }
        Species newSpecies = new Species(speciesKey, speciesName);
        _species.put(speciesKey, newSpecies);
    }

    /**
     * Register animal
     * 
     * @param animalKey animal Key Identifier
     * @param animalName animal name
     * @param speciesKey species Key Identifier
     * @param habitatKey habitat Key Identifier
     * @throws UnknownSpeciesException
     * @throws UnknownHabitatException
     */
    public void registerAnimal(String animalKey, String animalName, 
        String speciesKey, String habitatKey) 
            throws UnknownSpeciesException, UnknownHabitatException,
                    DuplicateAnimalException {

        Animal existingAnimal = _animals.get(animalKey);
        if(existingAnimal != null)
            throw new DuplicateAnimalException(animalKey);

        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null)
            throw new UnknownHabitatException(habitatKey);

        Species species = _species.get(speciesKey);
        if(species == null)
            throw new UnknownSpeciesException(speciesKey);

        Animal animal = new Animal(animalKey, animalName, species, habitat);
        species.addAnimalResgistry(animal);
        habitat.addAnimal(animal);
        _animals.put(animal.getKey(), animal);
        if(!getChanged())
            changed();
    }

    /**
     * Register habitat
     * 
     * @param habitatKey habitat Key Identifier
     * @param habitatName habitat name
     * @param area habitat area
     * @param trees trees planted in habitat
     * @throws DuplicateHabitatException
     * @throws UnknownTreeException 
     */
    public void registerHabitat(String habitatKey, String habitatName, 
        int area, String trees) throws DuplicateHabitatException{
        if(_habitats.containsKey(habitatKey))
            throw new DuplicateHabitatException(habitatKey);
        Habitat habitat = new Habitat(habitatKey, habitatName, area);
        if (!trees.isEmpty()) {
            String[] treesKeys = trees.split(",");
            for (String treesKey : treesKeys) {
                habitat.plantTree(_trees.get(treesKey));
            }
        }
        _habitats.put(habitatKey, habitat);
        if(!getChanged())
            changed();
    }

    /**
     * Register vaccine
     *
     * @param vaccineKey vaccine Key Identifier
     * @param vaccineName vaccine name
     * @param species suitable species
     * @throws DuplicateVaccineException
     * @throws UnknownSpeciesException
     */
    public void registerVaccine(String vaccineKey, String vaccineName, 
        String species) throws DuplicateVaccineException, 
            UnknownSpeciesException{
        if(_vaccines.containsKey(vaccineKey))
            throw new DuplicateVaccineException(vaccineKey);
        Vaccine vaccine = new Vaccine(vaccineKey, vaccineName);
        if (!species.isEmpty()) {
            String[] speciesKeys = species.split(",");

            for (String speciesKey : speciesKeys) {
                speciesKey = speciesKey.trim();
                if (!_species.containsKey(speciesKey)) {
                    throw new UnknownSpeciesException(speciesKey);
                }
                vaccine.addSpecies(_species.get(speciesKey));
            }
        }
        _vaccines.put(vaccineKey, vaccine);
        if(!getChanged())
            changed();
    }

    /**
     * Register employee
     * 
     * @param type function of employee
     * @param employeeKey employee Key Identifier
     * @param name employee name
     * @param responsibility employee responsibilities
     * @throws UnrecognizedEntryException
     * @throws DuplicateEmployeeException
     * @throws UnknownHabitatException
     * @throws UnknownSpeciesException
     */

    public void registerEmployee(String type, String employeeKey, 
    String name, String responsibility)
        throws UnrecognizedEntryException, DuplicateEmployeeException{

        if (_employees.containsKey(employeeKey)) {
            throw new DuplicateEmployeeException(employeeKey);
        }

        Employee employee = switch (type) {
            case "TRATADOR", "TRT" -> {
                Keeper keeper = new Keeper(employeeKey, name);
                if (!responsibility.isEmpty()) {
                    String[] habitatKeys = responsibility.split(",");
                    for (String habitatKey : habitatKeys) {
                        habitatKey = habitatKey.trim();
                        Habitat habitat = _habitats.get(habitatKey);
                        keeper.addResponsability(habitat);
                    }
                }
                yield keeper;
            }
            case "VETERINÁRIO", "VET" -> {
                Veterinarian veterinarian = new Veterinarian(employeeKey, name);
                if (!responsibility.isEmpty()) {
                    String[] speciesKeys = responsibility.split(",");
                    for (String speciesKey : speciesKeys) {
                        speciesKey = speciesKey.trim();
                        Species species = _species.get(speciesKey);
                        veterinarian.addResponsability(species);
                    }
                }
                yield veterinarian;
            }
            default -> {
                throw new UnrecognizedEntryException(type);
            }
        };
        _employees.put(employeeKey, employee);
        if(!getChanged())
            changed();
    }

    /**
     * Register tree
     * 
     * @param treeKey tree Key Identifier
     * @param treeName tree name
     * @param age 
     * @param baseCleaningDiff
     * @param type type of tree
     * @throws UnrecognizedEntryException
     * @throws DuplicateTreeException
     */
    public void registerTree(String treeKey, String treeName, int age, 
    int baseCleaningDiff, String type)
        throws UnrecognizedEntryException, DuplicateTreeException{

        if(_trees.containsKey(treeKey))
            throw new DuplicateTreeException(treeKey);
        var tree = switch(type){
            case "PERENE" -> new Evergreen(treeKey, treeName, 
                                                    baseCleaningDiff);
            case "CADUCA" -> new Deciduous(treeKey, treeName, 
                                                    baseCleaningDiff);
            default -> throw new UnrecognizedEntryException(type);
        };
        tree.initiateSeasonalCycle(getSeason(), age);
        _trees.put(treeKey, tree);
        if(!getChanged())
            changed();
    }

    public Collection<Animal> findAnimals(SearchPredicate<Animal> predicate) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : _animals.values()) {
            if (predicate.test(animal)) {
                result.add(animal);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public Collection<Animal> showAnimals() {
        return findAnimals(a -> true);
    }

    public Collection<Habitat> findHabitats(SearchPredicate<Habitat> predicate) {
        List<Habitat> result = new ArrayList<>();
        for (Habitat habitat : _habitats.values()) {
            if (predicate.test(habitat)) {
                result.add(habitat);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public Collection<Habitat> showHabitats() {
        return findHabitats(h -> true);
    }

    public Collection<Tree> showTreesInHabitat(String habitatKey)
        throws UnknownHabitatException {
        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null)
            throw new UnknownHabitatException(habitatKey);
        return habitat.findTrees(t -> true);
    }

    public Collection<Animal> showAnimalsInHabitat(String habitatKey)
        throws UnknownHabitatException {
        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null)
            throw new UnknownHabitatException(habitatKey);
        return habitat.findAnimalsHabitat(a -> true);
    }

    public Collection<Vaccine> findVaccines(SearchPredicate<Vaccine> predicate) {
        List<Vaccine> result = new ArrayList<>();
        for (Vaccine vaccine : _vaccines.values()) {
            if (predicate.test(vaccine)) {
                result.add(vaccine);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public Collection<Vaccine> showVaccines() {
        return findVaccines(v -> true);
    }

    public Collection<Employee> findEmployees(
                SearchPredicate<Employee> predicate) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : _employees.values()) {
            if (predicate.test(employee)) {
                result.add(employee);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public Collection<Employee> showEmployees() {
        return findEmployees(e -> true);
    }

    public Collection<Vaccination> findVaccinations(
                SearchPredicate<Vaccination> predicate) {
        List<Vaccination> result = new ArrayList<>();
        for (Vaccination vaccination : _vaccinations) {
            if (predicate.test(vaccination)) {
                result.add(vaccination);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public Collection<Vaccination> showWrongVaccinations() {
        return findVaccinations(v -> v.getDamage() != Damage.NORMAL);
    }

    public Collection<Vaccination> showVaccinations() {
        return findVaccinations(v -> true);
    }

    public Collection<Vaccination> showMedicalActsOnAnimal(String animalKey)
        throws UnknownAnimalException {
        Animal animal = _animals.get(animalKey);
        if (animal == null) {
            throw new UnknownAnimalException(animalKey);
        }
        return findVaccinations(v -> v.getAnimal().getKey().equals(animalKey));
    }

    public Collection<Vaccination> showMedicalActsByVeterinarian(
        String veterinarianKey) throws UnknownVeterinarianException {
        Employee employee = _employees.get(veterinarianKey);
        if (employee == null || !(employee instanceof Veterinarian)) {
            throw new UnknownVeterinarianException(veterinarianKey);
        }
        return findVaccinations(v -> 
            v.getVeterinarian().getKey().equals(veterinarianKey));
    }

    /**
     * Change a specific habitat area
     * 
     * @param habitatKey habitat Key Identifier
     * @param area new habitat area
     */
    public void changeHabitatArea(String habitatKey, int area) 
    throws UnknownHabitatException {
        if(!_habitats.containsKey(habitatKey))
            throw new UnknownHabitatException(habitatKey);

        Habitat habitat = _habitats.get(habitatKey);
        habitat.setArea(area);
        if(!getChanged())
            changed();
    }


    public void changeHabitatInfluence(String habitatKey, String speciesKey,
        String influence) throws UnknownHabitatException,
        UnknownSpeciesException {

        Habitat habitat = _habitats.get(habitatKey);
        if(habitat == null)
            throw new UnknownHabitatException(habitatKey);

        Species species = _species.get(speciesKey);
        if(species == null)
            throw new UnknownSpeciesException(speciesKey);
        
        habitat.changeSpeciesInfluence(species, influence);
        if(!getChanged())
            changed();

    }

    /**
     * Transfer a specific animal to a certain habitat
     * 
     * @param animalKey animal Key Identifier
     * @param habitatKey habitat Key Identifier
     */
    public void transferToHabitat(String animalKey, String habitatKey) 
        throws UnknownAnimalException, UnknownHabitatException{

        Animal animal = _animals.get(animalKey);
        if (animal == null) {
            throw new UnknownAnimalException(animalKey);
        }

        Habitat habitat = _habitats.get(habitatKey);
        if (habitat == null) {
            throw new UnknownHabitatException(habitatKey);
        }

        animal.getHabitat().removeAnimal(animalKey);
        animal.setHabitat(habitat);
        habitat.addAnimal(animal);
        if(!getChanged())
            changed();
    }

    public int animalSatisfaction(String animalKey)
        throws UnknownAnimalException {
        Animal animal = _animals.get(animalKey);
        if(animal == null)
            throw new UnknownAnimalException(animalKey);
        return (int) Math.round(animal.calculateSatisfaction());
    }

    public int employeeSatisfaction(String employeeKey)
        throws UnknownEmployeeException {
        Employee employee = _employees.get(employeeKey);
        if(employee == null)
            throw new UnknownEmployeeException(employeeKey);
        
        return (int) Math.round(employee.calculateSatisfaction());
    }

    public void addResponsability(String employeeKey, String responsability)
        throws UnknownEmployeeException, NoResponsabilityException {

        Employee employee = _employees.get(employeeKey);
        if(employee == null)
            throw new UnknownEmployeeException(employeeKey);

        if(employee instanceof Keeper) {
            Keeper employeeKeeper = (Keeper) employee;

            Habitat habitat = _habitats.get(responsability);
            if(habitat == null)
                throw new NoResponsabilityException(responsability);

            employeeKeeper.addResponsability(habitat);
        }
        else if(employee instanceof Veterinarian){
            Veterinarian employeeVeterinarian = (Veterinarian) employee;

            Species species = _species.get(responsability);
            if(species == null)
                throw new NoResponsabilityException(responsability);

            employeeVeterinarian.addResponsability(species);
        }
        if(!getChanged())
            changed();
    }

    public void removeResponsability(String employeeKey, String responsability)
    throws UnknownEmployeeException, NoResponsabilityException{

        Employee employee = _employees.get(employeeKey);
        if(employee == null)
            throw new UnknownEmployeeException(employeeKey);

        if(!employee.containsResponsability(responsability))
            throw new NoResponsabilityException(responsability);
        
        employee.removeResponsability(responsability);
        if(!getChanged())
            changed();
        
    }

    public Tree addTreeToHabitat(String habitatKey, String treeKey,
        String treeName, int treeAge, int treeDifficulty, String treeType)
        throws UnknownHabitatException, DuplicateTreeException, 
        UnrecognizedEntryException {
        if(!_habitats.containsKey(habitatKey))
            throw new UnknownHabitatException(habitatKey);
        if(_trees.containsKey(treeKey))
            throw new DuplicateTreeException(treeKey);
        Habitat habitat = _habitats.get(habitatKey);
        Tree tree = switch(treeType) {
            case "PERENE" -> new Evergreen(treeKey, treeName,
                                                    treeDifficulty);
            case "CADUCA" -> new Deciduous(treeKey, treeName,
                                                    treeDifficulty);
            default -> throw new UnrecognizedEntryException(treeType);
        };
        tree.initiateSeasonalCycle(_currentSeason, treeAge);
        habitat.plantTree(tree);
        _trees.put(treeKey, tree);
        if(!getChanged())
            changed();
        return tree;
    }

    public void vaccinateAnimal(String vaccineKey, String veterinarianKey, 
    String animalKey)
    throws UnknownVaccineException, UnknownAnimalException, 
            UnknownVeterinarianException, VeterinarianNotAuthException,
            VaccineNotAdequateException {
        Vaccine vaccine = _vaccines.get(vaccineKey);
        if(vaccine == null)
            throw new UnknownVaccineException(vaccineKey);
        Employee employee = _employees.get(veterinarianKey);
        if(employee == null || !(employee instanceof Veterinarian))
            throw new UnknownVeterinarianException(veterinarianKey);
        Animal animal = _animals.get(animalKey);
        if(animal == null)
            throw new UnknownAnimalException(animalKey);
        Veterinarian veterinarian = (Veterinarian) employee;
        if(!veterinarian.containsResponsability(animal.getSpecies().getKey()))
            throw new VeterinarianNotAuthException(veterinarianKey, 
                animal.getSpecies().getKey());

        if(!vaccine.checkSpeciesAdequacy(animal.getSpecies().getKey())){
            Damage damage = vaccine.calculateDamage(
                        animal.getSpecies().getName());
            Vaccination vaccination = 
                    new Vaccination(veterinarian, vaccine, animal, damage);
            _vaccinations.add(vaccination);
            vaccine.addVacination(vaccination);
            animal.addVaccination(vaccination);
            if(!getChanged())
                changed();
            throw new VaccineNotAdequateException(vaccineKey, animalKey);
        }
        Vaccination vaccination = 
                    new Vaccination(veterinarian, vaccine, animal, Damage.NORMAL);
        _vaccinations.add(vaccination);
        vaccine.addVacination(vaccination);
        animal.addVaccination(vaccination);
        if(!getChanged())
            changed();
    }
}
