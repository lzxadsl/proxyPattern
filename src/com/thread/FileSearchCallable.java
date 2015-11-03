package com.thread;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 多线程模拟文件搜索 使用Callable
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-3 上午11:58:18
 */
public class FileSearchCallable implements Callable<List<File>>{

	private File directory;//搜索目录
	private FilenameFilter filter;//要查找的文件名

	public FileSearchCallable(File directory,FilenameFilter filter){
		super();
		this.filter = filter;
		this.directory = directory;
	}
	
	@Override
	public List<File> call() throws Exception {
		List<Future<List<File>>> results = new ArrayList<Future<List<File>>>();
		List<File> list = new ArrayList<File>();
		File[] files = directory.listFiles();
		if(files != null){
			for(File file:files){
				if(file.isDirectory()){
					//System.out.println(file.getAbsolutePath());
					Callable<List<File>> callable = new FileSearchCallable(file,filter);
					FutureTask<List<File>> ft = new FutureTask<List<File>>(callable);
					results.add(ft);
					ft.run();
				}
				else if(filter.accept(file.getParentFile(),file.getName())){
					System.out.println("找到了："+file.getPath());
					list.add(file);
				}
			}
			for(Future<List<File>> result : results){
				list.addAll(result.get());
			}
		}
		return list;
	}

	public static void main(String[] args) throws Exception{
		long startTime = System.currentTimeMillis();
		File direct = new File("D:\\");//搜索目录
		//文件过滤（要查找的文件）
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.equals("noie.js");
			}
		};
		Callable<List<File>> fst = new FileSearchCallable(direct, filter);
		FutureTask<List<File>> task = new FutureTask<List<File>>(fst);
		task.run();
		for (File f : task.get()) {
			long endTime = System.currentTimeMillis();
			System.out.println("用时："+(endTime - startTime));
            System.out.println(f.getPath());
        }
	}
}
