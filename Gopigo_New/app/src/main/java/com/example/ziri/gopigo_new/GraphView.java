package com.example.ziri.gopigo_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GraphView extends SurfaceView{

    private final int height = 5;
    private final int pixel_beginning = 500;

    private SurfaceHolder holder;
    private Paint paint = new Paint();
    private List<String> data = new ArrayList<>();

    public GraphView(Context context) {
        super(context);
        this.setBackgroundColor(Color.WHITE);
        data = DataHolder.getInstance().getList();
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) { }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    draw(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

        });
    }

    public void draw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(0, pixel_beginning);
        for (int i=0; i<data.size(); i++){
            path.lineTo(i*10+10, pixel_beginning-(Integer.parseInt(data.get(i))*height));
        }
        canvas.drawPath(path, paint);
    }
}