package com.e.dailyTasks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;

public class CreatePiecesView extends PiecesView{
    public CreatePiecesView(Context context) {
        super(context);
    }

    public CreatePiecesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CreatePiecesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawingPieces(canvas, Color.WHITE, 20);
    }
}
