package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;

import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownHabitatException;

class DoTransferToHabitat extends Command<Hotel> {

    DoTransferToHabitat(Hotel hotel) {
        super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
        addStringField("animalKey", Prompt.animalKey());
        addStringField("habitatKey", 
                        hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.transferToHabitat(
                stringField("animalKey"),
                stringField("habitatKey")
            );  
        } catch (UnknownAnimalException e1) {
            throw new UnknownAnimalKeyException(
                                stringField("animalKey"));
        } catch (UnknownHabitatException e2){
            throw new UnknownHabitatKeyException(
                                stringField("habitatKey"));
        }
    }
}
