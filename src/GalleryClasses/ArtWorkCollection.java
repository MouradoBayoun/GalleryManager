package GalleryClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Omar on 12/14/2016.
 */
public class ArtWorkCollection {
    public ObservableList<ArtWork> artWorksList = FXCollections.observableArrayList();

    public ObservableList<ArtWork> getArtWorksList() {
        return artWorksList;
    }

    public void add(ArtWork artWork)
    {
        artWorksList.add(artWork);
    }

    public void remove(ArtWork artWork)
    {
        artWorksList.remove(artWork);
    }

    public ArtWork findArtwork(String title){
        for (ArtWork c:
               artWorksList ) {
            if(c.getTitle().equals(title))
                return c;
        }
        return null;
    }
}
