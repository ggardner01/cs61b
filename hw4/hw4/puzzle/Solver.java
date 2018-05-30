package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class Solver{
    private int size;
    private SearchNode fin;


    private class SearchNode implements Comparable{
        public WorldState state;
        public int moves;
        public SearchNode previous;
        public int disttogoal;

        SearchNode(WorldState s, int m, SearchNode p){
            state = s;
            moves = m;
            previous = p;
            disttogoal = s.estimatedDistanceToGoal();
        }
        public int compareTo(Object T){
            SearchNode other = (SearchNode)T;
            return (this.moves + this.disttogoal) - (other.moves + other.disttogoal);
        }
    }


    public Solver(WorldState initial) {
        MinPQ heap = new MinPQ();
        heap.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode curr = (SearchNode) heap.delMin();
            if (curr.state.isGoal()) {
                size = curr.moves;
                fin = curr;
                return;
            }
            Iterator neighs = curr.state.neighbors().iterator();
            while (neighs.hasNext()) {
                WorldState ne = (WorldState) neighs.next();
                if (curr.previous == null || ne != curr.previous.state) {
                    heap.insert(new SearchNode(ne, curr.moves + 1, curr));
                }

            }
        }
    }

    public int moves(){return size;}
    public Iterable<WorldState> solution(){
        return new it(fin);
    }


    private class it implements Iterable<WorldState>{
        SearchNode curr;

        public it (SearchNode k){
            this.curr = k;
        }

        public Iterator<WorldState> iterator(){
            return new myiterator();
        }

        public class myiterator implements Iterator<WorldState>{
            public int i;
            public ArrayList<WorldState> states;

            public myiterator() {
                SearchNode c = curr;
                states = new ArrayList<WorldState>();
                while (c.previous != null){
                    states.add(c.state);
                    c = c.previous;
                }
                Collections.reverse(states);
                i = 0;
            }

            @Override
            public boolean hasNext() {
                return i + 1 < states.size();
            }

            @Override
            public WorldState next() {
                if(hasNext()){
                    i += 1;
                    return states.get(i);
                }
                return null;
            }
        }


    }
}
