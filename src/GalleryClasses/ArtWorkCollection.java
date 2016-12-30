package GalleryClasses;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Omar on 12/14/2016.
 */
public class ArtWorkCollection {
    private List<ArtWork> artWorksList;

    public ArtWorkCollection(){
        artWorksList = new LinkedList<ArtWork>();
    }
    public void add(ArtWork artWork)
    {
        artWorksList.add(artWork);
    }
    public void remove(int rId)
    {
        for (ArtWork i:artWorksList) {
            if(rId == i.getId()) {
                artWorksList.remove(i);
                break;
            }
        }
    }
}
