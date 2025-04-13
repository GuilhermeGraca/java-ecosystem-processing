package tools;

public class Histogram {
    int[] hist;
    int nbins; //dimensão do histograma

    public Histogram(int data[], int nbins){
        this.nbins = nbins;
        hist = new int[nbins];
        for (int i = 0; i < data.length; i++) {
            hist[data[i]]++;
        }
    }

    public int[] getDistribution(){
        return hist;
    }

    // metodo que é acionado em caso de empate
    public int getMode(int preference){
        int maxValue = 0;
        int mode = 0;
        for(int i = 0; i < nbins; i++){
            if(hist[i] > maxValue){
                maxValue = hist[i];
                mode = i;
            }
        }

        if(hist[preference] == hist[mode]){
            return preference; //if it is a draw decides for preference
        }
        return mode;
    }

    public void display(){
        for(int i = 0; i < nbins; i++){
            System.out.println("hist[" + i + "] = " + hist[i]);
        }
        System.out.println();
    }
}
