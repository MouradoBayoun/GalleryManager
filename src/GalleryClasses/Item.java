package GalleryClasses;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Omar on 12/14/2016.
 */
public class Item implements Serializable , Cloneable{
    private ArtWork artWork;
    private Customer customer ;
    private int purchasePrice , soldPrice ;
    private Date purchaseDate ,soldDate ;
    transient private static int ids = 0;
    private boolean sold;
    private int id;
    private int gain ;

    public Item()
    {
        id = ++ids;

        artWork =new ArtWork();
        customer = new Customer();
        purchasePrice = soldPrice = 0 ;
        purchaseDate = new Date();
        soldDate = new Date();

        sold =false;
        gain = 0 ;
    }

    public Item(ArtWork art , Customer cus , int pp , int sp , Date pd , Date sd)
    {
        id = ++ids;

        artWork = new ArtWork(art) ;
        customer = new Customer(cus) ;
        purchasePrice =pp;
        soldPrice =sp;
        purchaseDate=pd;
        soldDate=sd;
        sold =false;
    }
    public Item(Item other)
    {
        id = ++ids;

        artWork = other.artWork ;
        customer = other.customer ;
        purchasePrice = other.purchasePrice;
        soldPrice = other.soldPrice;
        purchaseDate = other.purchaseDate;
        soldDate = other.soldDate;
        sold=other.sold;
    }

    public void print(){
        artWork.print();

        if(sold){
            System.out.println("Sold out to : " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Purchase Date : " + purchaseDate + " , sold date : " + soldDate);
            System.out.println("Purchase Price : " + purchasePrice + " , sold Price : " + soldPrice);
        }else{
            System.out.println("Purchase Date : " + purchaseDate);
            System.out.println("Purchase Price : " + purchasePrice );
        }

    }

    @Override
    public Item clone()
    {
        Item copyItem;
        try{
            copyItem = (Item) super.clone();
        }catch (CloneNotSupportedException ex)
        {
            copyItem = null ;
        }
        return copyItem;
    }

    public int getId() {
        return id;
    }

    public void setArtWork(ArtWork artWork) {
        this.artWork = artWork;
    }

    public ArtWork getArtWork() {
        return artWork;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setSoldPrice(int soldPrice) {
        this.soldPrice = soldPrice;
    }

    public int getSoldPrice() {
        return soldPrice;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public void setgain(int gain) {
        this.gain = gain;
    }

    public int getgain() {
        return gain;
    }
}

