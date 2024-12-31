package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.DuplicateVaccineKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;

import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.UnknownSpeciesException;

class DoRegisterVaccine extends Command<Hotel> {

    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);
        addStringField("key", Prompt.vaccineKey());
        addStringField("name", Prompt.vaccineName());
        addStringField("listofspecies", Prompt.listOfSpeciesKeys());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.registerVaccine(
                stringField("key"),
                stringField("name"),
                stringField("listofspecies")
            );
        } catch (DuplicateVaccineException e) {
            throw new DuplicateVaccineKeyException(stringField("key"));
        } catch (UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(e.getKey());
        }
    }

}
