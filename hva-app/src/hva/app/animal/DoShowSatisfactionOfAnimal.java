package hva.app.animal;

import hva.Hotel;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.exceptions.UnknownAnimalException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoShowSatisfactionOfAnimal extends Command<Hotel> {

    DoShowSatisfactionOfAnimal(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
        addStringField("key", Prompt.animalKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _display.popup(_receiver.animalSatisfaction(
                stringField("key")));
                
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(stringField("key"));
        }
    }

}
