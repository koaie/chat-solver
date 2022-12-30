package net.chat;

import java.util.regex.*;

public class Solver {
    String input;
    public Solver(String input)
    {
        this.input = input;
    }

    public String testAll(){
        int maths = this.arithmetic(1200,1800);
        if(maths != 65535) {
            return Integer.toString(maths);
        }
        String quick = this.quick(2000,3000);
        if(quick != null){
            return quick;
        }
        return null;
    }


    public void delay(int min, int max, long lag) {
		long curTime = System.currentTimeMillis();
		long delay = (int) (Math.random() * (max - lag + 1 - min - lag) + min - lag);
		Logger.LOGGER.info("Delay:{} Current time:{}", delay, curTime);

		while (System.currentTimeMillis() < curTime + delay) {
			;
		}
		Logger.LOGGER.info("Executed at {}", System.currentTimeMillis());
    }

    public void delay(int min, int max) {
		long curTime = System.currentTimeMillis();
		long delay = (int) (Math.random() * (max + 1 - min) + min);
		Logger.LOGGER.info("Delay:{} Current time:{}", delay, curTime);

		while (System.currentTimeMillis() < curTime + delay) {
			;
		}
		Logger.LOGGER.info("Executed at {}", System.currentTimeMillis());
    }

    
    public int arithmetic(int min, int max) {
        Pattern p = Pattern.compile("The first to solve '(\\d{1,4})\\s([-+x/])\\s(\\d{1,4})' wins!");
		Matcher m = p.matcher(this.input);
        if(!m.find())
        {
            return 65535;
        }

        this.delay(min, max);
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(3));
        String op = m.group(2);

        Logger.LOGGER.info("{} {} {}",x,op,y);
        if(op.equals("+")) {
            return x + y;
        }
        else if(op.equals("x"))
        {
            return x * y;
        }
        else if(op.equals("/"))
        {
            return x/y;
        }
        return x - y;
    }
    public String quick(int min, int max) {
        Pattern p = Pattern.compile("The first to type '([\\d\\w]+)' wins!");
        Matcher m = p.matcher(this.input);
        if(!m.find())
        {
            return null;
        }
        this.delay(min, max);
        return m.group(1);
    }
}
