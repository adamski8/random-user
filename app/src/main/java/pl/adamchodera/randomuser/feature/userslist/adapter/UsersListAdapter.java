package pl.adamchodera.randomuser.feature.userslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import io.realm.RealmResults;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.database.model.User;
import pl.adamchodera.randomuser.feature.userslist.fragment.UsersListFragment;

public class UsersListAdapter extends RecyclerView.Adapter<UserItemViewHolder> {

    private final Context context;
    private final List<User> users;
    private final UsersListFragment.OnListFragmentInteractionListener listener;

    public UsersListAdapter(final Context context, final RealmResults<User> items, final UsersListFragment
            .OnListFragmentInteractionListener listener) {
        this.context = context;
        users = items;
        this.listener = listener;
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_users_list, parent, false);
        return new UserItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserItemViewHolder holder, int position) {
        holder.user = users.get(position);

        holder.name.setText(holder.user.getFullName().toUpperCase(Locale.getDefault()));
        holder.email.setText(holder.user.getEmail());
        holder.date.setText(holder.user.getRegisteredDateFormatted());

        Picasso.with(context)
                .load(holder.user.getMediumPictureUrl())
                .centerCrop()
                .fit()
                .error(R.drawable.ic_error)
                .into(holder.photo);

        holder.root.setOnClickListener(v -> {
            if (null != listener) {
                listener.displayDetailsView(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
