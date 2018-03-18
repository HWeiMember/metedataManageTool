/**
 * 
 */
package com.metedataManage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author jjyylive
 * 
 */
public class GenerateData {
	private static final int MAX_NODE_NUMBER = 30;
	private static final int K = 3;

	private String[] x1 = new String[MAX_NODE_NUMBER];
	private String[] y1 = new String[MAX_NODE_NUMBER];
	private String[] z1 = new String[MAX_NODE_NUMBER];

	private String[] x2 = new String[MAX_NODE_NUMBER];
	private String[] y2 = new String[MAX_NODE_NUMBER];
	private String[] z2 = new String[MAX_NODE_NUMBER];

	String path1 = new String("./dataset/points.txt");
	String path2 = new String("./dataset/kcenters.txt");

	private void wirteToFile(String path) throws IOException {

		FileWriter fWriter = null;
		try {
			fWriter = new FileWriter(path);

			if (path1.equals(path)) {
				for (int i = 0; i < MAX_NODE_NUMBER; i++) {
					fWriter.write(x1[i] + "\t" + y1[i] + "\t" + z1[i] + "\n");
				}
			} else {
				for (int i = 0; i < K; i++) {
					fWriter.write(x2[i] + "\t" + y2[i] + "\t" + z2[i] + "\n");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fWriter.close();

		}
		System.out.println("数据生成结束！");
	}

	public void generateRandomPosition() {
		int i = 0, j = 0;
		Random rd = new Random();
		while (i < MAX_NODE_NUMBER) {

			x1[i] = String.format("%.3f", rd.nextInt(360000) / 1000.0);
			y1[i] = String.format("%.3f", rd.nextInt(360000) / 1000.0);
			z1[i] = String.format("%.3f", rd.nextInt(360000) / 1000.0);

			i++;
		}
		try {
			wirteToFile(path1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (j < K) {

			x2[j] = String.format("%.3f", rd.nextInt(360000) / 1000.0);
			y2[j] = String.format("%.3f", rd.nextInt(360000) / 1000.0);
			z2[j] = String.format("%.3f", rd.nextInt(360000) / 1000.0);

			j++;
		}
		try {
			wirteToFile(path2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
