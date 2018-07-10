package cn.com.nxz.sparkStudy;

import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.hadoop.mapreduce.LzoTextInputFormat;

import cn.com.cennavi.util.FileUtil;
import cn.com.nxz.conf.SparkConfigSington;
import cn.com.nxz.rdd.service.TestLZOFileReadGetInputRDD;

/**
 * lzo压缩文件读取
 * 
 * @author buf
 *
 */
public class TestLZOFileRead {
	
	public SparkConf sparkConf = null;
	public Configuration hadoopConf = null;
	private final String HADOOP_LIBRARY_FILE = "d:\\hadoop-2.4.0";
	public String lzoFile = "hdfs://nujhadoop/Mini/0x2001/a=110000/b=MQ/y=2016/m=06/d=30/w=5/00.lzo";

	public TestLZOFileRead() {
		try {

			System.setProperty("hadoop.home.dir", HADOOP_LIBRARY_FILE);

			SparkConfigSington props = SparkConfigSington.getInstance();
			ArrayList<String> list = FileUtil.quietly(
					props.getValue("spark.jars"), new ArrayList<String>());
			sparkConf=new SparkConf();
			sparkConf.setJars(list.toArray(new String[list.size()]));
			sparkConf.setAppName(props.getValue("spark.appName"));
			sparkConf.setMaster(props.getValue("spark.master"));

			// hadoop配置
			hadoopConf=new Configuration();
			hadoopConf
					.set("io.compression.codecs",
							"org.apache.hadoop.io.compress.DefaultCodec,org.apache.hadoop.io.compress.GzipCodec,com.hadoop.compression.lzo.LzopCodec");
			hadoopConf.set("io.compression.codec.lzo.class",
					"com.hadoop.compression.lzo.LzoCodec");
			hadoopConf.set("mapred.output.compress", "true");
			hadoopConf.set("mapred.output.compression.codec",
					"com.hadoop.compression.lzo.LzopCodec");

		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {
		//因为本地没有安装lzo，所以需要在nuj环境里跑，否则汇报“java.lang.RuntimeException: native-lzo library not available”
		TestLZOFileRead testLZOFileRead = new TestLZOFileRead();
		JavaSparkContext sc = new JavaSparkContext(testLZOFileRead.sparkConf);

		JavaPairRDD<LongWritable, Text> readDataRdd = sc.newAPIHadoopFile(
				testLZOFileRead.lzoFile, LzoTextInputFormat.class,
				LongWritable.class, Text.class, testLZOFileRead.hadoopConf);

		JavaRDD<String> result = readDataRdd
				.map(new TestLZOFileReadGetInputRDD());

		result.saveAsTextFile("file:///d:/data/11211/");

		sc.close();
	}
}
