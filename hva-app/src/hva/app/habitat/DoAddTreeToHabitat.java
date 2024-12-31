package hva.app.habitat;

import hva.Hotel;
import hva.app.exceptions.DuplicateTreeKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.DuplicateTreeException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnrecognizedEntryException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoAddTreeToHabitat extends Command<Hotel> {

    DoAddTreeToHabitat(Hotel receiver) {
        super(Label.ADD_TREE_TO_HABITAT, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("treeKey", Prompt.treeKey());
        addStringField("treeName", Prompt.treeName());
        addIntegerField("treeAge", Prompt.treeAge());
        addIntegerField("treeDifficulty", Prompt.treeDifficulty());
        addOptionField("treeType", 
                    Prompt.treeType(), "CADUCA", "PERENE");
    }

    @Override
    protected void execute() throws CommandException {
        try{
            _display.popup(_receiver.addTreeToHabitat(
                stringField("habitatKey"),
                stringField("treeKey"),
                stringField("treeName"),
                integerField("treeAge"),
                integerField("treeDifficulty"),
                optionField("treeType")
            ));
        }catch(UnknownHabitatException e){
            throw new UnknownHabitatKeyException(
                                stringField("habitatKey"));
        }catch(DuplicateTreeException e){
            throw new DuplicateTreeKeyException(
                                stringField("treeKey"));
        }catch(UnrecognizedEntryException e){
            e.printStackTrace();
        }
    }
}
