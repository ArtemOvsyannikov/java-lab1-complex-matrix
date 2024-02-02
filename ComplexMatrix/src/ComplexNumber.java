public class ComplexNumber {
    private double rl, img;

    public ComplexNumber() {
        rl = 0;
        img = 0;
    }

    public ComplexNumber(double rl, double img) {
        this.rl = rl;
        this.img = img;
    }

    public ComplexNumber(ComplexNumber rhs) {
        rl = rhs.rl;
        img = rhs.img;
    }

    public double getReal() { return rl; }
    public double getImaginary() { return img; }
    public void setReal(double rl) { this.rl = rl; }
    public void setImaginary(double img) { this.img = img; }
    
    public double getModule() { return rl * rl + img * img; }
    public double getPhase() { return Math.atan2(img, rl); }

    public ComplexNumber add(ComplexNumber rhs) {
        return new ComplexNumber(rl + rhs.rl, img + rhs.img);
    }

    public ComplexNumber subtract(ComplexNumber rhs) {
        return new ComplexNumber(rl - rhs.rl, img - rhs.img);
    }

    public ComplexNumber multiply(ComplexNumber rhs) {
        return new ComplexNumber(rl * rhs.rl - img * rhs.img, rl * rhs.img + img * rhs.rl);
    }

    @Override
    public String toString() {
        return (rl != 0 ? rl : "") + (img > 0 && rl != 0 ? "+" : "") + (img == 0 ? "" : (img + "i"));
    }
}
