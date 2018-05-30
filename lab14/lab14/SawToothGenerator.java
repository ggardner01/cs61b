package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator{
    private int period;
    private int state;

    public SawToothGenerator(int p){
        this.period = p;
        this.state = 0;

    }
    public double next(){
        double n = 2*(double)state/((double)period-1) - 1;
        state = (state + 1) % period;
        return n;
    }
}
