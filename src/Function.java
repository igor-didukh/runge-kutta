interface Function {
    String getName();
    String getLetter();
    double getInitValue();
    double f(double x);
}

class f1 implements Function {
    @Override
    public String getName() {
        return "a * sqrt(b*x) + c";
    }
    
    @Override
    public double f(double x) {
        return Math.sqrt(x);
    }

    @Override
    public String getLetter() {
        return "sqrt";
    }

    @Override
    public double getInitValue() {
        return 0;
    }
}

class f2 implements Function {
    @Override
    public String getName() {
        return "a * cos(b*x) + c";
    }
    
    @Override
    public double f(double x) {
        return Math.cos(x);
    }

    @Override
    public String getLetter() {
        return "cos";
    }

    @Override
    public double getInitValue() {
        return 0;
    }
}

class f3 implements Function {
    @Override
    public String getName() {
        return "a * exp(b*x) + c";
    }
    
    @Override
    public double f(double x) {
        return Math.exp(x);
    }

    @Override
    public String getLetter() {
        return "exp";
    }

    @Override
    public double getInitValue() {
        return 0;
    }
}

class f4 implements Function {
    @Override
    public String getName() {
        return "a * log(b*x) + c";
    }
    
    @Override
    public double f(double x) {
        return Math.log(x);
    }

    @Override
    public String getLetter() {
        return "log";
    }

    @Override
    public double getInitValue() {
        return 0;
    }
}