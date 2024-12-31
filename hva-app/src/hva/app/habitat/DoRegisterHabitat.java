package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.app.exceptions.DuplicateHabitatKeyException;
import hva.exceptions.DuplicateHabitatException;

class DoRegisterHabitat extends Command<Hotel> {

    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);
        addStringField("key", Prompt.habitatKey());
        addStringField("name", Prompt.habitatName());
        addIntegerField("area", Prompt.habitatArea());
    }

    @Override
    protected void execute() throws CommandException {
        try{
            _receiver.registerHabitat(
                stringField("key"),
                stringField("name"),
                integerField("area"),
                ""
            );
        }catch(DuplicateHabitatException e){
            throw new DuplicateHabitatKeyException(stringField("key"));
        }
    }

}
