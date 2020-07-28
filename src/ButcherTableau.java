
interface ButcherTableau {
    String getMethodName();
    
    // return cofficients of Butcher tableau
    double[] a();
    double[][] b();
    double[] g();
}

// Butcher tableau for classical Runge-Kutta method
class ButcherTableauRK4 implements ButcherTableau {
    @Override
    public String getMethodName() {
        return "RK4 method";
    }
    
    @Override
    public double[] a() {
        return new double[] {
            0.0,
            0.0,
            1.0/2.0,
            1.0/2.0,
            1.0
        };
    }

    @Override
    public double[][] b() {
        return new double[][] {
            {0.0},
            {0.0},
            {0.0, 1.0/2.0},
            {0.0, 0.0, 1.0/2.0},
            {0.0, 0.0, 0.0, 1.0}
        };
    }

    @Override
    public double[] g() {
        return new double[] {
            0.0, 1.0/6.0, 1.0/3.0, 1.0/3.0, 1.0/6.0
        };
    }
}

// Butcher tableau for Ralston method
class ButcherTableauRalston implements ButcherTableau {
    @Override
    public String getMethodName() {
        return "Ralston method";
    }
    
    double SQRT5 = 2.236067977499789694091736687312762;
    
    @Override
    public double[] a() {
        return new double[] {
            0.0,
            0.0,
            0.4,
            (14.0 - 3.0 * SQRT5) / 16.0,
            1.0
        };
    }

    @Override
    public double[][] b() {
        return new double[][] {
            {0.0},
            {0.0},
            {0.0, 0.4},
            {0.0, (-2889.0 + 1428.0 * SQRT5) / 1024.0, (3785.0 - 1620.0 * SQRT5) / 1024.0},
            {0.0, (-3365.0 + 2094.0 * SQRT5) / 6040.0, (-975.0 - 3046.0 * SQRT5) / 2552.0, (467040.0 + 203968.0 * SQRT5) / 240845.0}
        };
    }

    @Override
    public double[] g() {
        return new double[] {
            0.0,
            (263.0 + 24.0 * SQRT5) / 1812.0,
            (125.0 - 1000.0 * SQRT5) / 3828.0,
            1024.0 * (3346.0 + 1623.0 * SQRT5) / 5924787.0,
            (30.0 - 4.0 * SQRT5) / 123.0
        };
    }
}