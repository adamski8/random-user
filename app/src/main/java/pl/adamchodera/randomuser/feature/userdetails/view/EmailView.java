package pl.adamchodera.randomuser.feature.userdetails.view;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

import pl.adamchodera.randomuser.R;

public class EmailView extends TextView {

    private boolean isAnimating = false;

    public EmailView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextSize(final float size) {
        super.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setupViewWhenToolbarCollapsed() {

        setTextSize(getDimResourceById(R.dimen.text_size_big));

        final int leftPadding = getDimResourceById(R.dimen.activity_user_details_email_padding_left_collapsed);
        final int bottomPadding = getDimResourceById(R.dimen.toolbar_default_height) - getDimResourceById(R.dimen.margin_standard);

        setPadding(leftPadding, 0, 0, bottomPadding);

        setVisible(true);
    }

    public void setupViewWhenToolbarExpanded() {
        setTextSize(getDimResourceById(R.dimen.text_size_standard));

        final int leftPadding = getDimResourceById(R.dimen.activity_user_details_email_padding_left_expanded);
        final int bottomPadding = getDimResourceById(R.dimen.margin_medium);

        setPadding(leftPadding, 0, 0, bottomPadding);

        setVisible(true);
    }

    public void setVisible(final boolean visible) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int cx = getMeasuredWidth() / 2;
            int cy = getMeasuredHeight() / 2;

            if (visible) {
                setVisibility(VISIBLE);

                int finalRadius = Math.max(getWidth(), getHeight()) / 2;
                final Animator anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, 0, finalRadius);
                anim.start();
            } else {
                setVisibility(View.INVISIBLE);
            }
        } else {
            setVisibility(visible ? VISIBLE : INVISIBLE);
        }
    }

    private int getDimResourceById(final int resId) {
        return (int) getResources().getDimension(resId);
    }
}
