########################################
# Author: Alex Keller
# Date created: 11:39 AM 9/19/2017
# Date last edited: 12:50 PM 9/19/2017
# Semester: Fall2017
# Class: Geog306
# Assignment name: Lab03
########################################

### Question 1 
## Part A
dbinom(2, 31, 0.3)

## Part B
# 5, instead of 6 because when you set lower.tail 
# to false, it changes it from >= to <
pbinom(5, 31, 0.3, lower.tail = FALSE)


### Question 2
## Part A
dbinom(10, 10, 0.9)

## Part B
dbinom(8, 10, 0.9)


### Question 3
# This is because the question is asking for "A" meteorite,
# not "A" as in more than 1 
dpois(1, 0.6095)


### Question 4 
# This is fewer than 3, NOT 3 or fewer
pbinom(2, 10, 0.15)
