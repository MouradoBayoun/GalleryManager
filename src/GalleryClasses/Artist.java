package GalleryClasses;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.*;


/**
 * Created by Omar on 12/13/2016.
 */


    public class Artist implements Cloneable , Serializable , Comparable<Artist> {
        private String firstName, lastName;
        private List<String> specializations;
        private boolean isAlive;
        private Pair<Integer, Integer> priceRange; //first is min second is max
        transient private static int ids = 0;
        private int id;
        public Artist(){
            id = ++ids;
            firstName = "";
            isAlive = true;
            lastName = "";
            specializations = new LinkedList<String>();
            priceRange = new Pair<Integer , Integer>(1 , 2);
        }
        public Artist(Artist other)  // copy constructor
        {
            id = ++ids;
            firstName = other.firstName;
            lastName = other.lastName;
            specializations = other.specializations;
            isAlive = other.isAlive;
            priceRange = other.priceRange;
        }
        public Artist(String fn, String ln , boolean isAl ,List<String> spe ,int minPrice , int maxPrice){
            id = ++ids;
            firstName = fn ;
            lastName = ln ;
            isAlive=isAl;
            specializations = spe;
            priceRange = new Pair<Integer,Integer>(minPrice,maxPrice);
            
        }

        public void print(){
            System.out.println(firstName + " " + lastName);
            if(isAlive) System.out.println("Alive"); else System.out.println("Dead");
            System.out.println("Artist specializations : ");
            for (String sp:
                 specializations) {
                System.out.print(sp + " ,");
            }
            System.out.println("\b");
            System.out.println("Price Range : ( " + priceRange.getKey() + " , " + priceRange.getValue() + " )");

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

        public void setAlive(boolean alive) {
            isAlive = alive;
        }

        public boolean isAlive() {
            return isAlive;
        }

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

        public void setPriceRange(Pair<Integer, Integer> priceRange) {
            this.priceRange = priceRange;
        }

        public Pair<Integer, Integer> getPriceRange() {
            return priceRange;
        }

        public void setString(List<String> String) {
            this.specializations = String;
        }

        public List<String> getString() {
            return specializations;
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