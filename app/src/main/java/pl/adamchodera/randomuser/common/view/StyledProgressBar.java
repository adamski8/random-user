package pl.adamchodera.randomuser.common.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import pl.adamchodera.randomuser.R;

public class StyledProgressBar extends ProgressBar {

    public StyledProgressBar(final Context context) {
        super(context);

        setIntermediateStyle();
    }

    public StyledProgressBar(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        setIntermediateStyle();
    }

    public StyledProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
        setIntermediateStyle();
    }

    private void setIntermediateStyle() {
        setIndeterminate(true);
        getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimaryDark),
                android.graphics.PorterDuff.Mode.MULTIPLY);
    }
}
