package GalleryClasses;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Omar on 12/14/2016.
 */
public class CustomerCollection {
    private List<Customer> costumers;
    public CustomerCollection(){
        costumers = new LinkedList<Customer>();
    }
    public CustomerCollection(List<Customer> cs){
        costumers = cs;
    }
    public void add(Customer Customer)
    {
        costumers.add(Customer);
    }
    public void remove(int rId)
    {
        for (Customer i:costumers) {
            if(rId == i.getId()) {
                costumers.remove(i);
                break;
            }
        }
    }
    public Customer getCustomer(String fn , String ln)
    {
        for(Customer i:costumers)
        {
            if(fn ==i.getFirstName() &&ln==i.getLastName() )
                return i;
        }
        Customer newOne = new Customer();
        //read the new information
        add(newOne);
        return newOne;

    }
    public void addNewArtistPreference(Artist artist,String fn , String ln )
    {
        Customer customer =new Customer() ;
        for(Customer i:costumers)
        {
            if(fn ==i.getFirstName() &&ln==i.getLastName() )
            {
                customer = i ;
                break;
            }
        }
        customer.addArtistPreference(artist);
    }
    public void removeNewArtistPreference(Artist artist,String fn , String ln )
    {
        Customer customer =new Customer() ;
        for(Customer i:costumers)
        {
            if(fn ==i.getFirstName() &&ln==i.getLastName() )
            {
                customer = i ;
                break;
            }
        }
        customer.removeArtistPreference(artist);
    }
    public void addNewArtWorkPreference(ArtWork artWork,String fn , String ln )
    {
        Customer customer =new Customer() ;
        for(Customer i:costumers)
        {
            if(fn ==i.getFirstName() &&ln==i.getLastName() )
            {
                customer = i ;
                break;
            }
        }
        customer.addArtWorkPreference(artWork);
    }
    public void removeNewArtworkPreference(ArtWork artWork,String fn , String ln )
    {
        Customer customer =new Customer() ;
        for(Customer i:costumers)
        {
            if(fn ==i.getFirstName() &&ln==i.getLastName() )
            {
                customer = i ;
                break;
            }
        }
        customer.removeArtWorkPreference(artWork);
    }

    public void writeCustomersOnFolder()
    {
        try{
            File file = new File("C:\\Users\\Omar\\Desktop\\Customers");
            if(!file.exists())
                file.createNewFile();
            FileOutputStream out =new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            for (Customer i :costumers){
                oos.writeObject((Customer) i);
                oos.flush();
            }
        }catch (Exception e)
        {
            System.err.println(e);
        }
    }
    public boolean readCustomersFromFolder()
    {
        try {
            File file = new File("C:\\Users\\Omar\\Desktop\\Customers");
            if(file.exists()) {
                FileInputStream in = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(in);
                Customer customer = new Customer();

                while ((customer = (Customer) (ois.readObject())) != null) {
                    System.out.println(customer.getFirstName() + " " + customer.getLastName());
                    costumers.add(customer);
                }
            }
            else
                return false;
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
