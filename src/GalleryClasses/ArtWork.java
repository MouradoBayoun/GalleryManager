package GalleryClasses;
import java.io.Serializable;
import java.util.*;
/**
 * Created by Omar on 12/13/2016.
 */
public class ArtWork implements Comparable<ArtWork> , Cloneable , Serializable{
    private  String title;
    private  Artist theArtist;
    private  Date productionDate  ;
    transient private static int ids = 0;
    private int id;
    public ArtWork()
    {
        id = ++ids;
        title ="";
        theArtist =new Artist() ;
        productionDate =new Date(1996,1,2);

    }
    public ArtWork(String tit,Artist ans ,Date date)
    {
        id = ++ids;
        title=tit;
        theArtist = new Artist(ans);
        productionDate = date ;
    }
    public ArtWork(ArtWork other)
    {
        id = ++ids;
        title = other.title ;
        theArtist = other.theArtist ;
        productionDate = other.productionDate ;
    }

    public void print(){
        System.out.print(title);
        System.out.println(" , by : " + theArtist.getFirstName() + " " + theArtist.getLastName());
        System.out.println("production Date : " + productionDate);

    }
    @Override
    public ArtWork clone()
    {
        ArtWork copyArtWork ;
        try{
            copyArtWork = (ArtWork) super.clone();
        }catch (CloneNotSupportedException ex)
        {
            copyArtWork = null ;
        }
        return  copyArtWork ;
    }

    public int getId() {
        return id;
    }

    public void setTheArtist(Artist theArtist) {
        this.theArtist = theArtist;
    }

    public Artist getTheArtist() {
        return theArtist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    @Override
    public int compareTo(ArtWork o) {
        return getTitle().compareTo(o.getTitle());
    }
}
