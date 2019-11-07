import random, time

class test(object):
	def sort(self, a):
		# self.bubble(a)
		# self.select(a)
		self.insert(a)

	def insert(self, a):
		for i in range(len(a)):
			tmp = a[i]
			j = i
			for j in range(i-1, -1, -1):
				if tmp < a[j]:
					a[j+1] = a[j]
				else:
					j += 1
					break
			a[j] = tmp

	def insertea(self, a):
		for i in range(len(a)):
			tmp = a[i]
			for j in range(i-1,-1,-1):
				if a[j] > tmp:
					a[j+1] = a[j]
				else:
					j = j+1
					break
			a[j] = tmp
	
	def select(self, a):
		for i in range(len(a)):
			tmp = a[i]
			x = i
			for j in range(i+1,len(a)):
				if a[j] < tmp:
					tmp = a[j]
					x = j
			haha = a[i]
			a[i] = tmp
			a[x] = haha

	def bubble(self, a):
		for i in range(len(a)):
			for j in range(len(a)-i-1):
				if a[j] > a[j+1]:
					tmp = a[j+1]
					a[j+1] = a[j]
					a[j] = tmp
		
if __name__ == "__main__":
	arr = []
	for i in range(10000):
		arr.append(random.randint(0, 100))
	# print arr
	start = time.time()
	test().sort(arr)
	# print arr
	print "time: " + str(time.time() - start)