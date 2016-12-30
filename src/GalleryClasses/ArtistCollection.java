package GalleryClasses;

import java.io.*;
import java.util.*;
import javafx.util.Pair;

/**
 * Created by Omar on 12/14/2016.
 */
public class ArtistCollection {
    private List<Artist> artists;

    public ArtistCollection() {
        artists = new LinkedList<Artist>();
    }

    public void add(Artist newArtist) {
        artists.add(newArtist);
    }

    public void remove(int rId) {
        for (Artist i : artists) {
            if (rId == i.getId()) {
                artists.remove(i);
                break;
            }
        }
    }

    public Artist getArtist(Artist artist) {
        for (Artist i : artists) {
            if (artist.getId() == i.getId()) {
                return i;
            }
        }
        return null;
    }

    public void Edit(Item item, int cost) {
        Artist artist = getArtist(item.getArtWork().getTheArtist());
        if (cost > 0) {
            Pair<Integer, Integer> theNew = new Pair<Integer, Integer>(cost, artist.getPriceRange().getValue());
            artist.setPriceRange(theNew);
        }
        if (cost < 0) {
            Pair<Integer, Integer> theNew = new Pair<Integer, Integer>(artist.getPriceRange().getKey(), cost);
            artist.setPriceRange(theNew);
        }

    }

    public List<Artist> getAliveArtists() {
        List<Artist> aliveArtists = new LinkedList<Artist>();
        for (Artist i : artists) {
            if (i.isAlive()) {
                aliveArtists.add(i);
            }
        }
            return aliveArtists;

    }
    public void writeArtistsOnFolder()
    {
      try{
          File file = new File("C:\\Users\\Omar\\Desktop\\Artists");
          if(!file.exists())
              file.createNewFile();

          FileOutputStream out =new FileOutputStream(file);
          ObjectOutputStream oos = new ObjectOutputStream(out);
          for (Artist i :artists){
              oos.writeObject(i);
              oos.flush();
          }
      }catch (Exception e)
      {
          System.err.println(e);
      }
    }
    public boolean readArtistsFromFolder()
    {
        try {
            File file = new File("C:\\Users\\Omar\\Desktop\\Artists");

            if(file.exists()) {
                FileInputStream in = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(in);
                Artist artist = new Artist();

                while ((artist = (Artist) (ois.readObject())) != null) {
                    System.out.println(artist.getFirstName() + " " + artist.getLastName());
                    artists.add(artist);
                }
            }
            else{
                System.out.println("File not Found");
                return false;
            }
        }catch (EOFException e1)
        {
            return false;
        }
        catch (Exception e)
        {
            System.out.println("Exception Serializable :" + e);
            return false;
        }
        return true;
    }
}