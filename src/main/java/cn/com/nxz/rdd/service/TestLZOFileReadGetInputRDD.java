package cn.com.nxz.rdd.service;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.function.Function;

import scala.Tuple2;


public class TestLZOFileReadGetInputRDD implements Function<Tuple2<LongWritable,Text>, String>{

	private static final long serialVersionUID = 1L;

	public String call(Tuple2<LongWritable, Text> v1) throws Exception {
		// TODO Auto-generated method stub
		return v1._2.toString();
	}

}
