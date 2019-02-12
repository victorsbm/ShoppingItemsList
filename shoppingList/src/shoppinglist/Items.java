/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist;

/**
 *
 * @author Owner
 */
public class Items {
    //Object information
    private int id;
    private String name;
    private float price;
    private String shopName;
    private String shopAddress;
    private String addedDate;
    private byte[] image;
    
    public Items(int iId, String iName, float iPrice, String iShopName, String iShopAddress, String iAddedDate, byte[] iImage){
        this.id = iId;
        this.name = iName;
        this.price = iPrice;
        this.shopName = iShopName;
        this.shopAddress = iShopAddress;
        this.addedDate = iAddedDate;
        this.image = iImage;
    }
    public int getID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    public float getPrice(){
        return price;
    }
    public String getShop(){
        return shopName;
    }
    
    public String getAddress(){
        return shopAddress;
    }
    public String getAddedDate(){
        return addedDate;
    }
    public byte[] getImage(){
        return image;
    }    
    
    
}

