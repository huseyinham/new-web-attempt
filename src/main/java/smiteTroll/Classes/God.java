package smiteTroll.classes;

public class God {

    private String godName;
    private String godType;
    private String godImage;

    public God(String godName, String godType, String godImage) {
        this.godName = godName;
        this.godType = godType;
        this.godImage = godImage;
    }

    public String getGodName() {
        return godName;
    }

    public String getGodType() {
        return godType;
    }

    public String getGodImage(){
        return godImage;
    }

}
