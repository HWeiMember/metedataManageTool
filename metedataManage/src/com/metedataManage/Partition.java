package com.metedataManage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partition {
	//private static final int K = 5;
	private Map<Integer,K_Centers> kCenters = new HashMap<>();
	private Map<Integer,Nodes> nodes = new HashMap<>();
    private Map<Integer, Map<Integer,Nodes>> cluster =new HashMap<>();
    
//	String path1 = "./dataset/points.txt";
//	String path2 = "./dataset/kcenters.txt";
	
	public Map<Integer, K_Centers> getkCenters() {
		return kCenters;
	}

	public Map<Integer, Nodes> getNodes() {
		return nodes;
	}

	public Map<Integer, Map<Integer, Nodes>> getCluster() {
		return cluster;
	}
	

	// add nodes set;
	public void readDataset(String path,int flags) throws IOException {
		BufferedReader br = null;
		int index = 0;
		
		Double x;
		Double y;
		Double z;
		String[] str = new String[3];
		
		try {
			String line =null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				str = line.split("\t");

				x = Double.valueOf(str[0]);
				y = Double.valueOf(str[1]);
				z = Double.valueOf(str[2]);
				// 0  fills nodes; 1  fills kcenters;
				if(flags==0){
					Nodes node =new Nodes(x, y, z);
					nodes.put(Integer.valueOf(index), node);
				}else{
					K_Centers kcenter = new K_Centers(x, y, z);
					kCenters.put(Integer.valueOf(index), kcenter);
				}
				index++;				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}
	}


	// calculate the distance;
	private double getDistance(Points node, Points kcenter) {
		double sum = 0.0;

		double nodeX = node.getX();
		double nodeY = node.getY();
		double nodeZ = node.getZ();

		double kcenterX = kcenter.getX();
		double kcenterY = kcenter.getY();
		double kcenterZ = kcenter.getZ();
		
		sum = (nodeX - kcenterX) * (nodeX - kcenterX) + (nodeY - kcenterY)
				* (nodeY - kcenterY) + (nodeZ - kcenterZ) * (nodeZ - kcenterZ);

		return Math.sqrt(sum);
	}
	private Integer findMinDistanceNode(double[] distances) {
		int index = -1;
		double temp =Double.MAX_VALUE;
		
		for(int i=0;i < distances.length;i++){
			if(temp > distances[i]){
				temp = distances[i];
				index = i;
			}
		}
		if(index == -1){
			System.out.println("findMinDistanceNode error!");
		}
		return Integer.valueOf(index);
		
	}
	//partition using k-centers for all nodes;
	public Map<Integer, Map<Integer,Nodes>> cluster(){
		int k = kCenters.size();
		double[] distanceToCenter =new double[k];
		
		Integer kkey=null;
		Nodes value=null;
		
		Integer nodeKey = null;
		
		System.out.println("nodes "+nodes.size());
		for(int i=0;i < nodes.size();i++){
			for(int j=0;j< k;j++){
				distanceToCenter[j] = getDistance(nodes.get(Integer.valueOf(i)), kCenters.get(Integer.valueOf(j)));
			}
			kkey = findMinDistanceNode(distanceToCenter);
			value = nodes.get(Integer.valueOf(i));
			
			if(!cluster.containsKey(kkey)){
				Map<Integer,Nodes> map = new HashMap<>();
				cluster.put(kkey, map);
			}
			nodeKey = Integer.valueOf(i);
			cluster.get(kkey).put(nodeKey, value);			
		}
		
		return cluster;	
	}
	

}
