package pl.adamchodera.randomuser.feature.userslist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.database.model.User;

public class UserItemViewHolder extends RecyclerView.ViewHolder {

    final FrameLayout root;
    final TextView name;
    final TextView email;
    final TextView date;
    final ImageView photo;
    User user;

    public UserItemViewHolder(View view) {
        super(view);
        root = (FrameLayout) view;
        name = (TextView) view.findViewById(R.id.id_item_user_name);
        email = (TextView) view.findViewById(R.id.id_item_user_email);
        date = (TextView) view.findViewById(R.id.id_item_user_registration_date);
        photo = (ImageView) view.findViewById(R.id.id_item_user_photo);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + email.getText() + "'";
    }

    public User getUser() {
        return user;
    }

    public ImageView getPhotoView() {
        return photo;
    }

    public TextView getEmailView() {
        return email;
    }

    public TextView getNameView() {
        return name;
    }
}