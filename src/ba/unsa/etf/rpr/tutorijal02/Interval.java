package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetnaTacka;
    private double krajnjaTacka;
    private boolean pocetnaPripadaIntervalu;
    private boolean krajnjaPripadaIntervalu;

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pocetnaPripadaIntervalu, boolean krajnjaPripadaIntervalu) {
        if (pocetnaTacka > krajnjaTacka) throw new IllegalArgumentException("Pocetna tacka je veca od krajnje!");
        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pocetnaPripadaIntervalu = pocetnaPripadaIntervalu;
        this.krajnjaPripadaIntervalu = krajnjaPripadaIntervalu;
    }

    public Interval() {
        this.pocetnaTacka = 0;
        this.krajnjaTacka = 0;
        this.krajnjaPripadaIntervalu = false;
        this.krajnjaPripadaIntervalu = false;
    }

    public boolean isNull() {
        return (pocetnaTacka == 0 && krajnjaTacka == 0 && !pocetnaPripadaIntervalu && !krajnjaPripadaIntervalu);
    }

    public boolean isIn(double tacka) {
        if(((pocetnaPripadaIntervalu && (pocetnaTacka <= tacka)) || (pocetnaTacka < tacka)) && ((krajnjaPripadaIntervalu && (krajnjaTacka >= tacka)) || (krajnjaTacka > tacka))) return true;
        else return false;
    }

    public Interval intersect(Interval interval) {
        double pocetak = 0, kraj = 0;
        boolean pocetakPripada = false, krajPripada = false;
        if (this.krajnjaTacka == interval.pocetnaTacka) {
            if (!this.krajnjaPripadaIntervalu && !interval.pocetnaPripadaIntervalu) return new Interval();
            else if (this.krajnjaPripadaIntervalu)
                return new Interval(this.krajnjaTacka, interval.pocetnaTacka, true, false);
            else return new Interval(this.krajnjaTacka, interval.pocetnaTacka, false, true);
        } else if (this.krajnjaTacka < interval.pocetnaTacka) return new Interval();

        if (this.pocetnaTacka == interval.pocetnaTacka) {
            pocetak = this.pocetnaTacka;
            if (this.pocetnaPripadaIntervalu == interval.krajnjaPripadaIntervalu)
                pocetakPripada = pocetnaPripadaIntervalu;
        } else if (this.pocetnaTacka > interval.pocetnaTacka) {
            pocetak = this.pocetnaTacka;
            pocetakPripada = this.pocetnaPripadaIntervalu;
        } else {
            pocetak = interval.pocetnaTacka;
            pocetakPripada = interval.pocetnaPripadaIntervalu;
        }

        if (this.krajnjaTacka == interval.krajnjaTacka) {
            kraj = this.krajnjaTacka;
            if (this.krajnjaPripadaIntervalu == interval.krajnjaPripadaIntervalu)
                krajPripada = this.krajnjaPripadaIntervalu;
        } else if (this.krajnjaTacka < interval.krajnjaTacka) {
            kraj = this.krajnjaTacka;
            krajPripada = this.krajnjaPripadaIntervalu;
        } else {
            kraj = interval.krajnjaTacka;
            krajPripada = interval.krajnjaPripadaIntervalu;
        }

        return new Interval(pocetak, kraj, pocetakPripada, krajPripada);
    }

    public static Interval intersect(Interval i1, Interval i2) {
        return i1.intersect(i2);
    }

    @Override
    public boolean equals(Object obj) {
        Interval i = (Interval) obj;
        return (this.pocetnaTacka == i.pocetnaTacka && this.krajnjaTacka == i.krajnjaTacka && this.pocetnaPripadaIntervalu == i.pocetnaPripadaIntervalu && this.krajnjaPripadaIntervalu == i.krajnjaPripadaIntervalu);
    }

    @Override
    public String toString() {
        if(this.pocetnaTacka == 0 && this.krajnjaTacka == 0 && !this.pocetnaPripadaIntervalu && !this.krajnjaPripadaIntervalu) return "()";
        String returner = "";
        if(this.pocetnaPripadaIntervalu) returner += "[";
        else returner += "(";
        returner += this.pocetnaTacka + "," + this.krajnjaTacka;
        if(this.krajnjaPripadaIntervalu) returner += "]";
        else returner += ")";

        return returner;
    }
}
