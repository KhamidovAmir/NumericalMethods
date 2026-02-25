public class DualNumber {
    public final double real;   // обычная часть
    public final double dual;   // коэффициент при ε

    public DualNumber(double real, double dual) {
        this.real = real;
        this.dual = dual;
    }

    // сложение
    public DualNumber add(DualNumber other) {
        return new DualNumber(
                this.real + other.real,
                this.dual + other.dual
        );
    }

    // вычитание
    public DualNumber sub(DualNumber other) {
        return new DualNumber(
                this.real - other.real,
                this.dual - other.dual
        );
    }

    // умножение
    public DualNumber mul(DualNumber other) {
        return new DualNumber(
                this.real * other.real,
                this.real * other.dual + this.dual * other.real
        );
    }

    // деление
    public DualNumber div(DualNumber other) {
        double r = this.real / other.real;
        double d = (this.dual * other.real - this.real * other.dual)
                / (other.real * other.real);
        return new DualNumber(r, d);
    }

    // sin
    public static DualNumber sin(DualNumber x) {
        return new DualNumber(
                Math.sin(x.real),
                Math.cos(x.real) * x.dual
        );
    }

    // cos
    public static DualNumber cos(DualNumber x) {
        return new DualNumber(
                Math.cos(x.real),
                -Math.sin(x.real) * x.dual
        );
    }

    // exp
    public static DualNumber exp(DualNumber x) {
        double e = Math.exp(x.real);
        return new DualNumber(e, e * x.dual);
    }

    // степень
    public static DualNumber pow(DualNumber x, double power) {
        double r = Math.pow(x.real, power);
        double d = power * Math.pow(x.real, power - 1) * x.dual;
        return new DualNumber(r, d);
    }

    @Override
    public String toString() {
        return real + " + " + dual + "ε";
    }
}
