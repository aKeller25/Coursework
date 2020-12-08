########################################
# Author: Alex Keller
# Date created: 11:15 AM 8/29/2017
# Date last edited: 12:00 PM 8/29/2017
# Semester: Fall2017
# Class: Geog306
# Assignment name: LabProblems01
########################################

# Question 1-3
data("faithful")
help(faithful)
View(faithful)

# QUestion 4
setwd("C:/Users/akeller5/Desktop/GEOG306 Lab Introduction to R/GEOG306 Lab Introduction to R")
dc = read.csv("DCprecip.csv" , header = T)
write.csv(dc, "C:/Users/akeller5/Desktop/GEOG306 Lab Introduction to R/GEOG306 Lab Introduction to R/NewDCprecip.csv")

