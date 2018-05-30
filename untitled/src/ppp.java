public class ppp {
        public static void drawTriangle(int N) {
            int n=0;
            while (N > n) {
                int i=0;
                while (i<n){
                    System.out.print("*");
                    i=i+1;
                }
                System.out.println("*");
                n=n+1;
            }
        }
        public static int max(int[] m){
            int i=1;
            int max= m[0];
            while(i<m.length){
                if (m[i]>max){
                    max=m[i];
                i=i+1;
                }
            }
            return max;
        }
        public static void main(String[] args) {
            int[] listy = new int[]{1,4,11,42,2,56,11};
            System.out.println(max(listy));
        }
}
