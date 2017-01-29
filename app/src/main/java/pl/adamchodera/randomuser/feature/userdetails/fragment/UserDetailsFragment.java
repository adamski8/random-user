package pl.adamchodera.randomuser.feature.userdetails.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.common.Commons;
import pl.adamchodera.randomuser.database.DatabaseHelper;
import pl.adamchodera.randomuser.database.model.User;
import pl.adamchodera.randomuser.feature.userdetails.view.EmailView;
import pl.adamchodera.randomuser.feature.userdetails.view.SectionView;

public class UserDetailsFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    @Bind(R.id.id_fragment_user_details_root)
    public ViewGroup sceneRoot;

    @Bind(R.id.id_fragment_user_details_image)
    public ImageView imageView;

    @Bind(R.id.id_fragment_user_details_email)
    public EmailView emailView;

    @Bind(R.id.id_fragment_user_details_app_bar)
    public AppBarLayout appBarLayout;

    @Bind(R.id.id_fragment_user_details_toolbar_layout)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.id_fragment_user_details_toolbar)
    public Toolbar toolbar;

    @Bind(R.id.id_fragment_user_details_content)
    public SectionView sectionView;

    private User user;

    public UserDetailsFragment() {
    }

    public static UserDetailsFragment newInstance(String userEmail) {
        final UserDetailsFragment fragment = new UserDetailsFragment();
        final Bundle args = new Bundle();
        args.putSerializable(Commons.IntentKeys.USER_EMAIL, userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() == null) {
            return;
        }

        final String userEmail = getArguments().getString(Commons.IntentKeys.USER_EMAIL);
        user = DatabaseHelper.getUserByEmail(userEmail);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, view);

        setupViews();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.id_fragment_user_details_fab_button)
    public void composeEmailToUser() {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "This is only a Test.");
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupViews() {
        if (user == null) {
            return;
        }

        if (Realm.getDefaultInstance().isClosed()) {
            Realm.getDefaultInstance();
        }
        setupToolbar();
        setupUserImage();
        emailView.setText(user.getEmail());
        sectionView.setupView(user);
    }

    @SuppressWarnings("ConstantConditions")
    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(null);

        final String title = user.getFullName().toUpperCase(Locale.getDefault());
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
                .into(imageView);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        final boolean isToolbarExpanded = verticalOffset == 0;
        final boolean isToolbarCollapsed = appBarLayout.getTotalScrollRange() == Math.abs(verticalOffset);
        if (isToolbarExpanded) {
            emailView.setupViewWhenToolbarExpanded();
        } else if (isToolbarCollapsed) {
            emailView.setupViewWhenToolbarCollapsed();
        } else {
            // between expanded and collapsed
            emailView.setVisible(false);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        DatabaseHelper.closeDatabase();
    }
}
