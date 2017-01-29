package pl.adamchodera.randomuser.feature.userdetails.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.adamchodera.randomuser.R;
import pl.adamchodera.randomuser.database.model.User;

public class SectionView extends NestedScrollView {

    @Bind(R.id.id_view_user_details_section_view_phone)
    SectionItemView phoneView;

    @Bind(R.id.id_view_user_details_section_view_location)
    SectionItemView locationView;

    @Bind(R.id.id_view_user_details_section_view_registration_date)
    SectionItemView registrationDateView;

    public SectionView(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.view_user_details_section_view, this, true);

        ButterKnife.bind(this, view);
    }

    public void setupView(final User user) {
        phoneView.setTitle(getResources().getString(R.string.user_details_title_phone));
        phoneView.setValue(user.getAllPhoneNumbersFormatted());

        locationView.setTitle(getResources().getString(R.string.user_details_title_location));
        locationView.setValue(user.getLocationFormatted());

        registrationDateView.setTitle(getResources().getString(R.string.user_details_title_registration_date));
        registrationDateView.setValue(user.getRegisteredDateFormatted());
    }
}
