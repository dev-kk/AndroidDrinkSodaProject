package tbc.techbytecare.kk.androiddrinksodaproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import tbc.techbytecare.kk.androiddrinksodaproject.Common.Common;
import tbc.techbytecare.kk.androiddrinksodaproject.Database.ModelDB.Cart;
import tbc.techbytecare.kk.androiddrinksodaproject.R;
import tbc.techbytecare.kk.androiddrinksodaproject.ViewHolder.CartViewHolder;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>  {

    Context context;
    List<Cart> cartList;

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View iteView = LayoutInflater.from(context)
                .inflate(R.layout.cart_item_layout,parent,false);

        return new CartViewHolder(iteView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, final int position) {

        Picasso.with(context)
                .load(cartList.get(position).link)
                .into(holder.img_product);

        holder.txt_amount.setNumber(String.valueOf(cartList.get(position).amount));

        holder.txt_price.setText(new StringBuilder("$ ").append(cartList.get(position).price));
        holder.txt_product_name.setText(cartList.get(position).name);
        holder.txt_sugar_ice.setText(new StringBuilder("Sugar : ")
            .append(cartList.get(position).sugar)
            .append("%")
            .append("\n")
            .append("Ice : ")
            .append(cartList.get(position).ice)
            .append("%").toString());

        holder.txt_amount.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Cart cart = cartList.get(position);
                cart.amount = newValue;

                Common.cartRepository.updateCart(cart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}