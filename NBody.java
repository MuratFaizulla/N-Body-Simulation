public class NBody {
    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double t;
        int n = StdIn.readInt();
        double unirad = StdIn.readDouble();
        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];
        StdDraw.setXscale(-unirad, unirad);
        StdDraw.setYscale(-unirad, unirad);
        StdDraw.enableDoubleBuffering();
        StdAudio.playInBackground("2001.wav");

        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();
        }
        double G = 6.67e-11;

        for (t = 0.0; t < T; t = +dt) {
            double[] Fx = new double[n];
            double[] Fy = new double[n];

            StdDraw.picture(0, 0, "starfield.jpg");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double dx = px[j] - px[i];
                        double dy = py[j] - py[i];
                        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                        double F = G * mass[i] * mass[j] / Math.pow(r, 2);
                        Fx[i] = Fx[i] + F * dx / r;
                        Fy[i] = Fy[i] + F * dy / r;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                double ax = Fx[i] / mass[i];
                double ay = Fy[i] / mass[i];

                vx[i] = vx[i] + ax * dt;
                vy[i] = vy[i] + ay * dt;
                px[i] = px[i] + vx[i] * dt;
                py[i] = py[i] + vy[i] * dt;

                StdDraw.picture(px[i], py[i], image[i]);
            }
            StdDraw.show();
            StdDraw.pause(20);
            StdDraw.enableDoubleBuffering();
        }
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", unirad);

        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          px[i], py[i], vx[i], vy[i], mass[i], image[i]);
        }
    }
}
