package Backend;

public class Elements {
    private String comp_name;
    private String cpu_name;
    private String hdd_name;
    private Integer score;

    public Elements(String comp_name, String cpu_name, String hdd_name, Integer score) {
        this.comp_name = comp_name;
        this.cpu_name = cpu_name;
        this.hdd_name = hdd_name;
        this.score = score;
    }

    public String getComp_name() {
        return comp_name;
    }

    public String getCpu_name() {
        return cpu_name;
    }

    public String getHdd_name() {
        return hdd_name;
    }

    public Integer getScore() {
        return score;
    }
}
