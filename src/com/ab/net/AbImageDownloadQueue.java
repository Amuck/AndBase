/*
 * 
 */
package com.ab.net;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ab.global.AbAppData;
import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;

// TODO: Auto-generated Javadoc
/**
 * 描述：图片下载线程，按队列下载（先检查SD卡是否存在相同文件，不存在则下载，最后再从SD卡中读取）.
 *
 * @author zhaoqp
 * @date 2011-12-10
 * @version v1.0
 */
public class AbImageDownloadQueue extends Thread { 
	
	/** The tag. */
	private static String TAG = "AbImageDownloadQueue";
	
	/** The Constant D. */
	private static final boolean D = AbAppData.DEBUG;
	
	/** 下载队列. */
	private List<AbImageDownloadItem> queue;
	
	/** 图片下载线程单例类. */
    private static AbImageDownloadQueue imageDownloadThread = null; 
    
    /** 下载完成后的消息句柄. */
    private static Handler handler = new Handler() { 
        @Override 
        public void handleMessage(Message msg) { 
        	//if(D)Log.d(TAG, "任务callback handleMessage...");
            AbImageDownloadItem item = (AbImageDownloadItem)msg.obj; 
            item.callback.update(item.bitmap, item.imageUrl); 
        } 
    }; 
    
    /**
     * 构造图片下载线程队列.
     */
    private AbImageDownloadQueue() {
		queue = new ArrayList<AbImageDownloadItem>();
    } 
    
    /**
     * 单例构造图片下载线程.
     *
     * @return single instance of AbImageDownloadQueue
     */
    public static AbImageDownloadQueue getInstance() { 
        if (imageDownloadThread == null) { 
            imageDownloadThread = new AbImageDownloadQueue(); 
            //创建后立刻运行
            imageDownloadThread.start(); 
        } 
        return imageDownloadThread; 
    } 
     
    /**
     * 开始一个下载任务.
     *
     * @param item 图片下载单位
     * @return Bitmap 下载完成后得到的Bitmap
     */
    public void download(AbImageDownloadItem item) { 
    	//检查图片路径
    	String url = item.imageUrl;
    	if(AbStrUtil.isEmpty(url)){
    		if(D)Log.d(TAG, "图片URL为空，请先判断");
    	}else{
    		url = url.trim();
    	}
    	if(AbFileUtil.getFileNameFromUrl(url) != null){
    		//如果SD卡有这个图片, 先到SD中找这个图片
        	if(AbImageCache.imageCache.containsKey(item.imageUrl)){
        		item.bitmap = AbImageCache.imageCache.get(item.imageUrl).get();
        		if(item.bitmap == null){
        			addDownloadItem(item); 
        		}else{
	        		if (item.callback != null) {
	                    Message msg = handler.obtainMessage(); 
	                    msg.obj = item; 
	                    handler.sendMessage(msg); 
	                }
        	    }
        	}else{
        		addDownloadItem(item); 
        	}
    	}else{
    		if (item.callback != null) {
                Message msg = handler.obtainMessage(); 
                msg.obj = item; 
                handler.sendMessage(msg); 
            }
    	}
    } 
    
    /**
     * 描述：添加到图片下载线程队列.
     *
     * @param item 图片下载单位
     */
    private synchronized void addDownloadItem(AbImageDownloadItem item) { 
        queue.add(item); 
        //添加了下载项就激活本线程 
        this.notify();
    } 
    
    /**
     * 开始一个下载任务并清除原来队列.
     *
     * @param item 下载单位
     */
    public void downloadBeforeClean(AbImageDownloadItem item) { 
    	queue.clear();
        addDownloadItem(item); 
    } 
 
    /**
     * 描述：线程运行.
     *
     * @see java.lang.Thread#run()
     */
    @Override 
    public void run() { 
        while(true) { 
        	//if(D)Log.d(TAG, "任务大小："+queue.size());
            while(queue.size() > 0) { 
                AbImageDownloadItem item = queue.remove(0); 
                //开始下载
                item.bitmap = AbFileUtil.getBitmapFromSDCache(item.imageUrl,item.type,item.width,item.height);
                //缓存图片路径
				AbImageCache.imageCache.put(item.imageUrl,new SoftReference<Bitmap>(item.bitmap)); 
                //需要执行回调来显示图片
                if (item.callback != null) { 
                	//if(D)Log.d(TAG, "任务callback...");
                    //交由UI线程处理 
                    Message msg = handler.obtainMessage(); 
                    msg.obj = item; 
                    handler.sendMessage(msg); 
                } 
            } 
            try { 
            	//没有下载项时等待 
                synchronized(this) { 
                    this.wait();
                } 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
    } 

}

