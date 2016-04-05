package in.ds.alliv.control.utils;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import in.ds.alliv.control.*;

public class Utils 
implements Constants {
	
	public static boolean isTV(Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2 && ((UiModeManager) context
			.getSystemService(Context.UI_MODE_SERVICE))
			.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION;
    }

	public static Bitmap scaleDownBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int newWidth = width;
        int newHeight = height;

        if (maxWidth != 0 && newWidth > maxWidth) {
            newHeight = Math.round((float) maxWidth / newWidth * newHeight);
            newWidth = maxWidth;
        }

        if (maxHeight != 0 && newHeight > maxHeight) {
            newWidth = Math.round((float) maxHeight / newHeight * newWidth);
            newHeight = maxHeight;
        }

        return width != newWidth || height != newHeight ? resizeBitmap(bitmap, newWidth, newHeight) : bitmap;
    }
	
	public static Bitmap resizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
    }

	public static void circleAnimate(final View view, int cx, int cy) {
        if (view == null) return;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setVisibility(View.INVISIBLE);

                int finalRadius = Math.max(view.getWidth(), view.getHeight());
                Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
                anim.setDuration(500);
                anim.addListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationStart(Animator animation) {
							super.onAnimationStart(animation);
							view.setVisibility(View.VISIBLE);
						}
					});
                anim.start();
            }
        } catch (IllegalStateException e) {
            view.setVisibility(View.VISIBLE);
        }
    }
	
	public static void toast(String message, Context context) {
        toast(message, context, Toast.LENGTH_SHORT);
    }


    public static void toast(String message, Context context, int duration) {
        Toast.makeText(context, message, duration).show();
    }
	
	public static void saveString(String name, String value, Context context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().putString(name, value).apply();
    }


	public static String getString(String name, String defaults, Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(name, defaults);
    }
}
