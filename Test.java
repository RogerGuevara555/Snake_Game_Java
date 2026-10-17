public class Test {
    public static void main(String[] args) {

        Snake sn = new Snake();
        System.out.println(sn.getCoords());
        sn.walk(true);
        System.out.println(sn.getCoords());

    }
}
