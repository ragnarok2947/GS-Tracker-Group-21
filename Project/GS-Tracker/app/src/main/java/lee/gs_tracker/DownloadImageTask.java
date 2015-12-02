package lee.gs_tracker;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.InputStream;
/**
 * Created by Lee on 11/22/2015.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {   //class for generating the image from a URL
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap Image = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();   //get URL and decode it
            Image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();;
            //Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return Image;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}