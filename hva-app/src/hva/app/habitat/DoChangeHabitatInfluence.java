package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.UnknownHabitatException;

import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;

class DoChangeHabitatInfluence extends Command<Hotel> {

    DoChangeHabitatInfluence(Hotel receiver) {
        super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("speciesKey", hva.app.animal.Prompt.speciesKey());
        addOptionField("influence", Prompt.habitatInfluence(), 
            "POS", "NEG", "NEU");
    }

    @Override
    protected void execute() throws CommandException {
        try {

            _receiver.changeHabitatInfluence(
                stringField("habitatKey"),
                stringField("speciesKey"),
                optionField("influence")
            );

        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(
                                stringField("habitatKey"));
        } catch (UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(
                                stringField("speciesKey"));
        }
    }

}
