package pl.adamchodera.randomuser.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class CustomRingProgressBar extends RingProgressBar {
    public CustomRingProgressBar(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public void smoothlyFillProgressBar(final int animationDuration, final int countDownInterval) {
        new CountDownTimer(animationDuration, countDownInterval) {

            public void onTick(long millisUntilFinished) {
                final long timeAlreadySpent = animationDuration + countDownInterval - millisUntilFinished;
                final int percentageOfCompleteness = (int) ((timeAlreadySpent * 100) / animationDuration);

                if (percentageOfCompleteness >= 98) {
                    setProgress(100);
                    cancel();
                } else {
                    setProgress(percentageOfCompleteness);
                }
            }

            public void onFinish() {
            }
        }.start();
    }
}
