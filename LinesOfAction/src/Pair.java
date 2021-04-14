public class Pair {

    public int row;
    public int column;

    public Pair(int c, int r) {
        this.row = r;
        this.column = c;
    }

    public boolean equals(Pair that) {
        return ((this.row==that.row)&&(this.column==that.column));
    }

    public String toString() {
        return "<" + row + ", " + column + ">";
    }

    public Pair addPair(Pair that) {
        Pair add = new Pair(0,0);
        add.row = this.row + that.row;
        add.column = this.column + that.column;
        return add;
    }

    public Pair flipDirection() {
        this.row *= -1;
        this.column *= -1;
        return this;
    }

}
