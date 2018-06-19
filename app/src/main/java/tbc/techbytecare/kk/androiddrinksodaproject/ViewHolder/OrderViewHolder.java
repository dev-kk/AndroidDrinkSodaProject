package tbc.techbytecare.kk.androiddrinksodaproject.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tbc.techbytecare.kk.androiddrinksodaproject.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_order_id,txt_order_price,txt_order_address,txt_order_comment,txt_order_status;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txt_order_id = itemView.findViewById(R.id.txt_order_id);
        txt_order_price = itemView.findViewById(R.id.txt_order_price);
        txt_order_address = itemView.findViewById(R.id.txt_order_address);
        txt_order_comment = itemView.findViewById(R.id.txt_order_comment);
        txt_order_status = itemView.findViewById(R.id.txt_order_status);
    }
}
