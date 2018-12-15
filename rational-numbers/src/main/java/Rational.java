class Rational {
    int num, denom;

    Rational(int numerator, int denominator) {
        num = numerator;
        denom = denominator;
        simplify();
    }

    public Rational add(Rational other){
        return new Rational(
                this.num * other.denom + this.denom * other.num,
                this.denom * other.denom);
    }

    public Rational subtract(Rational other){
        return new Rational(
                this.num * other.denom - other.num * this.denom,
                this.denom * other.denom);
    }

    public Rational multiply(Rational other){
        return new Rational(
                this.num * other.num,
                this.denom * other.denom);
    }

    public Rational divide(Rational other){
        return new Rational(
                this.num * other.denom,
                this.denom * other.num);
    }

    public Rational abs(){
        return new Rational(Math.abs(num), Math.abs(denom));
    }

    //Raises this to the power of p
    // "I have the poweeeerrrrr!!!"
    public Rational pow(double p){
        if(p >= 0){
            return new Rational((int)Math.pow(num, p), (int)Math.pow(denom, p));
        }
        else {
            return new Rational((int)Math.pow(denom, -p), (int)Math.pow(denom, -p));
        }
    }

    //raises p to the power of this
    // "I *AM* THE POWEEERRRRRRR!!!!"
    public double exp(double x){
        double result = Math.pow(x, (double)num/(double)denom);
        if (Math.abs(Math.round(result) - result) < 0.0001){ //because Math.pow can't do integer results for fractional powers accurately
            return Math.round(result);
        }
        return result;
    }

    private void simplify(){
        if(num == 0) { //0/8 becomes 0/1
            denom = 1;
            return;
        }
        if(num < 0 && denom < 0){ //-1/-1 becomes 1/1
            num *= -1;
            denom *= -1;
        }
        if(num > 0 && denom < 0){ //3/-4 becomes -3/4
            num *= -1;
            denom *= -1;
        }

        int absNum = Math.abs(num);
        int absDenom = Math.abs(denom);
        int i = Math.min(absNum, absDenom); //start with the lower of the two; gcd can't be greater than either
        for(; i > 1; i--){
            if(absNum%i == 0 && absDenom%i == 0){
                num /= i;
                denom /= i;
                return;
            }
        }
    }

    public int getNumerator() {
        return num;
    }

    public int getDenominator() {
        return denom;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        Rational other = (Rational) obj;
        return this.getNumerator() == other.getNumerator()
            && this.getDenominator() == other.getDenominator();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + this.getNumerator();
        result = prime * result + this.getDenominator();

        return result;
    }
}
