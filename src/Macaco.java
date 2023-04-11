public class Macaco {
    private int mandaPar, mandaImpar, cocoPar, cocoImpar;
    
    public Macaco(int mandaPar, int mandaImpar, int cocoPar, int cocoImpar)
    {
        this.mandaPar = mandaPar;
        this.mandaImpar = mandaImpar;
        this.cocoPar = cocoPar;
        this.cocoImpar = cocoImpar;
    }
    public int getMandaPar(){return mandaPar;}
    public int getMandaImpar(){return mandaImpar;}    
    public int getCocoPar(){return cocoPar;}
    public int getCocoImpar(){return cocoImpar;}

    public void setCocoPar(int numCocoPar){
        cocoPar = numCocoPar;
    }

    public void setCocoImpar(int numCocoImpar){
        cocoImpar = numCocoImpar;
    }
    public int getTotal(){
        return cocoPar + cocoImpar;
    }
}
