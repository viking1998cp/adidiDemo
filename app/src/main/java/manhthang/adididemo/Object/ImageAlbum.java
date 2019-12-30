package manhthang.adididemo.Object;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Hoangdh on 24/09/2016.
 * edit tungda
 */
public class ImageAlbum implements Serializable{


    private String thoigianchup;
    private String ghichu;
    private String path;

    public ImageAlbum() {
    }

    public String getThoigianchup() {
        return thoigianchup;
    }

    public void setThoigianchup(String thoigianchup) {
        this.thoigianchup = thoigianchup;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


