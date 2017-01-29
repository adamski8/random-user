package pl.adamchodera.randomuser.feature.userslist.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmResults;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.database.DatabaseHelper;
import pl.adamchodera.randomuser.database.model.User;
import pl.adamchodera.randomuser.feature.userslist.adapter.UserItemViewHolder;
import pl.adamchodera.randomuser.feature.userslist.adapter.UsersListAdapter;

public class UsersListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private RealmResults<User> allUsers;

    public UsersListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allUsers = DatabaseHelper.getAllUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_users_list, container, false);
        setupRecyclerView((RecyclerView) view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setupRecyclerView(final RecyclerView recyclerView) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new UsersListAdapter(getContext(), allUsers, mListener));
    }

    public interface OnListFragmentInteractionListener {
        void displayDetailsView(final UserItemViewHolder viewHolder);
    }
}
