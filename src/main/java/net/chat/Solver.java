package net.chat;

import java.util.regex.*;

public class Solver {
    private long lag = 100;
    public Answer solve(String input) {
        int maths = this.arithmetic(input);
        if (maths != 65535) {
            return new Answer(Integer.toString(maths), this.delay(2000, 2400));
        }
        String quick = this.quick(input);
        if (quick != null) {
            return new Answer(quick, this.delay(3400, 4200));
        }
        String reversed = this.unreverse(input);
        if (reversed != null) {
            return new Answer(reversed, this.delay(2500, 3200));
        }
        return null;
    }

    public long setLag(long lag) {
        Chat.log.info("Lag set as {}", lag);
        this.lag = lag;
        return this.lag;
    }

    public long delay(int min, int max) {
        long delay = (int) (Math.random() * (max + 1 - min) + min);

        Chat.log.info("Delay:{} Lag:{}", delay, this.lag);
        delay = delay - this.lag;
        delay = delay / 50; // Milisceonds to ticks
        if (delay > 0) {
            return delay;
        }
        return 0;
    }

    public int arithmetic(String input) {
        Pattern p = Pattern.compile("^The first to solve '(\\d{1,4})\\s([-+x/])\\s(\\d{1,4})' wins!");
        Matcher m = p.matcher(input);
        if (!m.find()) {
            return 65535;
        }
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

    public String quick(String input) {
        Pattern p = Pattern.compile("^The first to type '([\\d\\w]+)' wins!");
        Matcher m = p.matcher(input);
        if (!m.find()) {
            return null;
        }
        return m.group(1);
    }

    public String unreverse(String input) {
        Pattern p = Pattern.compile("^The first to unreverse '([\\d\\w\\s]+)' back to the right order wins!");
        Matcher m = p.matcher(input);
        if (!m.find()) {
            return null;
        }
        StringBuilder text = new StringBuilder();
        text.append(m.group(1));
        text.reverse();
        return text.toString();
    }
}
