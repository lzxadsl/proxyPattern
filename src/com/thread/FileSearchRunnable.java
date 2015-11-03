package com.thread;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程模拟文件搜索 使用Runnable
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-2 下午3:38:58
 */
public class FileSearchRunnable implements Runnable{
	
	private File directory;//搜索目录
	private FilenameFilter filter;//要查找的文件名
	private ExecutorService execu;
	public FileSearchRunnable(){
		super();
	}
	public FileSearchRunnable(File directory,FilenameFilter filter,ExecutorService execu){
		super();
		this.filter = filter;
		this.directory = directory;
		this.execu = execu;
	}
	
	List<String> retVal = new ArrayList<String>();
	public List<String> search(String name,String path){
		File file = new File(path);
		if(file.isDirectory()){
			if(file.listFiles() != null){
				for (File sfile : file.listFiles()) {
					if(sfile.isDirectory()){
						search(name,sfile.getAbsolutePath());
					}
					else{
						if(name != null && name.equals(sfile.getName())){
							retVal.add(sfile.getAbsolutePath());
							return retVal;
						}
					}
				}
			}
		}
		else{
			if(name != null && name.equals(file.getName())){
				retVal.add(file.getAbsolutePath());
				return retVal;
			}
		}
		//System.out.println(msg);
		return retVal;
	}
	
	public static void main(String[] args) throws Exception{
		long startTime = System.currentTimeMillis();
		System.out.println("startTime："+(startTime));
		//FileSearch fs = new FileSearch();
		//List<String> result = fs.search("noie.js", "D:\\");//3658
		
		File direct = new File("D:\\");//搜索目录
		//文件过滤（要查找的文件）
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.equals("noie.js");
			}
		};
		//创建线程池
		ExecutorService exec = Executors.newSingleThreadExecutor();
		//newCachedThreadPool newSingleThreadExecutor = newFixedThreadPool(1) 括号中的值越大越慢
		Runnable task = new FileSearchRunnable(direct,filter,exec);
		exec.execute(task);
		//long endTime = System.currentTimeMillis();
		//System.out.println("找到了："+result);
		//System.out.println("用时："+(endTime - startTime));
	}
	
	@Override
	public void run() {
		File[] files = directory.listFiles();
		if(files != null){
			for(File file:files){
				if(file.isDirectory()){
					if(!execu.isShutdown()){//线程池如果被关闭了，表示已经找到了（关闭的情况下如果在添加任务会报错）
						Runnable callable = new FileSearchRunnable(file,filter,execu);
						execu.execute(callable);
					}
				}
				else if(filter.accept(file.getParentFile(),file.getName())){
					System.out.println("找到了："+file.getAbsolutePath());
					long endTime = System.currentTimeMillis();
					System.out.println("endTime："+endTime);
					execu.shutdownNow();
				}
			}
		}
	}
}
