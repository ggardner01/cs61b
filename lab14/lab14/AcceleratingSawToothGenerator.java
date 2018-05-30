package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double factor;

    public AcceleratingSawToothGenerator(int p, double f){
        this.period = p;
        this.state = 0;
        this.factor = f;

    }
    public double next(){
        double n = 2*(double)state/((double)period-1) - 1;;
        state = (state + 1) % period;
        if(state == 0){period = (int)(((double)period)*factor);}
        return n;
    }
}
