#!/usr/bin/python

import sys


###########################
# READING PARAMETERS

f = open(sys.argv[1],'r')

lstream = []
lsize = []
qstream = []
qsize = []

while 1:
	line = f.readline()
	if not line:
		break

	if "- Max Thread Number:" in line:
		thread = int(line.split()[-1])
	if "- Matrix Threshold:" in line:
		thre = str(line.split()[-1])
	if "- Linear Stream Size:" in line:
		for s in line.split():
			if s.isdigit():
				lstream.append(int(s));
	if "- Linear Matrix Size:" in line:
		for s in line.split():
			if s.isdigit():
				lsize.append(int(s));
	if "- Quadratic Stream Size:" in line:
		for s in line.split():
			if s.isdigit():
				qstream.append(int(s));
	if "- Quadratic Matrix Size:" in line:
		for s in line.split():
			if s.isdigit():
				qsize.append(int(s));


print "PARAMETRS FOUND"
print thread
print thre
print lstream
print lsize
print qstream
print qsize

####################################
### OBJECT DEFINING

matrixpar = [[0 for l in range(thread)] for k in range(len(lsize))]
matrixfarm = [[0 for l in range(thread)] for k in range(len(lsize))]
matrixseq = [0 for m in range(len(lsize))]
matrixqpar = [[0 for l in range(thread)] for k in range(len(qsize))]
matrixqseq = [0 for m in range(len(qsize))]




###################################
#	Linear


for p in lstream:
	
	f.seek(0,0)
	i_size = 0
	while 1:
		line = f.readline()
		if not line:
			break

		lookseq = "Sequential Linear Computation - Streamsize: " + str(p) + " Matrixsize: " + str(lsize[i_size]) + " x " + str(lsize[i_size]) + " Percentage: " + str(thre)
		if lookseq in line :
			line = f.readline()
			if "OUT OF MEMORY" in line:
				matrixseq[i_size] = -1
			else:
				matrixseq[i_size] = int((line[21:]))
			i_size = i_size+1
			if i_size == len(lsize):
				break
	
	f.seek(0,0)	
	i_size = 0
	i_thread = 0
	while 1:
		line = f.readline()
		if not line:
			break
		
		lookpar = "Parallel Linear Computation - Threads: " + str(i_thread+1) + " Streamsize: " + str(p) + " Matrixsize: " + str(lsize[i_size]) + " x " + str(lsize[i_size]) + " Percentage: " + str(thre)


		if lookpar in line :
			line = f.readline()
			
			if "OUT OF MEMORY" in line:
				matrixpar[i_size][i_thread] = -1
			else:
				matrixpar[i_size][i_thread] = int((line[21:]))
			i_thread = i_thread + 1

			if i_thread == thread:
				i_thread = 0
				i_size = i_size + 1
				if i_size >= len(lsize):
					break

	f.seek(0,0)
	i_size = 0
	i_thread = 0
	while 1:
		line = f.readline()
		if not line:
			break
		
		lookfarm = "Parallel Farm Computation - Threads: " + str(i_thread+1) + " Streamsize: " + str(p) + " Matrixsize: " + str(lsize[i_size]) + " x " + str(lsize[i_size]) + " Percentage: " + str(thre)
		

		if lookfarm in line :
			line = f.readline()

			if "OUT OF MEMORY" in line:
				matrixfarm[i_size][i_thread] = -1
			else:
				matrixfarm[i_size][i_thread] = int((line[21:]))

			i_thread = i_thread + 1

			if i_thread == thread:
				i_thread = 0
				i_size = i_size + 1
				if i_size >= len(lsize):
					break

	# Print

	sys.stdout.write("\n");
	sys.stdout.write("Stream;" + str(p) + ";");
	for psize in lsize:
		sys.stdout.write(str(psize) + ";");
	sys.stdout.write("\n");

	sys.stdout.write("Sequential;1;");
	for i in range(len(lsize)):
		sys.stdout.write(str(matrixseq[i]) + ";");
	sys.stdout.write("\n");
	for i in range(thread):
		sys.stdout.write("Parallel;" + str(i+1) + ";");
		for j in range(len(lsize)):
			sys.stdout.write(str(matrixpar[j][i]) + ";")
		sys.stdout.write("\n");
	for i in range(thread):
		sys.stdout.write("Farm;" + str(i+1) + ";");
		for j in range(len(lsize)):
			sys.stdout.write(str(matrixfarm[j][i]) + ";")
		sys.stdout.write("\n");
		
	for j in range (len(lsize)):
		matrixseq[j] = -1
		for i in range(thread):
			matrixfarm[j][i] = -1
			matrixpar[j][i] = -1			
			

###################################
#	Quadratic

for p in qstream:
	
	
	f.seek(0,0)
	i_size = 0
	while 1:
		line = f.readline()
		if not line:
			break

		lookseq = "Sequential Quadratic Computation - Streamsize: " + str(p) + " Matrixsize: " + str(qsize[i_size]) + " x " + str(qsize[i_size]) + " Percentage: " + str(thre)
		if lookseq in line :
			line = f.readline()
			if "OUT OF MEMORY" in line:
				matrixqseq[i_size] = -1
			else:
				matrixqseq[i_size] = int((line[21:]))
			i_size = i_size+1
			if i_size == len(qsize):
				break
	
	f.seek(0,0)	
	i_size = 0
	i_thread = 0
	while 1:
		line = f.readline()
		if not line:
			break
		
		lookpar = "Parallel Quadratic Computation - Threads: " + str(i_thread+1) + " Streamsize: " + str(p) + " Matrixsize: " + str(qsize[i_size]) + " x " + str(qsize[i_size]) + " Percentage: " + str(thre)


		if lookpar in line :
			line = f.readline()
			
			if "OUT OF MEMORY" in line:
				matrixqpar[i_size][i_thread] = -1
			else:
				matrixqpar[i_size][i_thread] = int((line[21:]))
			i_thread = i_thread + 1

			if i_thread == thread:
				i_thread = 0
				i_size = i_size + 1
				if i_size >= len(qsize):
					break

	# Print	
	

	sys.stdout.write("\n");
	sys.stdout.write("Stream;" + str(p) + ";");
	for psize in qsize:
		sys.stdout.write(str(psize) + ";");
	sys.stdout.write("\n");

	sys.stdout.write("SequentialQ;1;");
	for i in range(len(qsize)):
		sys.stdout.write(str(matrixqseq[i]) + ";");
	sys.stdout.write("\n");
	for i in range(thread):
		sys.stdout.write("ParallelQ;" + str(i+1) + ";");
		for j in range(len(qsize)):
			sys.stdout.write(str(matrixqpar[j][i]) + ";")
		sys.stdout.write("\n");

		
	for j in range (len(qsize)):
		matrixseq[j] = -1
		for i in range(thread):
			matrixqpar[j][i] = -1	

f.close()
