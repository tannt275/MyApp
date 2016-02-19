package tannt275.reuseactionbrain.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tannt on 2/16/2016.
 */
public class DrawerView extends View {

    Paint paint;
    Rect rect;
    public DrawerView(Context context) {
        super(context);
//        paint = new Paint();
    }

    public DrawerView(Context context, Rect r, Paint p){
        super(context);
        this.rect = r;
        this.paint = p;
    }

    public DrawerView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        /*paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);*/
        canvas.drawRect(rect, paint);
        /*canvas.drawRect(30, 30, 80, 80, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.CYAN);
        canvas.drawRect(33, 60, 77, 77, paint );
        paint.setColor(Color.YELLOW);
        canvas.drawRect(33, 33, 77, 60, paint );*/
    }
}
