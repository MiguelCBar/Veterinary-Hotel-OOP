package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.NoResponsibilityException;
import hva.app.exceptions.UnknownEmployeeKeyException;

import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.NoResponsabilityException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoAddResponsibility extends Command<Hotel> {

    DoAddResponsibility(Hotel receiver) {
        super(Label.ADD_RESPONSABILITY, receiver);
        addStringField("key", Prompt.employeeKey());
        addStringField("responsability", Prompt.responsibilityKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _receiver.addResponsability(
                stringField("key"),
                stringField("responsability"));

        } catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(stringField("key"));
        } catch (NoResponsabilityException e) {
            throw new NoResponsibilityException(stringField("key"),
            stringField("responsability"));
        }
    }

}
