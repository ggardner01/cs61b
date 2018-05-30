import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a shortestPath method for finding routes between two points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for your
 * satisfaction (or the autograder), upgrade your implementation by switching it to A*.
 * Your code will probably not be fast enough to pass the autograder unless you use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code, and boils
 * down to the priority you use to order your vertices.
 */
public class Router {
    /**
     * Return a List of longs representing the shortest path from the node
     * closest to a start location and the node closest to the destination
     * location.
     * @param g The graph to use.
     * @param stlon The longitude of the start location.
     * @param stlat The latitude of the start location.
     * @param destlon The longitude of the destination location.
     * @param destlat The latitude of the destination location.
     * @return A list of node id's in the order visited on the shortest path.
     */
    public static List<Long> shortestPath(GraphDB g, double stlon, double stlat,
                                          double destlon, double destlat) {
        HashMap<Long,Boolean> visited = new HashMap<>();

        PriorityQueue<Path> q = new PriorityQueue<Path>();
        long startid = g.closest(stlon, stlat);
        long goalid = g.closest(destlon, destlat);
        double goallon = g.lon(goalid); double goallat = g.lat(goalid);
        Path start = new Path(startid, goallon, goallat, g); q.add(start);


        long currid = startid; Path currp; /*Path oldp = new Path(goalid,  goallon, goallat, g);*/

        while(true){
            currp = q.poll();
            visited.put(currp.currn, true);
            /*
            while(oldp.disttrav == currp.disttrav && oldp.currn == currp.currn){
                currp = q.poll();
            } */

            currid = currp.currn;
            if(currid == goalid){
                ArrayList<Long> ret = new ArrayList<>();
                while(currp != null){
                    ret.add(currp.currn);
                    currp = currp.prev;
                }
                Collections.reverse(ret);
                return ret;}
            Iterator<Long> it = g.adjacent(currid).iterator();
            long c = 0;
            while(it.hasNext()){
                 c = it.next();
                 if(!visited.containsKey(c)){
                     Path n = currp.add(c, goallon, goallat, g);
                     q.add(n);
                 }
                 /*
                 if(currp.prev == null){
                     Path n = currp.add(c, goallon, goallat, g);
                     q.add(n);
                 } else if(currp.prev.prev == null;)
                else if(c != currp.prev.currn){
                    Path n = currp.add(c, goallon, goallat, g);
                    q.add(n);
                } */

            }
           /* oldp = currp; */

        }
    }

    private static class Path implements Comparable{

        public long currn;
        public Path prev;
        /*
        public ArrayList<Long> path;
        public double clat;
        public double clon;
        public double glat;
        public double glon; */

        public double disttrav;
        public double distgoal;
        public Path(){}
        public Path(long k, double dlon, double dlat, GraphDB g){
            currn = k; disttrav = 0; distgoal = GraphDB.distance(g.lon(currn), g.lat(currn), dlon, dlat);

            /*
            path = new ArrayList<>();
            curr = k; clat = currlat; clon = currlon; glat = goallat; glon = goallon; disttrav = 0;
            distgoal = GraphDB.distance(clon, clat, glon, glat);
            path.add(curr);
            */
        }
        public Path add(long k, double dlon, double dlat, GraphDB g){
            Path newp = new Path();
            newp.currn = k; newp.prev = this;
            newp.disttrav = this.disttrav + GraphDB.distance(g.lon(this.currn), g.lat(this.currn), g.lon(newp.currn), g.lat(newp.currn));
            newp.distgoal = GraphDB.distance(g.lon(k), g.lat(k), dlon, dlat);

            return newp;
        }

        @Override
        public int compareTo(Object O){
            Path P = (Path)O;
            int ret = -1;
            if((this.disttrav + this.distgoal) - (P.disttrav + P.distgoal) > 0){ret = 1;}
            return ret;
        }
    }

    /**
     * Create the list of directions corresponding to a route on the graph.
     * @param g The graph to use.
     * @param route The route to translate into directions. Each element
     *              corresponds to a node from the graph in the route.
     * @return A list of NavigatiionDirection objects corresponding to the input
     * route.
     */
    public static List<NavigationDirection> routeDirections(GraphDB g, List<Long> route) {
        return null; // FIXME
    }


    /**
     * Class to represent a navigation direction, which consists of 3 attributes:
     * a direction to go, a way, and the distance to travel for.
     */
    public static class NavigationDirection {

        /** Integer constants representing directions. */
        public static final int START = 0;
        public static final int STRAIGHT = 1;
        public static final int SLIGHT_LEFT = 2;
        public static final int SLIGHT_RIGHT = 3;
        public static final int RIGHT = 4;
        public static final int LEFT = 5;
        public static final int SHARP_LEFT = 6;
        public static final int SHARP_RIGHT = 7;

        /** Number of directions supported. */
        public static final int NUM_DIRECTIONS = 8;

        /** A mapping of integer values to directions.*/
        public static final String[] DIRECTIONS = new String[NUM_DIRECTIONS];

        /** Default name for an unknown way. */
        public static final String UNKNOWN_ROAD = "unknown road";
        
        /** Static initializer. */
        static {
            DIRECTIONS[START] = "Start";
            DIRECTIONS[STRAIGHT] = "Go straight";
            DIRECTIONS[SLIGHT_LEFT] = "Slight left";
            DIRECTIONS[SLIGHT_RIGHT] = "Slight right";
            DIRECTIONS[LEFT] = "Turn left";
            DIRECTIONS[RIGHT] = "Turn right";
            DIRECTIONS[SHARP_LEFT] = "Sharp left";
            DIRECTIONS[SHARP_RIGHT] = "Sharp right";
        }

        /** The direction a given NavigationDirection represents.*/
        int direction;
        /** The name of the way I represent. */
        String way;
        /** The distance along this way I represent. */
        double distance;

        /**
         * Create a default, anonymous NavigationDirection.
         */
        public NavigationDirection() {
            this.direction = STRAIGHT;
            this.way = UNKNOWN_ROAD;
            this.distance = 0.0;
        }

        public String toString() {
            return String.format("%s on %s and continue for %.3f miles.",
                    DIRECTIONS[direction], way, distance);
        }

        /**
         * Takes the string representation of a navigation direction and converts it into
         * a Navigation Direction object.
         * @param dirAsString The string representation of the NavigationDirection.
         * @return A NavigationDirection object representing the input string.
         */
        public static NavigationDirection fromString(String dirAsString) {
            String regex = "([a-zA-Z\\s]+) on ([\\w\\s]*) and continue for ([0-9\\.]+) miles\\.";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(dirAsString);
            NavigationDirection nd = new NavigationDirection();
            if (m.matches()) {
                String direction = m.group(1);
                if (direction.equals("Start")) {
                    nd.direction = NavigationDirection.START;
                } else if (direction.equals("Go straight")) {
                    nd.direction = NavigationDirection.STRAIGHT;
                } else if (direction.equals("Slight left")) {
                    nd.direction = NavigationDirection.SLIGHT_LEFT;
                } else if (direction.equals("Slight right")) {
                    nd.direction = NavigationDirection.SLIGHT_RIGHT;
                } else if (direction.equals("Turn right")) {
                    nd.direction = NavigationDirection.RIGHT;
                } else if (direction.equals("Turn left")) {
                    nd.direction = NavigationDirection.LEFT;
                } else if (direction.equals("Sharp left")) {
                    nd.direction = NavigationDirection.SHARP_LEFT;
                } else if (direction.equals("Sharp right")) {
                    nd.direction = NavigationDirection.SHARP_RIGHT;
                } else {
                    return null;
                }

                nd.way = m.group(2);
                try {
                    nd.distance = Double.parseDouble(m.group(3));
                } catch (NumberFormatException e) {
                    return null;
                }
                return nd;
            } else {
                // not a valid nd
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof NavigationDirection) {
                return direction == ((NavigationDirection) o).direction
                    && way.equals(((NavigationDirection) o).way)
                    && distance == ((NavigationDirection) o).distance;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction, way, distance);
        }
    }
}
