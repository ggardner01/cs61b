import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    private static double d1LonDPP =  0.000171661376953125;

    public Rasterer() {
        d1LonDPP =  0.000171661376953125;
    }

    private static int LonDPPdepth(double lr, double ul,double w) {
        double DPP = Math.abs(lr - ul)/w;
        int i = 1;
        double curr = d1LonDPP;
        while(DPP < curr ){
            curr = curr/2;
            i = i + 1;
        }
        return Math.min(i,7);
    }

 /*   private static int getString(double lrlon, double ullon, double lrlat, double ullat, int depth) {
        double dlon = (ROOT_LRLON - ROOT_ULLON)/(Math.pow(2,depth));
        double dlat = (ROOT_ULLAT - ROOT_LRLAT)/(Math.pow(2,depth));
        double ypos1 = Math.floor((ROOT_ULLAT - ullat)/dlat);
        double ypos2 = Math.floor((ROOT_ULLAT - lrlat)/dlat);
        double xpos1 = Math.floor((ullon - ROOT_ULLON)/dlon);
        double xpos2 = Math.floor((lrlon - ROOT_ULLON)/dlon);
        String[][] stringy = new String [(int)ypos2-(int)ypos1+1][(int)xpos2-(int)xpos1+1];
        for(int i = 0; i+(int)ypos1 < ypos2 + 1; i += 1){
            for(int j = 0; j + (int)xpos1 < xpos2 + 1; j += 1){
                stringy[i][j] = "d" + Integer.toString(depth) + "_x" + Integer.toString(j+ (int)xpos1) + "_y" + Integer.toString(i+(int)ypos1)+ ".png";
            }
        }
        double newlrlon = ROOT_ULLON + (xpos2+1)*dlon;
        double newullon = ROOT_ULLON + (xpos1)*dlon;
        double newlrlat = ROOT_ULLAT - (ypos2+1)*dlat;
        double newullat = ROOT_ULLAT - (ypos1)*dlat;

    }*/


    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();

        double ullat = params.get("ullat");
        double lrlat = params.get("lrlat");
        double ullon = params.get("ullon");;
        double lrlon = params.get("lrlon");
        double width = params.get("w");
        int depth = LonDPPdepth(lrlon, ullon,width);
        if(ullat > ROOT_ULLAT || lrlat < ROOT_LRLAT || lrlon > ROOT_LRLON || ullon < ROOT_ULLON){
            results.put("query_success", false);
            return results;
        }

        double dlon = (ROOT_LRLON - ROOT_ULLON)/(Math.pow(2,depth));
        double dlat = (ROOT_ULLAT - ROOT_LRLAT)/(Math.pow(2,depth));
        double ypos1 = Math.floor((ROOT_ULLAT - ullat)/dlat);
        double ypos2 = Math.floor((ROOT_ULLAT - lrlat)/dlat);
        double xpos1 = Math.floor((ullon - ROOT_ULLON)/dlon);
        double xpos2 = Math.floor((lrlon - ROOT_ULLON)/dlon);
        String[][] stringy = new String [(int)ypos2-(int)ypos1+1][(int)xpos2-(int)xpos1+1];
        for(int i = 0; i+(int)ypos1 < ypos2 + 1; i += 1){
            for(int j = 0; j + (int)xpos1 < xpos2 + 1; j += 1){
                stringy[i][j] = "d" + Integer.toString(depth) + "_x" + Integer.toString(j+ (int)xpos1) + "_y" + Integer.toString(i+(int)ypos1)+ ".png";
            }
        }
        double newlrlon = ROOT_ULLON + (xpos2+1)*dlon;
        double newullon = ROOT_ULLON + (xpos1)*dlon;
        double newlrlat = ROOT_ULLAT - (ypos2+1)*dlat;
        double newullat = ROOT_ULLAT - (ypos1)*dlat;

        results.put("render_grid", stringy);
        results.put("raster_ul_lon", newullon);
        results.put("raster_lr_lon",newlrlon );
        results.put("raster_ul_lat", newullat);
        results.put("raster_lr_lat", newlrlat);
        results.put("depth", depth);
        results.put("query_success", true);
        return results;
    }

    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;
/*
    public static void main(String[] args) {
        int p = LonDPPdepth(-122.2104604264636,-122.30410170759153,1085.0);
        System.out.print(p);
    }
 */
}
