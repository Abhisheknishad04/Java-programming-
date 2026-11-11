
public class Main {

    public static void main(String[] args) {

        AdditionBean bean = new AdditionBean();

        bean.setNum1(10);
        bean.setNum2(20);

        System.out.println("First Number: " + bean.getNum1());
        System.out.println("Second Number: " + bean.getNum2());
        System.out.println("Addition: " + bean.getResult());
    }
}
