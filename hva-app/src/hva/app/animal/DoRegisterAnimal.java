package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.app.exceptions.DuplicateAnimalKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateSpeciesNameException;

class DoRegisterAnimal extends Command<Hotel> {

    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        addStringField("key", Prompt.animalKey());
        addStringField("name", Prompt.animalName());
        addStringField("speciesKey", Prompt.speciesKey());
        addStringField("habitatKey", 
                            hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.registerAnimal(
                stringField("key"),
                stringField("name"),
                stringField("speciesKey"),
                stringField("habitatKey"));
        } catch (DuplicateAnimalException e) {

            throw new DuplicateAnimalKeyException(
                stringField("key"));
        } catch (UnknownSpeciesException e) {
            String speciesName = Form.requestString(Prompt.speciesName());
            try {
                _receiver.registerSpecies(
                    stringField("speciesKey"),
                    speciesName);

                _receiver.registerAnimal(
                    stringField("key"),
                    stringField("name"),
                    stringField("speciesKey"),
                    stringField("habitatKey"));

            } catch (DuplicateSpeciesNameException e1) {

                e1.printStackTrace();
            } catch (DuplicateAnimalException e1) {

                throw new DuplicateAnimalKeyException(
                    stringField("key"));
            } catch (UnknownHabitatException e1) {

                throw new UnknownHabitatKeyException(
                    stringField("habitatKey"));
            } catch (UnknownSpeciesException e1) {
                
                throw new UnknownSpeciesKeyException(
                    stringField("speciesKey"));
            }
        } catch (UnknownHabitatException e) {

            throw new UnknownHabitatKeyException(
                    stringField("habitatKey"));
        }
    }

}
