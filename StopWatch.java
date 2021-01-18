package org.example.watch;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StopWatch extends Canvas implements Runnable {
    private long startTime = 0;
    private long endTime = 0;
    private boolean bStart = false;
    public StopWatch(){
        super();
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setSize(80,30);
    }

    protected void processMouseEvent(MouseEvent e){
        if(e.getID() == MouseEvent.MOUSE_PRESSED){
            bStart = true;
            startTime = endTime = System.currentTimeMillis();

            repaint();
            new Thread(this).start();
        }else if(e.getID() == MouseEvent.MOUSE_RELEASED){
            bStart = false;
            repaint();
        }
        super.processMouseEvent(e);
    }

    public void paint(Graphics g){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date elapsedTime = null;
        try {
            elapsedTime = sdf.parse("00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        elapsedTime.setTime(endTime - startTime + elapsedTime.getTime());
        String display = sdf.format(elapsedTime);

        g.drawRect(0,0,250,100);
        g.fill3DRect(2,2,248,98,true);
        g.setColor(Color.WHITE);

        Font font = new Font("Default",Font.PLAIN,50);
        g.setFont(font);
        g.drawString(display,20,50);
    }


    @Override
    public void run() {
        while (bStart){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
            repaint();
        }
    }
}
