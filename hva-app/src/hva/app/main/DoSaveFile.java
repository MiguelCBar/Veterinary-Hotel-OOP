package hva.app.main;


import java.io.FileNotFoundException;
import hva.HotelManager;
import hva.exceptions.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import java.io.IOException;

/**
 * Saves file.
 */
class DoSaveFile extends Command<HotelManager> {
    DoSaveFile(HotelManager receiver) {
        super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    }

    @Override
    protected final void execute() {
    	//FIXME implement command using a local form
        try {
            _receiver.save();
        } catch (MissingFileAssociationException e) {
            String filename = Form.requestString(Prompt.newSaveAs());
            try {
                _receiver.saveAs(filename);
            } catch (IOException e2) {
            } catch (MissingFileAssociationException e2) {
            }
        } catch (FileNotFoundException e) {  
        } catch (IOException e) {
        }
    }
}
