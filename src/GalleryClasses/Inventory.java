package GalleryClasses;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Omar on 12/14/2016.
 */
public class Inventory {
    public List<Item> listOfSoldedArtWork, listOfAvaliableArtWork;
    public ArtistCollection artists;
    public  CustomerCollection customers;

    public Inventory()
    {
        listOfAvaliableArtWork = new LinkedList<Item>();
        listOfSoldedArtWork = new LinkedList<Item>();
        artists = new ArtistCollection();
        customers = new CustomerCollection();
    }

    public void printAvailable(){
        System.out.println("Available Artworks : \n_____________");
        if(listOfAvaliableArtWork.isEmpty()) System.out.println("There is no available artworks for sale in this Inventory");
        else {
            for (Item item :
                    listOfAvaliableArtWork) {
                item.print();
                System.out.println("------------------------------");
            }
        }
    }
    public void printSolded(){
        System.out.println("Sold out Artworks : \n_____________");
        if(listOfSoldedArtWork.isEmpty()) System.out.println("There is no sold artworks for sale in this Inventory");
        else {
            for (Item item :
                    listOfSoldedArtWork) {
                item.print();
                System.out.println("------------------------------");
            }
        }
    }

    public void sellArtWork(Item item) {
        listOfAvaliableArtWork.remove(item);
        String firstName = item.getCustomer().getFirstName(), lastName = item.getCustomer().getLastName();
        item.setCustomer(customers.getCustomer(firstName, lastName));
        int money = 0;
        item.setSoldPrice(money);
        // item.setSoldDate(Date.now);
        item.setSold(true);
        item.setgain(item.getSoldPrice() - item.getPurchasePrice());
        listOfSoldedArtWork.add(item);
        artists.Edit(item, money);
    }


    public List<Artist> getAliveArtists() {
        List<Artist> aliveArtists = new LinkedList<Artist>();
        for (Artist i : (List<Artist>) artists) {
            if (i.isAlive()) aliveArtists.add(i);
        }
        return aliveArtists;
    }
    public List<Item> getAvailableArtWork()
    {
        return listOfAvaliableArtWork ;
    }
    public CustomerCollection getCustomersForArtist(Artist artist)
    {
        List<Customer> CustomersForArtist = new LinkedList<Customer>() ;
        artist = artists.getArtist(artist) ;
        for (Item i: listOfSoldedArtWork) {
            if (i.getArtWork().getTheArtist().getId() == artist.getId()) {
                CustomersForArtist.add(i.getCustomer());
            }
        }
        return new CustomerCollection(CustomersForArtist) ;
    }
    public long getTotalSales(Date beginDate)
    {
        long sum = 0 ;
        Date endDate = new Date() ;
        endDate.setMonth(beginDate.getMonth()+1);
        for (Item i:listOfSoldedArtWork) {
            if(i.getSoldDate().after(beginDate) && i.getSoldDate().before(endDate))
                sum += i.getgain();
        }
        return sum ;
    }
    public void EditPreference(boolean who, boolean what, Object object, String fn, String ln) // who true for artist ,what true for add
    {
        if (who && what)
            customers.getCustomer(fn, ln).addArtistPreference((Artist) object);
        else if (who && !what)
            customers.getCustomer(fn, ln).removeArtistPreference((Artist) object);
        else if (!who && what)
            customers.getCustomer(fn, ln).addArtWorkPreference((ArtWork) object);
        else
            customers.getCustomer(fn, ln).removeArtWorkPreference((ArtWork) object);
    }
    public void writeAvailableItemsOnFolder()
    {
        try{
            File file = new File("C:\\Users\\Omar\\Desktop\\Available ArtWorks");
            if(!file.exists())
                file.createNewFile();
            FileOutputStream out =new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            for (Item i :listOfAvaliableArtWork
                    ){
                oos.writeObject((Item) i);
                oos.flush();
            }
        }catch (Exception e)
        {
            System.err.println(e);
        }
    }
    public boolean readAvailableItemsFromFolder()
    {
        try {
            File file = new File("C:\\Users\\Omar\\Desktop\\Available ArtWorks");
            if(file.exists()) {

                FileInputStream in = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(in);
                Item item = new Item();

                while ((item = (Item) (ois.readObject())) != null) {
                   // System.out.println(item.getArtWork().getTitle());
                    listOfAvaliableArtWork.add(item);

                }
            }
            else {
                System.out.println("file not found");
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

    public void writeSoldedItemsOnFolder()
    {
        try{
            File file = new File("C:\\Users\\Omar\\Desktop\\Solded ArtWorks");
            if(!file.exists())
                file.createNewFile();
            FileOutputStream out =new FileOutputStream(file);

            ObjectOutputStream oos = new ObjectOutputStream(out);

            for (Item i :listOfSoldedArtWork
                    ){
                oos.writeObject((Item) i);
                oos.flush();
            }
        }catch (Exception e)
        {
            System.err.println(e);
        }
    }
    public boolean readSoldedItemsFromFolder()
    {
        try {
            File file = new File("C:\\Users\\Omar\\Desktop\\Solded ArtWorks");
            if(file.exists()) {
                FileInputStream in = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(in);
                Item item = new Item();
                while ((item = (Item) (ois.readObject())) != null) {
                    //System.out.println(item.getArtWork().getTitle());
                    listOfSoldedArtWork.add(item);

                }
            }else{
                System.out.println("file not found");
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
