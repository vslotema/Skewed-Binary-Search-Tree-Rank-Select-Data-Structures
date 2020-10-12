#
# takes two arguments n_from, n_to
#
# seeds adjustable in code
#

import random
import sys
import math
import array

n_from = int(sys.argv[1])
n_to = int(sys.argv[2])
p = int(sys.argv[3])
alpha = float(sys.argv[4])

seed_array = array.array('i', [1,2,3,4,5])

for power in range(n_from, n_to + 1):

    n = int(math.pow(2, power))


    for i in seed_array:
        random.seed(i)


        my_file = open("test_" + str(n) + "_" + str(i) + ".txt","w+")

        my_set = set()
        while (len(my_set) != n) :
            my_set.add(random.randint(-2147483648/2, 2147483648/2))

        my_query_set = set()
        while (len(my_query_set) != 2*n):
            my_query_set.add(random.randint(-2147483648, 2147483647))

        my_file.write(str(n) + " " + str(p) + " " + str(alpha))
        my_file.write("\n")

        for i in my_set :
            my_file.write(str(i ) + " " )

        my_file.write("\n")

        for i in my_query_set :
            my_file.write(str(i ) + " " )
