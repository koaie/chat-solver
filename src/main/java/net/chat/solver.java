package net.chat;

import java.util.regex.*;

public class Solver {
    String input;
    public Solver(String input)
    {
        this.input = input;
    }

    public String testAll(){
        int maths = this.arithmetic();
        if(maths != 0) {
            return Integer.toString(maths);
        }
        return null;
    }

    
    public int arithmetic() {
        Pattern p = Pattern.compile("The first to solve '(\\d{1,4})\\s([-+])\\s(\\d{1,4})' wins!");
		Matcher m = p.matcher(this.input);
        if(!m.find())
        {
            return 0;
        }

        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(3));
        String op = m.group(2);

        Logger.LOGGER.info("{} {} {}",x,op,y);
        if(op.equals("+")) {
            return x + y;
        }
        return x - y;
    }
}
