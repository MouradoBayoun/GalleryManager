package GalleryClasses;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.*;


/**
 * Created by Omar on 12/13/2016.
 */


    public class Artist implements Cloneable , Serializable , Comparable<Artist> {
        private String firstName, lastName;
        private List<String> specializations = new ArrayList<>();
        private Boolean isAlive;
        private Integer minPrice , maxPrice; //first is min second is max
        transient private static int ids = 0;
        private int id;
        public Artist(){
            id = ++ids;
            firstName = null;
            isAlive = true;
            lastName =  null;
            specializations = new LinkedList<String>();
            minPrice = 0;
            maxPrice = 10;
        }
        public Artist(Artist other)  // copy constructor
        {
            id = ++ids;
            firstName = other.firstName;
            lastName = other.lastName;
            specializations = other.specializations;
            isAlive = other.isAlive;
            minPrice = other.minPrice;
            maxPrice = other.maxPrice;
        }
        public Artist(String fn, String ln , boolean isAl ,List<String> spe ,int minPrice , int maxPrice){
            id = ++ids;
            firstName = fn ;
            lastName = ln ;
            isAlive= isAl;
            specializations.addAll(spe);
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
        }

        public void print(){
            System.out.println(firstName + " " + lastName);
            if(isAlive) System.out.println("Alive"); else System.out.println("Dead");
            System.out.println("Artist specializations : ");
            System.out.println(getSpecializations());
            System.out.println("Price Range : ( " + minPrice + " , " + maxPrice + " )");
        }
        @Override
        public Artist clone()
        {
            Artist copyArtist ;
                try{
                        copyArtist = (Artist)super.clone();
                }catch (CloneNotSupportedException ex)
                {
                    copyArtist = null ;
                }
                return  copyArtist ;
        }
        public int getId()
        {
            return id;
        }

        public void setAlive(Boolean alive) {
            isAlive =  alive  ;
        }

        public Boolean isAlive() {
            return isAlive;
        }

        //public BooleanProperty isAliveProperty() {return isAlive;}

        public void setFirstName(String firstName) {
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

//        public StringProperty firtNameProperty(){
//            return firstName;
//        }
//        public StringProperty lastNameProperty(){
//            return lastName;
//        }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setSpecializations(List<String> specializations) {
            this.specializations = specializations;
        }

        public String getSpecializations() {
            String tmp = "";
            for (String s:
                 specializations) {
                tmp += s + " ,";
            }
            if(tmp.length() > 1)
                 tmp = tmp.substring(0 , tmp.length()-1);
            return tmp;
        }

        public void addSpe(String newOne)
        {
            specializations.add(newOne);
        }


    @Override
    public int compareTo(Artist o) {
        return this.getFirstName().compareTo(o.getFirstName());
    }

}