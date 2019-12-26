package manhthang.adididemo.Object;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Hoangdh on 24/09/2016.
 * edit tungda
 */
public class ImageAlbumObject {

    private String thoigiangui;
    private String diachi;
    private String thoigianchup;
    private String ghichu;
    private double kinhdo;
    private String path_thumbnail_medium;
    private double vido;
    private double acc;
    private int type;// 0 la chua gui, 1 la xoa , 2 la gui
    private int isUpload; // 0 la chua up, 1 la dang up , 2 la da up
    private int percent;
    private String path;

    public boolean isLocalFile() {

        //return LocalFile;
        return getType() == 0 || getType() == 1 || getType() == 3;
    }

    private void setLocalFile() {
        boolean localFile = true;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getIsUpload() {
        return isUpload;
    }

    public void setIsUpload() {
        this.isUpload = 1;
    }


    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public double getAcc() {
        return acc;
    }

    public void setAcc(double acc) {
        this.acc = acc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        if (type == 0 || type == 1 || type == 3) {
            setLocalFile();
        }
    }

    public void setPath_thumbnail_medium(String path_thumbnail_medium) {
        this.path_thumbnail_medium = path_thumbnail_medium;
    }

    public String getThoigiangui() {
        return thoigiangui;
    }

    public void setThoigiangui(String thoigiangui) {
        this.thoigiangui = thoigiangui;
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

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }

    public String getPath() {
        if (path == null) {
            return "";
        }
       /* if(type==2){
        return NetContext.getInstance().getBASE_URL()+path;
        }else {
            return path;
        }*/
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}


