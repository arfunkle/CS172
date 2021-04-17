public class Pair {

    public int row;
    public int column;

    /**
     * Pair initializes the Pair objects.
     * @param c This object represents the column value.
     * @param r This object represents the row value.
     */
    public Pair(int c, int r) {
        this.row = r;
        this.column = c;
    }

    /**
     * equals checks to see if two Pairs are equal.
     * @param that This is the object which the method checks is equal to the initial Pair.
     * @return The method returns true if the row and column values are the same.
     */
    public boolean equals(Pair that) {
        return ((this.row==that.row)&&(this.column==that.column));
    }

    /**
     * toString converts the Pair into a string.
     * @return The method returns the string version of the Pair.
     */
    public String toString() {
        return "<" + column + ", " + row + ">";
    }

    /**
     * addPair adds another Pair to the initial Pair.
     * @param that This object represents the other Pair.
     * @return The method returns a new Pair: a combination of both Pairs.
     */
    public Pair addPair(Pair that) {
        Pair add = new Pair(0,0);
        add.row = this.row + that.row;
        add.column = this.column + that.column;
        return add;
    }

    /**
     * flipDirection flips the direction of the Pair.
     * @return A new Pair with the values of the initial multiplied by -1.
     */
    public Pair flipDirection() {
        Pair flip = new Pair(this.column*-1,this.row*-1);
        return flip;
    }

    /**
     * isValid determines if a Pair is on the 8 by 8 grid.
     * @return The method returns true if it is on the grid, false if not.
     */
    public boolean isValid() {
        return this.row >= 0 && this.row < 8 && this.column >= 0 && this.column < 8;
    }
}
