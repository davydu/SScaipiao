package com.sscaipiao.android.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class AsyncImageLoader {
    //SoftReference�������ã���Ϊ�˸�õ�Ϊ��ϵͳ���ձ���
    private HashMap<String, SoftReference<Drawable>> imageCache;
    public AsyncImageLoader() {
        imageCache = new HashMap<String, SoftReference<Drawable>>();
    }
    public Drawable loadDrawable(final String imageUrl,final ImageView imageView, 
    		final ImageView cacheIamge,
    		final ImageCallback imageCallback){
        if (imageCache.containsKey(imageUrl)) {
            //�ӻ����л�ȡ
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            Drawable drawable = softReference.get();
            if (drawable != null) {
                return drawable;
            }
        }
        final Handler handler = new Handler() {
            public void handleMessage(Message message) {
                imageCallback.imageLoaded((Drawable) message.obj, imageView,cacheIamge,imageUrl);
            }
        };
        //������һ���µ��߳�����ͼƬ
        new Thread() {
            @Override
            public void run() {
                Drawable drawable = null;
				try {
					//drawable = ImageUtil.geRoundDrawableFromUrl(imageUrl, 20);
					 drawable = loadImageFromUrl(imageUrl);
				} catch (Exception e) {
					e.printStackTrace();
				}
                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                Message message = handler.obtainMessage(0, drawable);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }
    
	public static Drawable loadImageFromUrl(String url) {
		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Drawable d = Drawable.createFromStream(i, "src");
		return d;
	}
	
    //�ص��ӿ�
    public interface ImageCallback {
        public void imageLoaded(Drawable imageDrawable,ImageView imageView, ImageView cacheIamge,String imageUrl);
    }
}
