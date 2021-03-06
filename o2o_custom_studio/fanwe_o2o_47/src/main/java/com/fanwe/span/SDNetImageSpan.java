package com.fanwe.span;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.fanwe.library.common.SDBitmapCache;
import com.fanwe.library.span.SDDynamicDrawableSpan;
import com.fanwe.o2o.newo2o.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class SDNetImageSpan extends SDDynamicDrawableSpan
{

	private String url;

	public SDNetImageSpan(TextView view)
	{
		super(view);
	}

	public SDNetImageSpan setImage(String url)
	{
		this.url = url;
		return this;
	}

	@Override
	protected int getDefaultDrawableResId()
	{
		return R.drawable.nopic_expression;
	}

	@Override
	protected void beforeReturnDrawable(Drawable drawable)
	{
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth() + 20, drawable.getIntrinsicHeight() + 20);
	}

	@Override
	protected Bitmap onGetBitmap()
	{
		Bitmap bitmap = SDBitmapCache.getInstance().get(url);
		if (bitmap == null)
		{
			ImageLoader.getInstance().loadImage(url, new ImageLoadingListener()
			{

				@Override
				public void onLoadingStarted(String imageUri, View view)
				{
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason)
				{
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
				{
					SDBitmapCache.getInstance().put(url, loadedImage);
					getTextView().postInvalidate();
				}

				@Override
				public void onLoadingCancelled(String imageUri, View view)
				{
				}
			});
		}
		return bitmap;
	}

}
