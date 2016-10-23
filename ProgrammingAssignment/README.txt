I) To Copy Jar files from local machine to AWS Cloud machines:

1) Rename the Jar file 'Task2a.md’ -> 'Task2a.jar'

2) Rename the Jar file 'Task2a.md’ -> 'Task2b.jar'

3) Harinarayanan:Cloud Hari$ scp -i CloudKeyPair.pem Task2a.jar ubuntu@ec2-52-55-2-64.compute-1.amazonaws.com:/home/ubuntu

4) Harinarayanan:Cloud Hari$ scp -i CloudKeyPair.pem Task2b.jar ubuntu@ec2-52-55-2-64.compute-1.amazonaws.com:/home/ubuntu


II) Copy Input Files to Hadoop File System to peform both tasks:

1) ubuntu@ip-172-31-5-213:~$ hadoop fs -mkdir /input

2) ubuntu@ip-172-31-5-213:~$ hadoop fs -hadoop fs -put states/* /input/


III) Running Both Task A & B

Task 2a:

1) Run Jar File

ubuntu@ip-172-31-5-213:~$ hadoop jar Task2a.jar ProgrammingAssignment/Task2a 

2) List All the Files in Hadoop File System 

ubuntu@ip-172-31-5-213:~$ hadoop fs -ls /
Found 5 items
drwxr-xr-x   - ubuntu supergroup          0 2016-10-17 18:00 /input
drwxr-xr-x   - ubuntu supergroup          0 2016-10-17 18:16 /job1Output
drwxr-xr-x   - ubuntu supergroup          0 2016-10-17 18:16 /job2Output
drwxr-xr-x   - ubuntu supergroup          0 2016-10-17 18:16 /output
drwx------   - ubuntu supergroup          0 2016-10-17 18:07 /tmp

3) Print Contents of Job 2's Output 

ubuntu@ip-172-31-5-213:~$ hadoop fs -cat /job1Output/part-r-00000
Missouri	a3
Florida	a1
Iowa	a7
Alabama	a1
California	a2
Massachusetts	a2
Tennessee	a2
South_Dakota	a4
Maryland	a2
Louisiana	a4
Wisconsin	a3
Nevada	a3
Ohio	a1
New_Jersey	a1
Delaware	a1
Washington	a1
Virginia	a4
Mississippi	a3
Texas	a2
Vermont	a3
North_Carolina	a4
Wyoming	a2
Oklahoma	a2
Kansas	a1
Kentucky	a1
Alaska	a1
Georgia	a1
Michigan	a1
Colorado	a1
Pennsylvania	a2
Nebraska	a2
Arkansas	a3
New_York	a1
North_Dakota	a3
Missouri	e6
New_Mexico	e4
Florida	e3
Alabama	e10
Iowa	e6
California	e6
Oregon	e2
New_Hampshire	e3
Massachusetts	e4
Tennessee	e6
South_Dakota	e3
Maryland	e5
Louisiana	e6
Wisconsin	e2
West_Virginia	e4
Ohio	e3
New_Jersey	e12
Idaho	e1
Minnesota	e1
Delaware	e3
Washington	e6
Virginia	e9
Illinois	e3
Mississippi	e6
Hawaii	e7
Texas	e12
Connecticut	e1
Vermont	e2
Montana	e5
North_Carolina	e3
Oklahoma	e12
Kansas	e4
South_Carolina	e15
Arizona	e9
Kentucky	e11
Alaska	e3
Georgia	e1
Michigan	e4
Colorado	e2
Pennsylvania	e3
Nebraska	e1
Arkansas	e13
North_Dakota	e1
New_York	e5
New_Mexico	p1
Florida	p3
Alabama	p4
California	p2
New_Hampshire	p1
Massachusetts	p3
Tennessee	p4
South_Dakota	p2
Maryland	p3
Louisiana	p2
West_Virginia	p5
Ohio	p1
Idaho	p2
New_Jersey	p1
Virginia	p4
Mississippi	p2
Texas	p2
Vermont	p2
Wyoming	p2
North_Carolina	p5
Kansas	p1
Michigan	p2
Maine	p1
Colorado	p2
Pennsylvania	p3
North_Dakota	p1
Arkansas	p4
Missouri	p1
Iowa	p2
Rhode_Island	p1
Oregon	p2
Wisconsin	p8
Nevada	p2
Minnesota	p1
Washington	p1
Delaware	p1
Illinois	p2
Hawaii	p2
Connecticut	p1
Montana	p1
Oklahoma	p3
South_Carolina	p8
Arizona	p4
Kentucky	p3
Alaska	p1
Georgia	p2
Utah	p4
Nebraska	p1
New_York	p1
Missouri	s8
New_Mexico	s1
Alabama	s4
Florida	s7
Iowa	s2
Rhode_Island	s1
California	s8
Oregon	s4
New_Hampshire	s3
Massachusetts	s4
Indiana	s2
South_Dakota	s3
Louisiana	s5
Maryland	s3
Wisconsin	s3
Nevada	s3
Ohio	s1
Idaho	s1
New_Jersey	s18
Minnesota	s8
Delaware	s1
Washington	s2
Illinois	s11
Virginia	s5
Texas	s5
Vermont	s3
Connecticut	s9
Montana	s7
North_Carolina	s3
Oklahoma	s5
Kansas	s12
Arizona	s8
Kentucky	s1
Michigan	s3
Utah	s6
Pennsylvania	s2
Colorado	s8
Maine	s1
Nebraska	s1
Arkansas	s3
New_York	s1

4) Print Contents of Job 2's Output 

ubuntu@ip-172-31-5-213:~$ hadoop fs -cat /job2Output/part-r-00000
Alabama	e
Alaska	e
Arizona	e
Arkansas	e
California	s
Colorado	s
Connecticut	s
Delaware	e
Florida	s
Georgia	p
Hawaii	e
Idaho	p
Illinois	s
Indiana	s
Iowa	a
Kansas	s
Kentucky	e
Louisiana	e
Maine	p
Maryland	e
Massachusetts	e
Michigan	e
Minnesota	s
Mississippi	e
Missouri	s
Montana	s
Nebraska	a
Nevada	a
New_Hampshire	e
New_Jersey	s
New_Mexico	e
New_York	e
North_Carolina	p
North_Dakota	a
Ohio	e
Oklahoma	e
Oregon	s
Pennsylvania	p
Rhode_Island	s
South_Carolina	e
South_Dakota	a
Tennessee	e
Texas	e
Utah	s
Vermont	a
Virginia	e
Washington	e
West_Virginia	p
Wisconsin	p
Wyoming	a

5) Print Contents of Job 3's Output or Final Output

ubuntu@ip-172-31-5-213:~$ hadoop fs -cat /output/part-r-00000
Word: Agriculture =>  No of States :	7
Word: Education =>  No of States :	22
Word: Politics =>  No of States :	7
Word: Sports =>  No of States :	14


Task 2b:

6)

ubuntu@ip-172-31-5-213:~$ hadoop fs -rm -r /job1Output
16/10/17 18:23:56 INFO fs.TrashPolicyDefault: Namenode trash configuration: Deletion interval = 0 minutes, Emptier interval = 0 minutes.
Deleted /job1Output

7)

ubuntu@ip-172-31-5-213:~$ hadoop fs -rm -r /job2Output
16/10/17 18:24:03 INFO fs.TrashPolicyDefault: Namenode trash configuration: Deletion interval = 0 minutes, Emptier interval = 0 minutes.
Deleted /job2Output

8)

ubuntu@ip-172-31-5-213:~$ hadoop fs -rm -r /output
16/10/17 18:24:10 INFO fs.TrashPolicyDefault: Namenode trash configuration: Deletion interval = 0 minutes, Emptier interval = 0 minutes.
Deleted /output

9) Run Jar File 

ubuntu@ip-172-31-5-213:~$ hadoop jar Task2b.jar ProgrammingAssignment/Task2b

10) List All the Files in Hadoop File System 

ubuntu@ip-172-31-5-213:~$ hadoop fs -ls /
Found 5 items
drwxr-xr-x   - ubuntu supergroup          0 2016-10-17 18:00 /input
drwxr-xr-x   - ubuntu supergroup          0 2016-10-17 18:30 /job1Output
drwxr-xr-x   - ubuntu supergroup          0 2016-10-17 18:31 /job2Output
drwxr-xr-x   - ubuntu supergroup          0 2016-10-17 18:31 /output
drwx------   - ubuntu supergroup          0 2016-10-17 18:07 /tmp

11) Print Contents of Job 1's Output

ubuntu@ip-172-31-5-213:~$ hadoop fs -cat /job1Output/part-r-00000
Missouri	a3
Florida	a1
Iowa	a7
Alabama	a1
California	a2
Massachusetts	a2
Tennessee	a2
South_Dakota	a4
Maryland	a2
Louisiana	a4
Wisconsin	a3
Nevada	a3
Ohio	a1
New_Jersey	a1
Delaware	a1
Washington	a1
Virginia	a4
Mississippi	a3
Texas	a2
Vermont	a3
North_Carolina	a4
Wyoming	a2
Oklahoma	a2
Kansas	a1
Kentucky	a1
Alaska	a1
Georgia	a1
Michigan	a1
Colorado	a1
Pennsylvania	a2
Nebraska	a2
Arkansas	a3
New_York	a1
North_Dakota	a3
Missouri	e6
New_Mexico	e4
Florida	e3
Alabama	e10
Iowa	e6
California	e6
Oregon	e2
New_Hampshire	e3
Massachusetts	e4
Tennessee	e6
South_Dakota	e3
Maryland	e5
Louisiana	e6
Wisconsin	e2
West_Virginia	e4
Ohio	e3
New_Jersey	e12
Idaho	e1
Minnesota	e1
Delaware	e3
Washington	e6
Virginia	e9
Illinois	e3
Mississippi	e6
Hawaii	e7
Texas	e12
Connecticut	e1
Vermont	e2
Montana	e5
North_Carolina	e3
Oklahoma	e12
Kansas	e4
South_Carolina	e15
Arizona	e9
Kentucky	e11
Alaska	e3
Georgia	e1
Michigan	e4
Colorado	e2
Pennsylvania	e3
Nebraska	e1
Arkansas	e13
North_Dakota	e1
New_York	e5
New_Mexico	p1
Florida	p3
Alabama	p4
California	p2
New_Hampshire	p1
Massachusetts	p3
Tennessee	p4
South_Dakota	p2
Maryland	p3
Louisiana	p2
West_Virginia	p5
Ohio	p1
Idaho	p2
New_Jersey	p1
Virginia	p4
Mississippi	p2
Texas	p2
Vermont	p2
Wyoming	p2
North_Carolina	p5
Kansas	p1
Michigan	p2
Maine	p1
Colorado	p2
Pennsylvania	p3
North_Dakota	p1
Arkansas	p4
Missouri	p1
Iowa	p2
Rhode_Island	p1
Oregon	p2
Wisconsin	p8
Nevada	p2
Minnesota	p1
Washington	p1
Delaware	p1
Illinois	p2
Hawaii	p2
Connecticut	p1
Montana	p1
Oklahoma	p3
South_Carolina	p8
Arizona	p4
Kentucky	p3
Alaska	p1
Georgia	p2
Utah	p4
Nebraska	p1
New_York	p1
Missouri	s8
New_Mexico	s1
Alabama	s4
Florida	s7
Iowa	s2
Rhode_Island	s1
California	s8
Oregon	s4
New_Hampshire	s3
Massachusetts	s4
Indiana	s2
South_Dakota	s3
Maryland	s3
Louisiana	s5
Wisconsin	s3
Nevada	s3
Ohio	s1
Idaho	s1
New_Jersey	s18
Minnesota	s8
Delaware	s1
Washington	s2
Illinois	s11
Virginia	s5
Texas	s5
Vermont	s3
Connecticut	s9
Montana	s7
North_Carolina	s3
Oklahoma	s5
Kansas	s12
Arizona	s8
Kentucky	s1
Michigan	s3
Utah	s6
Pennsylvania	s2
Colorado	s8
Maine	s1
Nebraska	s1
Arkansas	s3
New_York	s1

12) Print Contents of Job 2's Output

ubuntu@ip-172-31-5-213:~$ hadoop fs -cat /job2Output/part-r-00000
Alabama	a,p,e
Alaska	p,e
Arizona	p,s,e
Arkansas	a,p,e
California	p,e,s
Colorado	a,e,s
Connecticut	p,s
Delaware	s,e
Florida	a,e,s
Georgia	e,p
Hawaii	p,e
Idaho	e,p
Illinois	p,e,s
Indiana	s
Iowa	p,e,a
Kansas	p,e,s
Kentucky	s,p,e
Louisiana	p,a,s,e
Maine	s
Maryland	a,p,e
Massachusetts	a,p,s
Michigan	a,p,s,e
Minnesota	e,s
Mississippi	p,a,e
Missouri	p,a,e,s
Montana	p,e,s
Nebraska	e,a
Nevada	p,s
New_Hampshire	p,s
New_Jersey	p,e,s
New_Mexico	p,e
New_York	s,e
North_Carolina	s,a,p
North_Dakota	e,a
Ohio	p,e
Oklahoma	a,p,s,e
Oregon	p,s
Pennsylvania	a,e
Rhode_Island	p
South_Carolina	p,e
South_Dakota	p,s,a
Tennessee	a,p,e
Texas	a,s,e
Utah	p,s
Vermont	e,s
Virginia	p,s,e
Washington	p,s,e
West_Virginia	e,p
Wisconsin	e,s,p
Wyoming	p

13) Print Contents of Job 3's Output or Final Output

ubuntu@ip-172-31-5-213:~$ hadoop fs -cat /output/part-r-00000
[1.Agriculture,2.Education]    => 	  [Pennsylvania]
[1.Agriculture,2.Education,3.Sports]    => 	  [Florida,Colorado]
[1.Agriculture,2.Politics,3.Education]    => 	  [Arkansas,Maryland,Tennessee,Alabama]
[1.Agriculture,2.Politics,3.Sports]    => 	  [Massachusetts]
[1.Agriculture,2.Politics,3.Sports,4.Education]    => 	  [Michigan,Oklahoma]
[1.Agriculture,2.Sports,3.Education]    => 	  [Texas]
[1.Education,2.Agriculture]    => 	  [Nebraska,North_Dakota]
[1.Education,2.Politics]    => 	  [Georgia,West_Virginia,Idaho]
[1.Education,2.Sports]    => 	  [Vermont,Minnesota]
[1.Education,2.Sports,3.Politics]    => 	  [Wisconsin]
[1.Politics]    => 	  [Wyoming,Rhode_Island]
[1.Politics,2.Agriculture,3.Education]    => 	  [Mississippi]
[1.Politics,2.Agriculture,3.Education,4.Sports]    => 	  [Missouri]
[1.Politics,2.Agriculture,3.Sports,4.Education]    => 	  [Louisiana]
[1.Politics,2.Education]    => 	  [Ohio,Alaska,Hawaii,South_Carolina,New_Mexico]
[1.Politics,2.Education,3.Agriculture]    => 	  [Iowa]
[1.Politics,2.Education,3.Sports]    => 	  [Illinois,California,New_Jersey,Montana,Kansas]
[1.Politics,2.Sports]    => 	  [New_Hampshire,Oregon,Utah,Connecticut,Nevada]
[1.Politics,2.Sports,3.Agriculture]    => 	  [South_Dakota]
[1.Politics,2.Sports,3.Education]    => 	  [Washington,Arizona,Virginia]
[1.Sports]    => 	  [Maine,Indiana]
[1.Sports,2.Agriculture,3.Politics]    => 	  [North_Carolina]
[1.Sports,2.Education]    => 	  [Delaware,New_York]
[1.Sports,2.Politics,3.Education]    => 	  [Kentucky]


