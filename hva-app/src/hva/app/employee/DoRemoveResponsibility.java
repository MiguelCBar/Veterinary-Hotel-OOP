package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.NoResponsibilityException;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.exceptions.NoResponsabilityException;
import hva.exceptions.UnknownEmployeeException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoRemoveResponsibility extends Command<Hotel> {

    DoRemoveResponsibility(Hotel receiver) {
        super(Label.REMOVE_RESPONSABILITY, receiver);
        addStringField("key", Prompt.employeeKey());
        addStringField("responsibility", Prompt.responsibilityKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _receiver.removeResponsability(
                stringField("key"),
                stringField("responsibility"));

        } catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(stringField("key"));
        } catch (NoResponsabilityException e) {
            throw new NoResponsibilityException(stringField("key"),
            stringField("responsibility"));
        }
    }
}


