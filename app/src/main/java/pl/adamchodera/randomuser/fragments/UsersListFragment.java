package pl.adamchodera.randomuser.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.network.pojo.User;
import pl.adamchodera.randomuser.views.adapters.UsersListAdapter;

public class UsersListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private RealmResults<User> loadedUsers;

    public UsersListFragment() {
    }

    public static UsersListFragment newInstance() {
        UsersListFragment fragment = new UsersListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<User> query = realm.where(User.class);
        loadedUsers = query.findAll();
    }

    @Override
    public void onStop() {
        super.onStop();
        Realm.getDefaultInstance().close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);


        RecyclerView recyclerView = (RecyclerView) view;

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new UsersListAdapter(getContext(), loadedUsers, mListener));

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

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(User item);
    }
}
