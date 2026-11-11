
import javax.swing.*;
import java.awt.*;

public class StringAnimation extends JPanel implements Runnable {

    Thread t;
    int x = 0;
    boolean running = true;

    public void startAnimation() {
        t = new Thread(this);
        t.start();
    }

    public void run() {
        while (running) {
            x = x + 5;

            if (x > getWidth()) {
                x = -200;
            }

            repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 22));

        g.drawString("Welcome to Java Programming", x, 100);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("String Animation");

        StringAnimation obj = new StringAnimation();

        frame.add(obj);

        frame.setSize(600, 200);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        obj.startAnimation();
    }
}
