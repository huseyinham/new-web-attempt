package smiteTroll.classes;

public class Relic {

    private String relicName;
    private String relicImage;

    public Relic(String relicName, String relicImage){
        this.relicName = relicName;
        this.relicImage = relicImage;
    }

    public String getRelicName(){
        return relicName;
    }

    public String getRelicImage(){
        return relicImage;
    }


}
