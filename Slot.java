enum Slot {
    VOID('-'),
    SNAKE('o'),
    APPLE('2'),
    WALL('3');
    
    private final char repr;

    Slot (char icon) {
        this.repr = icon;
    }

    public char getRepr () {
        return this.repr;
    }
    
    public boolean isThis (char icon) {
        return icon == getRepr();
    }
}