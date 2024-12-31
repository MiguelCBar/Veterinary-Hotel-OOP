package hva.app.search;

import hva.Hotel;
import hva.exceptions.UnknownVeterinarianException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

    DoShowMedicalActsByVeterinarian(Hotel receiver) {
        super(Label.MEDICAL_ACTS_BY_VET, receiver);
        addStringField("veterinarianKey", 
                        hva.app.employee.Prompt.employeeKey());
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
        try {
            _display.popup(_receiver.showMedicalActsByVeterinarian(
                                stringField("veterinarianKey")));
        }catch (UnknownVeterinarianException e){
            throw new UnknownVeterinarianKeyException(
                                stringField("veterinarianKey"));
        }
    }

}
