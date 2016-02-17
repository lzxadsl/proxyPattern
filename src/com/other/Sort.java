package com.other;


/**
 * 排序算法
 * @author LiZhiXian
 * @version 1.0
 * @date 2016-2-17 下午4:21:32
 */
public class Sort {
	//38 49 65 76 97 13
	//38 49 65 76 13 97
	int[] data = {49,38,65,97,76,13,27,49,78,34,12,64,1};
	
	public void 直接插入排序(){
		for(int i = 1;i < data.length;i++){
			int temp = data[i];
			int j;
			for(j = i-1;j >= 0;j--){
				if(data[j] > temp){
					data[j+1] = data[j];
				}else{
					break;
				}
			}
			data[j] = temp;
		}
		
		for(int i = 0;i < data.length; i++){
			System.out.print(data[i]+" ");
		}
	}
	public static void main(String[] args) {
		
		Sort sort = new Sort();
		sort.直接插入排序();
	}
}
