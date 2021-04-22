package ua.opu.contactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ExchangeHolder> {

    public interface DeleteItemListener {
        void onDeleteItem(int position);
    }

    private final LayoutInflater inflater;
    private final List<Item> list;
    private DeleteItemListener listener;


    private static final int EMPTY_LIST_TYPE = 0;
    private static final int NON_EMPTY_LIST_TYPE = 1;

    public ReportAdapter(Context context, List<Item> users, DeleteItemListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.list = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExchangeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == EMPTY_LIST_TYPE) {
            view = inflater.inflate(R.layout.list_no_items, parent, false);
            view.setTag(EMPTY_LIST_TYPE);
        } else {
            view = inflater.inflate(R.layout.list_reports, parent, false);
            view.setTag(NON_EMPTY_LIST_TYPE);
        }

        return new ExchangeHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull ExchangeHolder holder, int position) {

        if (getItemViewType(position) == EMPTY_LIST_TYPE)
            return;

        Item item = list.get(position);

        holder.data.setText(item.getData());
        holder.weight.setText(item.getWeight());
        holder.height.setText(item.getHeight());
    }

    @Override
    public int getItemCount() {
        return Math.max(list.size(), 1);
    }

    @Override
    public int getItemViewType(int position) {
        return list.isEmpty() ? EMPTY_LIST_TYPE : NON_EMPTY_LIST_TYPE;
    }


    static class ExchangeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView data;
        TextView weight;
        TextView height;

        ImageButton deleteButton;
        DeleteItemListener listener;

        public ExchangeHolder(@NonNull View itemView, DeleteItemListener listener) {
            super(itemView);

            data = itemView.findViewById(R.id.name);
            weight = itemView.findViewById(R.id.limit);
            height = itemView.findViewById(R.id.amount);

            deleteButton = itemView.findViewById(R.id.clearButton);
            this.listener = listener;

            if ((int) itemView.getTag() == NON_EMPTY_LIST_TYPE) {
                deleteButton.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            listener.onDeleteItem(getAdapterPosition());
        }
    }
}