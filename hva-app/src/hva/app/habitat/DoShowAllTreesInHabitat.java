package hva.app.habitat;

import hva.Hotel;
import hva.exceptions.UnknownHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownHabitatKeyException;

class DoShowAllTreesInHabitat extends Command<Hotel> {

    DoShowAllTreesInHabitat(Hotel receiver) {
        super(Label.SHOW_TREES_IN_HABITAT, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
    }

    @Override
    protected void execute() throws CommandException {
        try{
            _display.popup(_receiver.showTreesInHabitat(
                                stringField("habitatKey")));
        }catch(UnknownHabitatException e){
            throw new UnknownHabitatKeyException(
                                stringField("habitatKey"));
        }
    }
}
