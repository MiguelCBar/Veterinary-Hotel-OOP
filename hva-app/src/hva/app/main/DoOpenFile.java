package hva.app.main;

import java.io.IOException;

import hva.HotelManager;
import hva.app.exceptions.FileOpenFailedException;
import hva.exceptions.UnavailableFileException;
import hva.exceptions.MissingFileAssociationException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
* Open a file if it exists. 
* Saves file in use if changed.
*/
class DoOpenFile extends Command<HotelManager> {
    DoOpenFile(HotelManager receiver) {
        super(Label.OPEN_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        if (_receiver.isChanged() &&
            Form.confirm(Prompt.saveBeforeExit())) {
            try {
                _receiver.save();
            } catch (IOException e) {
                throw new FileOpenFailedException(e);
            } catch (MissingFileAssociationException e) {
                String filename = Form.requestString(Prompt.newSaveAs());
                try {
                    _receiver.saveAs(filename);
                } catch (IOException e2) {
                    throw new FileOpenFailedException(e2);
                } catch (MissingFileAssociationException e2) {
                    throw new FileOpenFailedException(e2);
                }
            }
        }
        try {
            _receiver.load(Form.requestString(Prompt.openFile()));
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }   
    }           
}



