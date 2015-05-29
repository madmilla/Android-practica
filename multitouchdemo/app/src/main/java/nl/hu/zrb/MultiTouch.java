package nl.hu.zrb;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MultiTouch extends Activity implements OnTouchListener {
	// Matrix instances to move and zoom image

	Matrix matrix               = new Matrix();
	Matrix eventMatrix          = new Matrix();

	// possible touch states
	final static int NONE       = 0;
	final static int DRAG       = 1;
	final static int ZOOM       = 2;

	int touchState              = NONE;

    private PointF start        = new PointF();
    private PointF mid          = new PointF();
    private float oldDist       = 1f;
    private float d             = 0f;
    private float newRot        = 0f;
    float eventDistance 		= 0f;



    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView view = (ImageView) findViewById(R.id.imageView);
		view.setOnTouchListener(this);
	}
    
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageView view = (ImageView) v;
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                //primary touch event starts: remember touch down location
                eventMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                touchState = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //secondary touch event starts: remember distance and center
                eventDistance = calcDistance(event);
                if (eventDistance > 10f) {
                    eventMatrix.set(matrix);
                    calcMidpoint(mid, event);
                    touchState = ZOOM;
                }
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                touchState = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (touchState == DRAG) {
                    //single finger drag, translate accordingly
                    matrix.set(eventMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);

                } else if (touchState == ZOOM) {
                    //multi-finger zoom, scale accordingly around center
                    float dist = calcDistance(event);

                    if (dist > 10f) {
                        matrix.set(eventMatrix);
                        float scale = dist / eventDistance;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                    if (event.getPointerCount() == 2) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        matrix.postRotate(r, view.getMeasuredWidth() / 2, view.getMeasuredHeight() / 2);
                    }
                }
                break;
        }
        // Perform the transformation
        view.setImageMatrix(matrix);
        Log.w("?", start.x + "|" + start.y + " " + mid.x + "|" + mid.y + " ");
		return true;
	}

	private float calcDistance(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}
	
	private void calcMidpoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
        point.set(x/2,y/2);
	}

	private float rotation(MotionEvent event){
		double delta_X = (event.getX(0) - event.getX(1));
		double delta_Y = (event.getY(0) - event.getY(1));
		double radians = Math.atan2(delta_Y,delta_X);
		return (float) Math.toDegrees(radians);
	}
}


