package com.metedataManage;

import java.io.IOException;

public class SimulateManage {
	public static void main(String[] args) {
		
		//GenerateData gData = new GenerateData();
		//gData.generateRandomPosition();
		Partition partition = new Partition();
		try {
			partition.readDataset("./dataset/points.txt",0);
			partition.readDataset("./dataset/kcenters.txt",1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println(partition.cluster().get(0).size());
		System.out.println(partition.cluster().get(1).size());
		System.out.println(partition.cluster().get(2).size());
		
	}
}
