package GalleryClasses;

import java.io.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Omar on 12/14/2016.
 */
public class ArtistCollection {

    private ObservableList<Artist> artists = FXCollections.observableArrayList();

    public void add(Artist newArtist) {
        artists.add(newArtist);
    }

    public ObservableList<Artist> getArtists() {
        return artists;
    }

    public void remove(Artist ar) {
        artists.remove(ar);
//        for (Artist i : artists) {
//            if (firstName.equals(i.getFirstName()) && lastName.equals(i.getLastName())) {
//                artists.remove(i);
//                break;
//            }
//        }
    }

    public Artist getArtist(Artist artist) {
        for (Artist i : artists) {
            if (artist.getId() == i.getId()) {

                return i;
            }
        }
        return null;
    }

    public void print() {
        for (Artist i : artists)
            i.print();
    }

    public void Edit(Inventory inventory, int cost) {
        Artist artist = getArtist(inventory.getArtWork().getTheArtist());
        if (cost > artist.getMaxPrice()) {
            Integer newMax = cost;
            artist.setMaxPrice(newMax);
        }
        if (cost < artist.getMinPrice()) {
            Integer newMin = cost;
            artist.setMinPrice(newMin);
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

    public Artist findArtist(String firtName , String lastName){
        for (Artist c:
                artists) {
            if(c.getFirstName().equals(firtName) && c.getLastName().equals(lastName) )
                return c;
        }
        return null;
    }
}