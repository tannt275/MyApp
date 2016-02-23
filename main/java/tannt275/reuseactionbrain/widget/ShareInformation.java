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
import tannt275.reuseactionbrain.common.AppConfig;
import tannt275.reuseactionbrain.model.GameModel;

/**
 * Created by tannt on 2/18/2016.
 */
public class ShareInformation extends View {

    public static String TAG = ShareInformation.class.getSimpleName();

    public static final int BITMAP_W = 400;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

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

//        int avatar_size = (int) (widthScreen / 1.5f);
        canvas.translate(10, 10);

        // Draw avatar
        if (avatar != null) {
            ImageView avatarView = new ImageView(context);
            avatarView.setLayoutParams(new ViewGroup.LayoutParams(400, 600));
            avatarView.setImageBitmap(avatar);
            avatarView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            avatarView.measure(
                    MeasureSpec.makeMeasureSpec(400, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(600, MeasureSpec.EXACTLY));
            avatarView.layout(0, 0, 400, 600);
            avatarView.draw(canvas);
        }
        canvas.translate(10, 0);
    }

    public void drawAppName() {
        String appName = context.getString(R.string.app_name);
        Rect r = new Rect();
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(40f);
        p.setColor(Color.WHITE);
        p.setUnderlineText(true);
        p.getTextBounds(appName, 0, appName.length(), r);
        canvas.drawText(appName, 20, 480, p);

    }

    public void drawerContent(GameModel gameModel) {

        String gameModeStr = "Game mode: " + (gameModel.get_mode() == 0 ? "Normal" : "Timed");
        String gameScore = "Score: " + gameModel.get_score();
        String gameTime = "Time: " + AppConfig.parseToSecond(gameModel.get_time()) + " s";

        Rect rectGameMode = new Rect();
        Rect rectGameScore = new Rect();
        Rect rectGameTime = new Rect();

        paint.setColor(Color.WHITE);
        paint.setTextSize(20f);
        paint.setStyle(Paint.Style.FILL);

        paint.getTextBounds(gameModeStr, 0, gameModeStr.length(), rectGameMode);
        canvas.drawText(gameModeStr, 20, 510, paint);

        paint.getTextBounds(gameScore, 0, gameScore.length(), rectGameScore);
        canvas.drawText(gameScore, 20, 530, paint);

        paint.getTextBounds(gameTime, 0, gameTime.length(), rectGameTime);
        canvas.drawText(gameTime, 20, 550, paint);
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
