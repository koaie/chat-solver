package net.chat;

import java.util.regex.*;

public class Solver {
    String input;

    public Solver(String input) {
        this.input = input;
    }

    public String testAll() {
        long lag = 0; // Accounts for server compute
        int maths = this.arithmetic(1200, 1800, lag);
        if (maths != 65535) {
            return Integer.toString(maths);
        }
        String quick = this.quick(2000, 3000, lag);
        if (quick != null) {
            return quick;
        }
        return null;
    }

    public void delay(int min, int max, long lag) {
        long curTime = System.currentTimeMillis();
        long delay = (int) (Math.random() * (max + 1 - min) + min);
        Chat.log.info("Delay:{} Lag:{} Current time:{}", delay, lag, curTime);

        while (System.currentTimeMillis() < curTime + delay - lag) {
            ;
        }
        Chat.log.info("Executed at {}", System.currentTimeMillis());
    }

    public int arithmetic(int min, int max, long lag) {
        Pattern p = Pattern.compile("The first to solve '(\\d{1,4})\\s([-+x/])\\s(\\d{1,4})' wins!");
        Matcher m = p.matcher(this.input);
        if (!m.find()) {
            return 65535;
        }

        this.delay(min, max, lag);
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(3));
        String op = m.group(2);

        Chat.log.info("{} {} {}", x, op, y);
        if (op.equals("+")) {
            return x + y;
        } else if (op.equals("x")) {
            return x * y;
        } else if (op.equals("/")) {
            return x / y;
        }
        return x - y;
    }

    public String quick(int min, int max, long lag) {
        Pattern p = Pattern.compile("The first to type '([\\d\\w]+)' wins!");
        Matcher m = p.matcher(this.input);
        if (!m.find()) {
            return null;
        }
        this.delay(min, max, lag);
        return m.group(1);
    }
}
