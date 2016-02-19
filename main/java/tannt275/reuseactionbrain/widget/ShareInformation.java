package tannt275.reuseactionbrain.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.awt.font.TextAttribute;

import tannt275.reuseactionbrain.R;

/**
 * Created by tannt on 2/18/2016.
 */
public class ShareInformation extends View {

    public static String TAG = ShareInformation.class.getSimpleName();

    public static final int BITMAP_W = 1200;
    public static final int BITMAP_H = 600;

    private Context context;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;

    private int heightScreen;
    private int widthScreen;

    private int appNameInY;
    private int userNameInY;
    private int modeGameInY;
    private int scoreInY;

    public ShareInformation(Context context) {
        super(context);
        this.context = context;
        if (!isInEditMode())
            initBitmap();
    }

    public ShareInformation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (!isInEditMode())
            initBitmap();
    }

    private void initBitmap() {
        calculatorScreen();
        bitmap = Bitmap.createBitmap(BITMAP_W, BITMAP_H, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
    }

    public void drawAvatar(Bitmap avatar) {

        int avatar_size = (int) (widthScreen / 3.0f);
        canvas.translate(10, 10);

        // Draw avatar
        if (avatar != null) {
            ImageView avatarView = new ImageView(context);
            avatarView.setLayoutParams(new ViewGroup.LayoutParams(avatar_size, avatar_size));
            avatarView.setImageBitmap(avatar);
            avatarView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            avatarView.measure(
                    MeasureSpec.makeMeasureSpec(avatar_size, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(avatar_size, MeasureSpec.EXACTLY));
            avatarView.layout(0, 0, avatar_size, avatar_size);
            avatarView.draw(canvas);
        }
        canvas.translate(10, 0);
    }

    public void drawAppName() {
        String appName = context.getString(R.string.app_name);
        Rect r = new Rect();
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(50f);
        p.setColor(Color.BLUE);
        p.getTextBounds(appName, 0, appName.length(), r);
        int posX = widthScreen / 2 - r.right / 2;
        int posY = r.height();
        canvas.drawText(appName, posX, posY, p);

        /*int yPos = 40;
        int xPos = (widthScreen - r.right)/2;
        canvas.drawText(appName, xPos, yPos, p);*/
    }

    Rect bitmapRect;
    Rect screenRect;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmapRect == null)
            bitmapRect = new Rect(0, 0, widthScreen, heightScreen);
        if (screenRect == null)
            screenRect = new Rect(0, 0, getWidth(), getHeight());

        canvas.drawBitmap(bitmap, bitmapRect, screenRect, paint);
    }


    private void calculatorScreen() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        heightScreen = displayMetrics.heightPixels;
        widthScreen = displayMetrics.widthPixels;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        height = (int) (width * BITMAP_H / (float) BITMAP_W);

        int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);
    }
}
