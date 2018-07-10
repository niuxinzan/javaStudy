package cn.com.nxz.sparkStudy;

import java.util.ArrayList;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import cn.com.cennavi.util.FileUtil;
import cn.com.nxz.conf.SparkConfigSington;

/**
 * 本地文件读取
 * @author buf
 *
 */
public class TestLocalFileRead {
	
	private static String logFile = "file:///d:/data/POI_Relationbeijing.mid";
	private static String outFile = "file:///d:/data/121";
	private static String hadoopFile="d:\\hadoop-2.4.0";
	
	private static SparkConf sparkconf = new SparkConf();
	public int init() {	
		try {
			System.setProperty("hadoop.home.dir",hadoopFile);
			SparkConfigSington props = SparkConfigSington.getInstance();
			ArrayList<String> list = FileUtil.quietly(props.getValue("spark.jars"),
					new ArrayList<String>());
			sparkconf.setJars(list.toArray(new String[list.size()]));
			sparkconf.setAppName(props.getValue("spark.appName"));
			sparkconf.setMaster(props.getValue("spark.master"));
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	public static void main(String[] args) {
		int initResult = new TestLocalFileRead().init();
		if (initResult == 0) {
			System.out.println("init mathod failure!");
			System.exit(0);
		}

		JavaSparkContext sc = new JavaSparkContext(sparkconf);
		JavaRDD<String> logData = sc.textFile(logFile).cache();

		JavaRDD<String> result = logData.map(new Function<String, String>() {

			public String call(String line) throws Exception {
				System.out.println(line);
				return line;
			}
		});
		result.saveAsTextFile(outFile);
		sc.close();

	}

	
}
