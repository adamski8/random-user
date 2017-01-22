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
import pl.adamchodera.randomuser.fragments.dummy.DummyContent.DummyItem;
import pl.adamchodera.randomuser.network.pojo.User;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private Context context;
    private final List<User> users;
    private final UsersListFragment.OnListFragmentInteractionListener mListener;

    public UsersListAdapter(final Context context, final RealmResults<User> items, final UsersListFragment
            .OnListFragmentInteractionListener listener) {
        this.context = context;
        users = items;
        mListener = listener;
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
        holder.name.setText(holder.user.getName());
        holder.email.setText(holder.user.getEmail());

        Picasso.with(context)
                .load(holder.user.getPhotoUrl())
//                .resize(0, (int) context.getResources().getDimension(R.dimen.item_master_image_height))
                .into(holder.photo);

        holder.root.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View root;
        public final TextView name;
        public final TextView email;
        private final ImageView photo;
        public User user;

        public ViewHolder(View view) {
            super(view);
            root = view;
            name = (TextView) view.findViewById(R.id.id_item_user_name);
            email = (TextView) view.findViewById(R.id.id_item_user_email);
            photo = (ImageView) view.findViewById(R.id.id_item_user_photo);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + email.getText() + "'";
        }
    }
}
