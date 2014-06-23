#!/bin/bash

###############################################
###### Histogram Thresholding Test Script
#
###### General Parameters
#
# Max Number of Threads to spawn
#
thread=24
#
# Max Heap Size
#
heap=30720m
#
# Matrix Threshold
#
thre=0.5
#
###### Linear Computation Parameters
#
# Stream size to test
#
linear_stream=(1 5 10 25 50 75 100)
#
# Matrix size to tes
#
linear_size=(1000 2000 5000 7000 10000 12000 15000 20000 25000 30000 35000 40000 50000 60000 70000)
#
###### Quadratic Computation Parameters
#
# Stream size to test
#
quad_stream=(1 5 10)
#
# Matrix size to tes
#
quad_size=(100 150 200 250 300 400 500 600 700 800 900 1000)
###############################################

echo -e "----------------------------------------"
echo -e "\t Histogram Thresholding Test"
echo -e "----------------------------------------"
echo -e "-------------- Parameters --------------"
echo -e "- Max Thread Number: $thread"
echo -e "- Max Heap Size: $heap"
echo -e "- Matrix Threshold: $thre"
echo -e "- Linear Stream Size: { ${linear_stream[*]} }"
echo -e "- Linear Matrix Size: { ${linear_size[*]} }"
echo -e "- Quadratic Stream Size: { ${quad_stream[*]} }"
echo -e "- Quadratic Matrix Size: { ${quad_size[*]} }"
echo -e "----------------------------------------"
echo -e "\n"	


rm histogramthresholding.jar
ant clean
ant build
ant jar


echo -e "########################################"
echo -e "########################################"
echo -e "########## Linear Computation ##########"
echo -e "########################################"
echo -e "########################################"

for strm in ${linear_stream[@]}
do
	echo -e "\n"	
	echo -e "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
	echo -e "\t Stream Size: $strm"
	echo -e "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"

	for sz in ${linear_size[@]}
	do
		echo -e "\n"	
		echo -e "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
		echo -e "\t Matrix Size: $sz"
		echo -e "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
		echo -e "\n"	

		java -Xmx$heap -jar histogramthresholding.jar seq $strm $sz $sz $thre
		if [ $? -ne 0 ]; then
			echo -e "\n !!!!!!!!!!!!!!!!!!!! OUT OF MEMORY  !!!!!!!!!!!!!!!!!!!!"
			continue
		fi
		for k in $( seq 1 $thread )
		do
			java -Xmx$heap -jar histogramthresholding.jar par $k $strm $sz $sz $thre
			if [ $? -ne 0 ]; then
				echo -e "\n !!!!!!!!!!!!!!!!!!!! OUT OF MEMORY  !!!!!!!!!!!!!!!!!!!!"
				break
			fi		
			java -Xmx$heap -jar histogramthresholding.jar farm $k $strm $sz $sz $thre
			if [ $? -ne 0 ]; then
				echo -e "\n !!!!!!!!!!!!!!!!!!!! OUT OF MEMORY  !!!!!!!!!!!!!!!!!!!!"
				break
			fi			
		done
	done
done

echo -e "########################################"
echo -e "########################################"
echo -e "######## Quadratic Computation #########"
echo -e "########################################"
echo -e "########################################"


for strm in ${quad_stream[@]}
do
	echo -e "\n"	
	echo -e "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
	echo -e "\t Stream Size: $strm"
	echo -e "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"

	for sz in ${quad_size[@]}
	do
		echo -e "\n"	
		echo -e "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
		echo -e "\t Matrix Size: $sz"
		echo -e "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
		echo -e "\n"	

		java -Xmx$heap -jar histogramthresholding.jar quadseq $strm $sz $sz $thre
		if [ $? -ne 0 ]; then
			echo -e "\n !!!!!!!!!!!!!!!!!!!! OUT OF MEMORY  !!!!!!!!!!!!!!!!!!!!"
			continue
		fi
		for k in $( seq 1 $thread )
		do
			java -Xmx$heap -jar histogramthresholding.jar quadpar $k $strm $sz $sz $thre
			if [ $? -ne 0 ]; then
				echo -e "\n !!!!!!!!!!!!!!!!!!!! OUT OF MEMORY  !!!!!!!!!!!!!!!!!!!!"
				break
			fi				
		done
	done
done
