CC = gcc
CFLAGS = -ansi -Wall -g -O0 -Wwrite-strings -Wshadow -pedantic-errors \
	-fstack-protector-all

PROGS = student_tests public01 public02 public03

all: $(PROGS)

htable.o: htable.h htable.c
	$(CC) -c $(CFLAGS) htable.c

my_memory_checker_216.o: my_memory_checker_216.h my_memory_checker_216.c
	$(CC) -c $(CFLAGS) my_memory_checker_216.c

public01: public01.o htable.o my_memory_checker_216.o
	$(CC) -o public01 public01.o htable.o my_memory_checker_216.o

public01.o: my_memory_checker_216.h htable.h public01.c
	$(CC) -c $(CFLAGS) public01.c

public02: public02.o htable.o my_memory_checker_216.o
	$(CC) -o public02 public02.o htable.o my_memory_checker_216.o

public02.o: my_memory_checker_216.h htable.h public02.c
	$(CC) -c $(CFLAGS) public02.c

public03: public03.o htable.o my_memory_checker_216.o
	$(CC) -o public03 public03.o htable.o my_memory_checker_216.o

public03.o: my_memory_checker_216.h htable.h public03.c
	$(CC) -c $(CFLAGS) public03.c

student_tests: student_tests.o htable.o my_memory_checker_216.o
	$(CC) -o student_tests student_tests.o htable.o my_memory_checker_216.o

student_tests.o: my_memory_checker_216.h htable.h student_tests.c
	$(CC) -c $(CFLAGS) student_tests.c


clean:
	@echo "Cleaning my system"
	@rm -f *.o $(PROGS)
