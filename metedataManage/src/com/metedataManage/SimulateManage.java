package com.metedataManage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SimulateManage {

	private static void printPartitionResult() {

		Partition partition = new Partition();
		try {
			partition.readDataset("./dataset/points.txt", 0);
			partition.readDataset("./dataset/kcenters.txt", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer, Map<Integer, Nodes>> map = partition.cluster();
		UtilTools utilTools = new UtilTools();

		for (int i = 0; i < map.size(); i++) {
			K_Centers kCenters = partition.getkCenters()
					.get(Integer.valueOf(i));
			System.out.println("\t ���Ľڵ�  K" + utilTools.setNumber(i) + ":"
					+ kCenters.getX() + "\t" + kCenters.getX() + "\t"
					+ kCenters.getZ());

			Map<Integer, Nodes> nodes = map.get(Integer.valueOf(i));

			// �жϸ÷����Ƿ��ֽڵ����

			if (!(nodes == null)) {
				for (Integer key : nodes.keySet()) {
					Nodes node = nodes.get(key);
					System.out.println("��ͨ�ڵ�  " + utilTools.setNumber(key)
							+ ": \t" + node.getX() + "\t" + node.getY() + "\t"
							+ node.getZ());
				}
			} else {
				System.out.println("�޽ڵ����");
			}

		}

	}

	public static void main(String[] args) {

		GenerateData gData = new GenerateData();
		gData.generateRandomPosition();
		printPartitionResult();
	}

}
