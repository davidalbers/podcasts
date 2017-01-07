package Data;

import java.util.Date;

/**
 * Created by DavidAlbers on 11/21/2016.
 */

public class Podcast {

    public Podcast(String name, String title, String desc, Date date) {
        this.name = name;
        this.title = title;
        this.desc = desc;
        this.date = date;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String name;
    private String title;
    private String desc;
    private Date date;
}
