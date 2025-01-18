public class PresetColor{
    private String name;
    private int rValue;
    private int gValue;
    private int bValue;

    public PresetColor(){
        name = "Default PresetColor";
        rValue = 0;
        gValue = 0;
        bValue = 0;
    }

    public PresetColor(String n, int r, int g, int b){
        name = n;
        rValue = r;
        gValue = g;
        bValue = b;
    }

    public PresetColor(PresetColor rhs){
        name = rhs.name;
        rValue = rhs.rValue;
        gValue = rhs.gValue;
        bValue = rhs.bValue;
    }

    public String getName(){
        return name;
    }

    public int getRValue(){
        return rValue;
    }

    public int getGValue(){
        return gValue;
    }
    public int getBValue(){
        return bValue;
    }

    public void setName(String n){
        name = n;
    }
    
    public void setRValue(int r){
        rValue = r;
    }

    public void setGValue(int g){
        gValue = g;
    }

    public void setBValue(int b){
        bValue = b;
    }
}