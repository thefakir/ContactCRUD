package com.example.fidelbarcaya.contacts;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private ArrayList<Contact> dataset;
    private Context context;
    private static  final String TAG = "CONTACT";
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    FireBaseService fireBaseService;
    public ContactListAdapter(Context context){

        this.context = context;
        dataset = new ArrayList<>();
        fireBaseService = new FireBaseService();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Contact contact = dataset.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.emailTextView.setText(contact.getEmail());

    holder.editContact.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Dialog contactForm = new Dialog(context);
            contactForm.buildForm("EDIT",contact);
        }
    });
    holder.deleteContact.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          fireBaseService.deleteContact(contact);
        }
    });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public void addContactList(List<Contact> contacts){

        dataset.removeAll(dataset);
        dataset.addAll(contacts);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameTextView;
        private TextView emailTextView;
        private ImageView editContact;
        private ImageView deleteContact;
        private final Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            emailTextView = (TextView) itemView.findViewById(R.id.email);
            editContact = (ImageView) itemView.findViewById(R.id.edit_contact);
            deleteContact = (ImageView) itemView.findViewById(R.id.delete_contact);
            context = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //TO DO
            /*try {
                Toast.makeText(v.getContext(), "position = " + getPosition(), Toast.LENGTH_SHORT).show();
                Contact contact = dataset.get(getPosition());
                Intent intent = new Intent(v.getContext(), ContactViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, contact);
                context.startActivity(intent);
            } catch(Exception e)
            {
                String m = e.getMessage();
                e.printStackTrace();

            }
*/
        }
    }

}

