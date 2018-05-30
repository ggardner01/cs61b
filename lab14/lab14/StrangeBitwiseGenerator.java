package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator{
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int p){
        this.period = p;
        this.state = 0;

    }
    public double next(){
        state = state + 1;
        int weirdState = state & (state >>> 3) % period;
        return 2*(double)weirdState/((double)period-1) - 1;
    }
}
