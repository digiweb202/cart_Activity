package com.mart.cart_activity.Helper;

import android.content.Context;
import android.widget.Toast;


import com.mart.cart_activity.Adapter.CartAdapter;
import com.mart.cart_activity.domain.PopularDomain;

import java.util.ArrayList;

public class ManagmentCart {
    private Context context;
    private TinyDB tinyDB;
    private CartAdapter cartAdapter;


    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
        this.cartAdapter = cartAdapter;
    }

    public void insertFood(PopularDomain item) {
        ArrayList<PopularDomain> listpop = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            listpop.get(n).setNumberInChart(item.getNumberInChart());
        }else{
            listpop.add(item);
        }
        tinyDB.putListObject("CartList",listpop);
        displayCartItems();
        cartAdapter.notifyDataSetChanged();
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public Double getTotalFee(){
        ArrayList<PopularDomain> listItem=getListCart();
        double fee=0;
        for (int i = 0; i < listItem.size(); i++) {
            fee=fee+(listItem.get(i).getPrice()*listItem.get(i).getNumberInChart());
        }
        return fee;
    }
    public void minusNumberItem(ArrayList<PopularDomain> listItem,int position,ChangeNumberItemsListener changeNumberItemsListener){
        if(listItem.get(position).getNumberInChart()==1){
            listItem.remove(position);
        }else{
            listItem.get(position).setNumberInChart(listItem.get(position).getNumberInChart()-1);
        }
        tinyDB.putListObject("CartList",listItem);
        changeNumberItemsListener.change();

        cartAdapter.notifyDataSetChanged();
    }
    public  void plusNumberItem(ArrayList<PopularDomain> listItem,int position,ChangeNumberItemsListener changeNumberItemsListener){
        listItem.get(position).setNumberInChart(listItem.get(position).getNumberInChart()+1);
        tinyDB.putListObject("CartList",listItem);
        changeNumberItemsListener.change();
        cartAdapter.notifyDataSetChanged();
    }

    public void displayCartItems() {
        ArrayList<PopularDomain> listItem = getListCart();

        StringBuilder cartItemsStringBuilder = new StringBuilder("Cart Items:\n");

        for (int i = 0; i < listItem.size(); i++) {
            PopularDomain item = listItem.get(i);
            cartItemsStringBuilder.append(String.format("%d. %s - Quantity: %d\n",
                    i + 1, item.getTitle(), item.getNumberInChart()));
        }

        Toast.makeText(context, cartItemsStringBuilder.toString(), Toast.LENGTH_LONG).show();
    }
    public void updateQuantity(int position, int quantity) {
        ArrayList<PopularDomain> listItem = getListCart();

        if (position >= 0 && position < listItem.size()) {
            listItem.get(position).setNumberInChart(quantity);
            tinyDB.putListObject("CartList", listItem);
        }
    }
//    public void updateItemQuantity(int position, int quantity) {
//        ArrayList<PopularDomain> listItem = getListCart();
//
//        if (position >= 0 && position < listItem.size()) {
//            listItem.get(position).setNumberInChart(quantity);
//            tinyDB.putListObject("CartList", listItem);
//        }
//    }
// Implement the CartAdapterListener interface
public void onQuantityChanged(int position, int quantity) {
    // Update the quantity of the product in your product domain
    // Use the position to identify the product in your list
    // Update the quantity using the provided quantity parameter
    // Example:
    ArrayList<PopularDomain> listItem = getListCart();
    if (position >= 0 && position < listItem.size()) {
        listItem.get(position).setNumberInChart(quantity);
        tinyDB.putListObject("CartList", listItem);
    }
}

}
