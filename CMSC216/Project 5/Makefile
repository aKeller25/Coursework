CC = gcc
CFLAGS = -ansi -Wall -g -O0 -Wwrite-strings -Wshadow -pedantic-errors \
	-fstack-protector-all

PROGS = tester public01 public02 public03 public04

all: $(PROGS)

bag.o: bag.h bag-implementation.h bag.c
	$(CC) $(CFLAGS) -c bag.c

my_memory_checker_216.o: my_memory_checker_216.h my_memory_checker_216.c
	$(CC) $(CFLAGS) -c my_memory_checker_216.c

public01: public01.o bag.o
	$(CC) -o public01 public01.o bag.o

public01.o: bag.h bag-implementation.h public01.c
	$(CC) $(CFLAGS) -c public01.c

public02: public02.o bag.o
	$(CC) -o public02 public02.o bag.o

public02.o: bag.h bag-implementation.h public02.c
	$(CC) $(CFLAGS) -c public02.c

public03: public03.o bag.o
	$(CC) -o public03 public03.o bag.o

public03.o: bag.h bag-implementation.h public03.c
	$(CC) $(CFLAGS) -c public03.c

public04: public04.o bag.o my_memory_checker_216.o
	$(CC) -o public04 public04.o bag.o my_memory_checker_216.o

public04.o: my_memory_checker_216.h bag.h bag-implementation.h public04.c
	$(CC) $(CFLAGS) -c public04.c

tester: tester.o bag.o my_memory_checker_216.o
	$(CC) -o tester tester.o bag.o my_memory_checker_216.o

tester.o: my_memory_checker_216.h bag.h bag-implementation.h tester.c
	$(CC) $(CFLAGS) -c tester.c


clean:
	@echo "Cleaning my system"
	@rm -f *.o $(PROGS)
