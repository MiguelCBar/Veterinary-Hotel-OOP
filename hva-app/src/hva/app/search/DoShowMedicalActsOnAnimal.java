package hva.app.search;

import hva.Hotel;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.exceptions.UnknownAnimalException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoShowMedicalActsOnAnimal extends Command<Hotel> {

    DoShowMedicalActsOnAnimal(Hotel receiver) {
        super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
        addStringField("animalKey", 
                        hva.app.animal.Prompt.animalKey());
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
        try {
            _display.popup(_receiver.showMedicalActsOnAnimal(
                                stringField("animalKey")));
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(
                                stringField("animalKey"));
        }
    }
}
