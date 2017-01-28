package pl.adamchodera.randomuser.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import pl.adamchodera.randomuser.Commons;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.network.pojo.User;

public class UserDetailsActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

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

    @OnClick(R.id.id_activity_user_details_fab_button)
    public void composeEmailToUser() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"}); // TODO change
        i.putExtra(Intent.EXTRA_SUBJECT, "This is only a Test.");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(UserDetailsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        final String userEmail = getIntent().getExtras().getString(Commons.IntentKeys.USER_EMAIL);

        Realm realm = Realm.getDefaultInstance();

        final RealmQuery<User> query = realm.where(User.class);
        query.contains("email", userEmail);
        user = query.findFirst();

        setupToolbar();

        emailView.setText(user.getEmail());

        Picasso.with(this)
                .load(user.getPhotoUrl())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageView.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        imageView.setBackgroundResource(R.drawable.ic_error);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        imageView.setBackgroundResource(R.drawable.ic_clock);
                    }

                });
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

    private void setupToolbar() {
        String title = user.getName().toUpperCase();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setCollapsingToolbarLayoutTitle(title);
    }

    private void setCollapsingToolbarLayoutTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
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
}
