package ru.nikky.aviasales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<Product> productList;
    private OnNoteListener mOnNoteListener;



    public ProductAdapter(Context mCtx, List<Product> productList, OnNoteListener onNoteListener) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.airportsTextView.setText(product.getAirports());
        holder.timeTextView.setText(product.getTime());
        holder.airport1TextView.setText(product.getAirport1());
        holder.airport2TextView.setText(product.getAirport2());
        holder.airport3TextView.setText(product.getAirport3());
        holder.transfer1TextView.setText(product.getTransfer1());
        holder.transfer2TextView.setText(product.getTransfer2());
        holder.transfer3TextView.setText(product.getTransfer3());
        holder.transfersTextView.setText(product.getTransfers());
        holder.priceTextView.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView airportsTextView, timeTextView, airport1TextView, airport2TextView, airport3TextView, transfer1TextView, transfer2TextView, transfer3TextView, transfersTextView, priceTextView;
        OnNoteListener onNoteListener;

        public ProductViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            airportsTextView = itemView.findViewById(R.id.airportsTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            airport1TextView = itemView.findViewById(R.id.airportTextView1);
            airport2TextView = itemView.findViewById(R.id.airportTextView2);
            airport3TextView = itemView.findViewById(R.id.airportTextView3);
            transfer1TextView = itemView.findViewById(R.id.transferDurationTextView1);
            transfer2TextView = itemView.findViewById(R.id.transferDurationTextView2);
            transfer3TextView = itemView.findViewById(R.id.transferDurationTextView3);
            transfersTextView = itemView.findViewById(R.id.transfersTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);

            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
