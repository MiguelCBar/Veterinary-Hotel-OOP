package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.DuplicateEmployeeKeyException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.UnrecognizedEntryException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoRegisterEmployee extends Command<Hotel> {

    DoRegisterEmployee(Hotel receiver) {
        super(Label.REGISTER_EMPLOYEE, receiver);
        addStringField("key", Prompt.employeeKey());
        addStringField("name", Prompt.employeeName());
        addOptionField("type", 
                    Prompt.employeeType(), "VET", "TRT");
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _receiver.registerEmployee(
                optionField("type"),
                stringField("key"),
                stringField("name"),
                ""
            );
        } catch (DuplicateEmployeeException e) {
            throw new DuplicateEmployeeKeyException(stringField("key"));
        } catch (UnrecognizedEntryException e) {
            e.printStackTrace();
        }
    }

}
