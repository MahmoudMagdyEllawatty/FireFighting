package com.app.firefighter.helper;

import android.content.Context;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class Utils {
    private Utils() {

    }

    /**
     * Gets timestamp in millis and converts it to HH:mm (e.g. 16:44).
     */
    public static String formatTime(long timeInMillis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return dateFormat.format(timeInMillis);
    }

    public static String formatDate(long timeInMillis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(timeInMillis);
    }

    /**
     * Crops image into a circle that fits within the ImageView.
     */
    public static void displayRoundImageFromUrl(final Context context, final String url, final CircleImageView imageView) {
        Picasso.get()
                .load(url)
                .into(imageView);
    }


    /**
     * Crops image into a circle that fits within the ImageView.
     */
    public static void displayRoundImageFromUrl(final Context context, int image, final CircleImageView imageView) {
        Picasso.get()
                .load(image)
                .into(imageView);
    }
}
