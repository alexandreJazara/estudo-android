package br.com.jasa.netiflixremaker.model;
/*esta parte é o trecho responsável pela leitura da capa do vídeo/filme*/
public class Movie {
    //private int coverUrl;
    private int id;
    private String coverUrl;
    private String title;
    private String desc;
    private String cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    //public int getCoverUrl() { return coverUrl; }
    public String getCoverUrl() {
        return coverUrl;
    }

    //public void setCoverUrl(int coverUrl) {this.coverUrl = coverUrl; }
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
