package com.other;


/**
 * 排序算法
 * @author LiZhiXian
 * @version 1.0
 * @date 2016-2-17 下午4:21:32
 */
public class Sort {
	
	public void 直接插入排序(int[] data){
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
			data[j+1] = temp;
		}
	}
	public void 二分法插入排序(int[] data){
		int z = 0;
		for(int i = 1;i < data.length;i++){
			int low = 0;//低位下标
			int temp = data[i];
			int hight = i-1;
			if(temp > data[hight]){
				continue;
			}
			while(low < hight){
				int mid = (hight+low)/2;//二分位下标
				
				if(temp < data[mid]){//在左边
					hight = mid - 1;
				}else{//在右边
					low = mid + 1;
				}
				z++;
			}
			for(int j = i;j > low;j--){
				data[j] = data[j-1];
			}
			if(low != i){
				data[low] = temp;
			}
		}
		System.out.println("计算次数："+z);
	}
	//1 3 4 2 5 6
	public static void main(String[] args) {
		int[] data = {49,38,65,97,76,13,27,49,78,34,12,64,1};
		Sort sort = new Sort();
		//sort.直接插入排序(data);
		sort.二分法插入排序(data);
		for(int i = 0;i < data.length; i++){
			System.out.print(data[i]+" ");
		}
	}
}
