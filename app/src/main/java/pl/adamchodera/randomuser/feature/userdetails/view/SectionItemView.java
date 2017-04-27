package pl.adamchodera.randomuser.feature.userdetails.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.adamchodera.randomuser.R;

public class SectionItemView extends NestedScrollView {

    @BindView(R.id.id_view_user_details_section_item_view_title)
    TextView titleView;

    @BindView(R.id.id_view_user_details_section_item_view_value)
    TextView valueView;

    public SectionItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.view_user_details_section_item_view, this, true);

        ButterKnife.bind(this, view);
    }

    public void setTitle(final String title) {
        titleView.setText(title);
    }

    public void setValue(final String value) {
        valueView.setText(value);
    }
}
