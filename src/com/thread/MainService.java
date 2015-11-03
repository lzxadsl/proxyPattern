package com.thread;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-2 下午2:30:40
 */
public class MainService {
   
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec =  Executors.newCachedThreadPool();//调用封装好的线程池
		//ThreadPoolExecutor tPool = new ThreadPoolExecutor(10, 20,200, TimeUnit.MICROSECONDS,new ArrayBlockingQueue<Runnable>(10));
		ThreadService ts = new ThreadService();
		for(int i=0;i<5;i++){
			Thread t = new Thread(ts);
			//t.start();
			exec.execute(t);
		}
		exec.shutdown();
	}
	
	/*public String searchFile(String name,String path){
		File f = new File(path);
		if(f.isDirectory() && f.listFiles() != null){
			for(File sf:f.listFiles()){
				if(sf.isDirectory()){
					Runnable r = new FileSearch(name, sf.getAbsolutePath(),tp);
					tp.execute(r);
				}
				else{
					if(name != null && name.equals(sf.getName())){
						retVal = sf.getAbsolutePath();
						System.out.println("找到了："+retVal);
						this.tpe.shutdownNow();
					}
				}
			}
		}
		else if(f.isFile() && name != null && name.equals(f.getName())){
			System.out.println(f.getName());
			retVal = f.getAbsolutePath();
			this.tpe.shutdownNow();
			System.out.println("找到了："+retVal);
		}
		return retVal;
	}*/
}
