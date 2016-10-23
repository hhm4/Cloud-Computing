package ProgrammingAssignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Task2b{
	
	public static class Mapper1 extends Mapper<LongWritable,Text,Text,Text>{
		
		JobConf conf;
		public void configure(JobConf conf) {
		    this.conf = conf;
		}
		
		public void map(LongWritable key, Text value,Context context)throws IOException, InterruptedException{
			String FileName = ((FileSplit)context.getInputSplit()).getPath().getName();
			String line=value.toString();
			StringTokenizer token=new StringTokenizer(line," ");
			while(token.hasMoreElements()){
				String temp=new String(token.nextToken());
				temp=new String(temp.trim());
				if (temp.equalsIgnoreCase("education")||temp.equalsIgnoreCase("politics")||
						temp.equalsIgnoreCase("sports")||temp.equalsIgnoreCase("agriculture")){
					context.write(new Text(temp.trim().toLowerCase()), new Text(FileName));
				}
			}
		}
	}
	
	public static class Reducer1 extends Reducer<Text,Text,Text,Text>{
		public void reduce(Text key,Iterable<Text> values,Context context) throws IOException, InterruptedException{
			HashMap <String,Integer> temp=new HashMap<String,Integer>();
			for (Text val: values){
				if (temp.containsKey(val.toString())){
					temp.put(val.toString(), temp.get(val.toString())+1);
				}
				else{
					temp.put(val.toString(),1);
				}
			}
			
			for (String wordKey:temp.keySet()){
				String t=key.toString().substring(0,1)+temp.get(wordKey).toString();
				context.write(new Text(wordKey), new Text(t));
			}
		}
	}
	
	
	public static class Mapper2 extends Mapper<LongWritable,Text,Text,Text>{
		public void map(LongWritable key, Text value,Context context)throws IOException, InterruptedException{
			String line=value.toString();
			String state=line.split("\\t")[0];
			String word=line.split("\\t")[1];
			context.write(new Text(state), new Text(word));
		}
	}
	
	public static class Reducer2 extends Reducer<Text,Text,Text,Text>{
		public void reduce(Text key,Iterable<Text> values,Context context) throws IOException, InterruptedException{
			HashMap<Integer,String> words=new HashMap<Integer,String>();
			for (Text val:values){
				int x=Integer.parseInt(val.toString().substring(1, val.toString().length()));
				words.put(x, val.toString().substring(0,1));
			}
			Map<Integer,String> sortedWords=new TreeMap<Integer,String>(words);
			
			List<String> sorted=new ArrayList<String>(sortedWords.values());
			String ranking=StringUtils.join(sorted,",");
			context.write(key, new Text(ranking));
		}
	}
	
	public static class Mapper3 extends Mapper<LongWritable,Text,Text,Text>{
		public void map(LongWritable key, Text value,Context context)throws IOException, InterruptedException{
			String line=value.toString();
			String state=line.split("\\t")[0];
			String ranking=line.split("\\t")[1];
			context.write(new Text(ranking), new Text(state));
		}
	}
	
	public static class Reducer3 extends Reducer<Text,Text,Text,Text>{
		public void reduce(Text key,Iterable<Text> values,Context context) throws IOException, InterruptedException{
			ArrayList<String> states=new ArrayList<String>();
			ArrayList<String> wordRanking=new ArrayList<String>();
			int count=1;
			for (int i=0; i<key.toString().length();i++){
				switch(key.toString().charAt(i)){
				case 's':
					wordRanking.add((count++)+".Sports");
					break;
				case 'e':
					wordRanking.add((count++)+".Education");
					break;
				case 'p':
					wordRanking.add((count++)+".Politics");
					break;
				case 'a':
					wordRanking.add((count++)+".Agriculture");
					break;
				}
			}
			for (Text val:values){
				states.add(val.toString());
			}
			String ranking=StringUtils.join(wordRanking,",");
			ranking="["+ranking+"]    => ";
			String equalStates=StringUtils.join(states,",");
			equalStates="  ["+equalStates+"]";
			context.write(new Text(ranking), new Text(equalStates));
			
		}
	}
		
public static void main(String []args)throws Exception{
		
		Configuration conf=new Configuration();
		Job job1=new Job(conf,"Word Count");
		job1.setJarByClass(Task2b.class); 
		FileInputFormat.addInputPath(job1, new Path("/input"));//Input 1 
		FileOutputFormat.setOutputPath(job1,new Path("/job1Output"));//Output 1  or Input 2
		job1.setMapperClass(Mapper1.class);
		job1.setReducerClass(Reducer1.class);
		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(Text.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		job1.waitForCompletion(true);
		
		Job job2=new Job(conf,"Job 2");
		job2.setJarByClass(Task2b.class);
		FileInputFormat.addInputPath(job2, new Path("/job1Output"));// Input 2 or Output 1
		FileOutputFormat.setOutputPath(job2,new Path("/job2Output"));//  Output 2 or Input 3 
		job2.setMapperClass(Mapper2.class);
		job2.setReducerClass(Reducer2.class);
		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);
		job2.waitForCompletion(true);
		
		Job job3=new Job(conf,"Job 3");
		job3.setJarByClass(Task2b.class);
		FileInputFormat.addInputPath(job3, new Path("/job2Output"));// Input 3 or Output 2
		FileOutputFormat.setOutputPath(job3,new Path("/output"));//Final Output
		job3.setMapperClass(Mapper3.class);
		job3.setReducerClass(Reducer3.class);
		job3.setMapOutputKeyClass(Text.class);
		job3.setMapOutputValueClass(Text.class);
		job3.setOutputKeyClass(Text.class);
		job3.setOutputValueClass(Text.class);
		System.exit(job3.waitForCompletion(true) ? 0 : 1); 
}
}
