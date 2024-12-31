package hva.app.main;

import java.io.IOException;

import hva.HotelManager;
import hva.app.exceptions.FileOpenFailedException;
import hva.exceptions.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoNewFile extends Command<HotelManager> {
    DoNewFile(HotelManager receiver) {
        super(Label.NEW_FILE, receiver);
    }

/**
 * Creates new file.
 */
    
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
                    /** Unreachable */
                    throw new FileOpenFailedException(e2);
                }
            }
        }
        _receiver.newHotel();
    }
}
