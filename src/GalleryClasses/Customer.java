package GalleryClasses;
import java.io.Serializable;
import java.util.*;
/**
 * Created by Omar on 12/13/2016.
 */

 public class Customer implements Comparable<Customer> , Cloneable , Serializable{
    private String firstName , lastName;
    private String phoneNumber ,address;
    private List<Artist> artistPreferences;
    private List<ArtWork> artWorkPreferences;
    transient private static int ids = 0;
    private int id;
    public  Customer()
    {
        id = ++ids;

        firstName = lastName = phoneNumber = address = "" ;
        artistPreferences = new LinkedList<>();
        artWorkPreferences= new LinkedList<>();
    }
    public Customer(String fn, String ln ,String pN ,String add ,List<Artist> aP , List<ArtWork> artP)
    {
        id = ++ids;

        firstName = fn ;
        lastName = ln ;
        phoneNumber = pN ;
        address = add ;
        artistPreferences = aP ;
        artWorkPreferences = artP ;
    }
    public Customer (Customer other)
    {
        id = ++ids;

        firstName = other.firstName ;
        lastName =other.lastName ;
        phoneNumber =other.phoneNumber ;
        address =other.address ;
        artistPreferences =other.artistPreferences ;
        artWorkPreferences =other.artWorkPreferences ;
    }
    public void print(){
        System.out.println(firstName + " " + lastName);
        System.out.println("Phone Number : " + phoneNumber + " , Address : " + address);
        System.out.println("Artist  Preferences: ");
        for (Artist sp:
                artistPreferences) {
            System.out.println("\t" + sp.getFirstName() + " " + sp.getLastName());
        }
        System.out.println("Artwork  Preferences: ");
        for (ArtWork sp:
                artWorkPreferences) {
            System.out.println("\t" + sp.getTitle());
        }
    }
    @Override
    public Customer clone()
    {
        Customer copyCustomer ;
        try{
            copyCustomer = (Customer) super.clone();
        }catch (CloneNotSupportedException ex)
        {
            copyCustomer = null ;
        }
        return  copyCustomer ;
    }

    public int getId() {
        return id;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setArtistPreferences(List<Artist> artistPreferences) {
        this.artistPreferences = artistPreferences;
    }

    public List<Artist> getArtistPreferencesList() {
        return artistPreferences;
    }

    public String getArtistPreferences(){
        String tmp = "";
        for (Artist i:
            artistPreferences ) {
            tmp += i.getFirstName() + " " + i.getLastName() + " ,";
        }
        if(tmp.length() > 1)
            tmp = tmp.substring(0 , tmp.length() - 1);
        return tmp;
    }

    public void setArtWorkPreferences(List<ArtWork> artWorkPreferences) {
        this.artWorkPreferences = artWorkPreferences;
    }

    public List<ArtWork> getArtWorkPreferences() {
        return artWorkPreferences;
    }

    public void addArtWorkPreference(ArtWork newArtWork){
        artWorkPreferences.add(newArtWork);
    }
    public void addArtistPreference(Artist newArtist){
        artistPreferences.add(newArtist);
    }
    public void removeArtWorkPreference(ArtWork newArtWork){
        artWorkPreferences.remove(newArtWork.getId());
    }
    public void removeArtistPreference(Artist newArtist){
        artistPreferences.remove(newArtist.getId());
    }

    @Override
    public int compareTo(Customer o) {
        return getFirstName().compareTo(o.getFirstName());
    }
}
