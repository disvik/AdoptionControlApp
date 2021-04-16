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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder> {

    public interface DeleteItemListener {
        void onDeleteItem(int position);
    }

    private final LayoutInflater inflater;
    private final List<Item> list;
    private DeleteItemListener listener;


    private static final int EMPTY_LIST_TYPE = 0;
    private static final int NON_EMPTY_LIST_TYPE = 1;

    public ContactsAdapter(Context context, List<Item> users, DeleteItemListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.list = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == EMPTY_LIST_TYPE) {
            view = inflater.inflate(R.layout.list_no_items, parent, false);
            view.setTag(EMPTY_LIST_TYPE);
        } else {
            view = inflater.inflate(R.layout.list_contact, parent, false);
            view.setTag(NON_EMPTY_LIST_TYPE);
        }

        return new ContactHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {

        if (getItemViewType(position) == EMPTY_LIST_TYPE)
            return;

        Item contact = list.get(position);

        holder.name.setText(contact.getName());
        holder.email.setText(contact.getLimit());
        holder.phone.setText(contact.getAmount());
    }

    @Override
    public int getItemCount() {
        return Math.max(list.size(), 1);
    }

    @Override
    public int getItemViewType(int position) {
        return list.isEmpty() ? EMPTY_LIST_TYPE : NON_EMPTY_LIST_TYPE;
    }


    static class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView email;
        TextView phone;

        ImageButton deleteButton;
        DeleteItemListener listener;

        public ContactHolder(@NonNull View itemView, DeleteItemListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);

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