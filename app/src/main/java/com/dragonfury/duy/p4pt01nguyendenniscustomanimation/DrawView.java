package com.dragonfury.duy.p4pt01nguyendenniscustomanimation;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import java.util.*;


public class DrawView extends View {
    public DrawView(Context context) {
        super(context);
    }

    private Paint nam = new Paint();
    private Path path = new Path();
    private int colorCount = 0;
    private int colorSpeed = 60;
    private RectF[] snow = new RectF[10];
    private Random generator = new Random();
    private int snowXSpeed = generator.nextInt(6) + 15;
    private int snowYSpeed = 30;
    private int touchCount = 0;
    private int treeOffSetVar = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(3 * getWidth() / 8, 2 * getHeight() / 3, 5 * getWidth() / 8, 6 * getHeight() / 8, nam);
        //Set Style for Tree
        nam.setColor(Color.GREEN);
        nam.setStyle(Paint.Style.FILL);

        //Draw Top Triangle
        drawTriangle(getWidth() / 2 + treeOffSetVar, (float) .125 * getHeight() + treeOffSetVar, (float) 3 * getWidth() / 8 + treeOffSetVar, getHeight() / 3 + treeOffSetVar, 5 * getWidth() / 8 + treeOffSetVar, getHeight() / 3 + treeOffSetVar, canvas);

        //Draw 2nd Triangle
        drawTriangle(getWidth() / 2, 2 * getHeight() / 8 + treeOffSetVar, 2 * getWidth() / 8 + treeOffSetVar, getHeight() / 2 + treeOffSetVar, 6 * getWidth() / 8 + treeOffSetVar, getHeight() / 2 + treeOffSetVar, canvas);

        //Draw 3rd Triangle
        drawTriangle((float) .5 * getWidth() + treeOffSetVar, (float) .4 * getHeight() + treeOffSetVar, getWidth() / 8 + treeOffSetVar, 2 * getHeight() / 3 + treeOffSetVar, 7 * getWidth() / 8 + treeOffSetVar, 2 * getHeight() / 3 + treeOffSetVar, canvas);

        //Draw Flashing Lights
        colorFlash();
        canvas.drawCircle(getWidth() / 2, ((getHeight() / 3) - ((float) .125 * getHeight() / 2)) + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle(3 * getWidth() / 8, (float) .43 * getHeight() + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle(5 * getWidth() / 8, (float) .43 * getHeight() + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle((float) .5 * getWidth(), (float) .43 * getHeight() + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle((float) .35 * getWidth(), (float) .55 * getHeight() + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle((float) .65 * getWidth(), (float) .55 * getHeight() + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle((float) .5 * getWidth(), (float) .55 * getHeight() + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle((float) .25 * getWidth(), (float) .62 * getHeight() + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle((float) .75 * getWidth(), (float) .62 * getHeight() + treeOffSetVar, 40, nam);
        colorFlash();
        canvas.drawCircle((float) .5 * getWidth(), (float) .62 * getHeight() + treeOffSetVar, 40, nam);
        nam.setColor(Color.WHITE);

        //Create Snow RectF Array
        for (int i = 0; i < snow.length; i++) {
            snow[i] = new RectF(i * getWidth() / 20, 0, (i + 1) * getWidth() / 20, (float) getHeight() / 20);
        }

        nam.setStyle(Paint.Style.STROKE);
        //Movement of Snowflake
        for (RectF snowFlake: snow) {
            if (generator.nextInt(2) + 1 == 1){
                snowXSpeed = (generator.nextInt(6) + 15) * -1;
            } else snowXSpeed = generator.nextInt(6) + 15;
            snowFlake.offset(snowXSpeed, snowYSpeed);
        }

        for (RectF snowFlake: snow) {
            canvas.drawRect(snowFlake, nam);
        }
        //Move Snowflake to Top after it reaches bottom
        for (int i = 0; i<snow.length; i++) {
            if (snow[i].top > getWidth()) {
                snow[i].offsetTo(i * getWidth() / 20, 0);
            }
        }
        //Draw Snowflake Lines
        for(int i=0;i<10;i++){
            canvas.drawLine(snow[i].left,snow[i].top,snow[i].right,snow[i].bottom,nam);
            canvas.drawLine(snow[i].width()/2,snow[i].top,snow[i].width()/2,snow[i].bottom,nam);
            canvas.drawLine(snow[i].right,snow[i].top,snow[i].left,snow[i].bottom,nam);
        }
        invalidate();
        //Draw base of tree
        nam.setARGB(500, 165 + treeOffSetVar, 42, 42 + treeOffSetVar);

        System.out.println("test " + 3 * getWidth() / 8 + "," + 6 * getWidth() / 8 + "," + 2 * getHeight() / 3 + "," + 5 * getHeight() / 8);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(touchCount == 0){
                colorFlash();
            }else if(touchCount == 1){
                colorFlash();
            }else if(touchCount == 2){
                colorFlash();
            }else if(touchCount == 4){
                colorFlash();
            }else touchCount = 0;
        }

        return super.onTouchEvent(event);
    }

    private void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3, Canvas canvas) {
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close();
        canvas.drawPath(path, nam);
    }

    private void colorFlash(){
        if(generator.nextInt(4) == 0){
            nam.setColor(Color.BLUE);
        } else if(generator.nextInt(4) == 1){
            nam.setColor(Color.RED);
        } else if(generator.nextInt(4) == 2){
            nam.setColor(Color.YELLOW);
        }
    }

    private void colorFlash1() {
        if (colorCount < colorSpeed) {
            nam.setColor(Color.BLUE);
            colorCount++;
        } else if (colorCount < colorSpeed * 2) {
            nam.setColor(Color.RED);
            colorCount++;
        } else if (colorCount < colorSpeed * 3) {
            nam.setColor(Color.YELLOW);
            colorCount++;
        } else
            colorCount = 0;
    }

    private void colorFlash2() {
        if (colorCount < colorSpeed) {
            nam.setColor(Color.RED);
            colorCount++;
        } else if (colorCount < colorSpeed * 2) {
            nam.setColor(Color.YELLOW);
            colorCount++;
        } else if (colorCount < colorSpeed * 3) {
            nam.setColor(Color.BLUE);
            colorCount++;
        } else
            colorCount = 0;
    }

    public void colorFlash3() {
        if (colorCount < colorSpeed) {
            nam.setColor(Color.YELLOW);
            colorCount++;
        } else if (colorCount < colorSpeed * 2) {
            nam.setColor(Color.RED);
            colorCount++;
        } else if (colorCount < colorSpeed * 3) {
            nam.setColor(Color.BLUE);
            colorCount++;
        } else
            colorCount = 0;
    }
    private void colorFlash4(){
        if(colorCount<colorSpeed){
            nam.setColor(Color.YELLOW);
            colorCount++;
        } else if (colorCount < colorSpeed * 2) {
            nam.setColor(Color.BLUE);
            colorCount++;
        } else if (colorCount < colorSpeed * 3) {
            nam.setColor(Color.RED);
            colorCount++;
        } else colorCount = 0;
    }
}
