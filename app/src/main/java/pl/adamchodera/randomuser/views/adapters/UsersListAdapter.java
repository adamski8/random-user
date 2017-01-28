package pl.adamchodera.randomuser.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.RealmResults;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.fragments.UsersListFragment;
import pl.adamchodera.randomuser.network.pojo.User;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private Context context;
    private final List<User> users;
    private final UsersListFragment.OnListFragmentInteractionListener listener;

    public UsersListAdapter(final Context context, final RealmResults<User> items, final UsersListFragment
            .OnListFragmentInteractionListener listener) {
        this.context = context;
        users = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_users_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.user = users.get(position);
        holder.name.setText(holder.user.getName().toUpperCase());
        holder.email.setText(holder.user.getEmail());
        holder.date.setText(holder.user.getRegistered().substring(0, holder.user.getRegistered().length() - 3));

        Picasso.with(context)
                .load(holder.user.getPhotoUrl())
                .centerCrop()
                .fit()
                .error(R.drawable.ic_error)
                .into(holder.photo);

        holder.root.setOnClickListener(v -> {
            if (null != listener) {
                listener.onListFragmentInteraction(holder.user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View root;
        final TextView name;
        final TextView email;
        final TextView date;
        final ImageView photo;
        User user;

        public ViewHolder(View view) {
            super(view);
            root = view;
            name = (TextView) view.findViewById(R.id.id_item_user_name);
            email = (TextView) view.findViewById(R.id.id_item_user_email);
            date = (TextView) view.findViewById(R.id.id_item_user_registration_date);
            photo = (ImageView) view.findViewById(R.id.id_item_user_photo);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + email.getText() + "'";
        }
    }
}
