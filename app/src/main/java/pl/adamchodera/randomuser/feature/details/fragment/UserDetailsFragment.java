package pl.adamchodera.randomuser.feature.details.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.common.Commons;
import pl.adamchodera.randomuser.database.DatabaseHelper;
import pl.adamchodera.randomuser.database.model.User;

public class UserDetailsFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    @Bind(R.id.id_activity_user_details_image)
    public ImageView imageView;

    @Bind(R.id.id_activity_user_details_email)
    public TextView emailView;

    @Bind(R.id.id_activity_user_details_app_bar)
    public AppBarLayout appBarLayout;

    @Bind(R.id.id_activity_user_details_toolbar_layout)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.id_activity_user_details_toolbar)
    public Toolbar toolbar;

    private User user;

    public UserDetailsFragment() {
    }

    public static UserDetailsFragment newInstance(String userEmail) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(Commons.IntentKeys.USER_EMAIL, userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null) {
            return;
        }

        final String userEmail = getArguments().getString(Commons.IntentKeys.USER_EMAIL);
        user = DatabaseHelper.getUserByEmail(userEmail);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, view);

        setupViews();

        return view;
    }

    @OnClick(R.id.id_activity_user_details_fab_button)
    public void composeEmailToUser() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"}); // TODO change
        i.putExtra(Intent.EXTRA_SUBJECT, "This is only a Test.");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupViews() {
        setupToolbar();
        emailView.setText(user.getEmail());
        setupUserImage();
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String title = user.getFullName().toUpperCase();
        setCollapsingToolbarLayoutTitle(title);
    }

    private void setCollapsingToolbarLayoutTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
    }

    private void setupUserImage() {
        Picasso.with(getContext())
                .load(user.getLargePictureUrl())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_clock)
                .into(imageView);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            // expanded
            emailView.setTextSize(16);
            emailView.setVisibility(View.VISIBLE);
            emailView.setPadding((int) (getResources().getDimension(R.dimen.id_activity_user_details_email_padding_left_expanded)),
                    0, 0, (int) getResources().getDimension(R.dimen.margin_medium));
        } else if (appBarLayout.getTotalScrollRange() == Math.abs(verticalOffset)) {
            // collapsed
            emailView.setVisibility(View.VISIBLE);
            emailView.setTextSize(20);
            emailView.setPadding((int) (getResources().getDimension(R.dimen.id_activity_user_details_email_padding_left_collapsed)), 0, 0, (int)
                    (getResources().getDimension(R.dimen.toolbar_height) -
                            getResources().getDimension(R.dimen.margin_medium)));
        } else {
            emailView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        appBarLayout.removeOnOffsetChangedListener(this);
    }
}
