package hva;

import hva.exceptions.ImportFileException;
import hva.exceptions.MissingFileAssociationException;
import hva.exceptions.UnavailableFileException;
import hva.habitats.Season;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class that represents the hotel application.
 */
public class HotelManager {

    /** This is the current hotel. */
    private Hotel _hotel = new Hotel();



    /**
     * Creates the hotel.
     */
    public void newHotel() {
        this._hotel = new Hotel();
    }

    public Hotel getHotel() {return _hotel;}

    /**
     * Saves the serialized application's state into the file associated 
     * to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot 
     *      be created or opened.
     * @throws MissingFileAssociationException if the current network 
     *      does not have a file.
     * @throws IOException if there is some error while serializing the 
     *      state of the network to disk.
     */
    public void save() throws FileNotFoundException, 
                        MissingFileAssociationException, IOException {
        if (_hotel.getFilename().isBlank()) 
            throw new MissingFileAssociationException();

        try (ObjectOutputStream out = new ObjectOutputStream(
                                        new BufferedOutputStream(
                                            new FileOutputStream(
                                                _hotel.getFilename())))) {
            out.writeObject(getHotel());
            _hotel.notChanged();
        }
    }
    /**
     * Saves the serialized application's state into the file associated 
     * to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot 
     *      be created or opened.
     * @throws MissingFileAssociationException if the current network 
     *      does not have a file.
     * @throws IOException if there is some error while serializing the 
     *      state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, 
                            MissingFileAssociationException, IOException {
        _hotel.setFilename(filename);
        save();
    }

    /**
     * @param filename name of the file containing the serialized 
     *      application's state to load.
     * @throws UnavailableFileException if the specified file does not 
     *      exist or there is an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        if (filename == null || filename.equals("")) 
            throw new UnavailableFileException(filename);

        try (ObjectInputStream in = new ObjectInputStream(
                                        new BufferedInputStream(
                                            new FileInputStream(
                                                filename)))) {
            _hotel = (Hotel) in.readObject();
            _hotel.setFilename(filename);
            _hotel.notChanged();
        } catch (IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
    }

    /**
     * Read text input file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename) throws ImportFileException {
        _hotel.importFile(filename);
        _hotel.changed();
    }

    /**
     * @return true if there are unsaved changes on the spreadsheet.
     */
    public boolean isChanged() {
        return getHotel() != null && getHotel().getChanged();
    }

    public Season advanceSeason() {
        if(!getHotel().getChanged())
            getHotel().changed();
        return getHotel().advanceSeason();
    }

    public int showGlobalSatisfaction() {
        return getHotel().calculateGlobalSatisfaction();
    }

}
