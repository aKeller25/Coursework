########################################
# Author: Alex Keller
# Date created: 11:15 AM 8/29/2017
# Date last edited: 12:00 PM 8/29/2017
# Semester: Fall2017
# Class: Geog306
# Assignment name: Lab01
########################################

#1 R Commands
# 1.1 Expressions
2 + 3 * 12

# 1.2 Assignemnt Operators
x = 2 + 3 *12
x <- 1:20

# 1.3 Functions
sqrt(25)
y <- sqrt(25)
y
y <- sqrt(25); y

print("Hello World!")
print("Hello World!", quote = FALSE)
print("Hello World!", quote = F)

#2 Data organization
# 2.1 Vectors

# have to be of the same type
z <- c(1, 2, 3)
z

b <- scan()
b

# 2.2 Lists
myList <- list(hisLame = "Steve", herName = "Sue", hisAge = 40, herAge = 39)
myList

View(myList)
myList[2]

myList$hisAge

# 2.3 Data frames
View(z)
View(myList)

x <- c(6, 7, 8, 9, 10)
y <- c("a", "b", "c")

fix(y)

xy <- data.frame(x, y)

xy
View(xy)

xy <- data.frame(numbers = x, letters = y); View(xy)

xy[1]

xy$letters

xy[1, 2]

# 2.4 Datasets
#  2.4.1 Built-in datasets
data() # Lists the built in datasets

data("BOD") # Loads the biochemical oxygen demand dataset
help(BOD) # Provides the helpfile for the dataset
View(BOD)

#  2.4.2 Creating datasets
var1 <- c(1, 2, 3)
var2 <- scan() # Can't take in characters?

dataframe <- data.frame(var1, var2)
View(dataframe)

#  2.4.3 Reading data from a file
read.csv("C:/Users/akeller5/Desktop/GEOG306 Lab Introduction to R/GEOG306 Lab Introduction to R/DCprecip.csv")

# 'header' tells R that the first row contains variable names
dc = read.csv("C:/Users/akeller5/Desktop/GEOG306 Lab Introduction to R/GEOG306 Lab Introduction to R/DCprecip.csv", header = TRUE)

View(dc)

setwd("C:/Users/akeller5/Desktop/GEOG306 Lab Introduction to R/GEOG306 Lab Introduction to R")
dc = read.csv("DCprecip.csv" , header = T)
View(dc)

write.csv(dc, "C:/Users/akeller5/Desktop/GEOG306 Lab Introduction to R/GEOG306 Lab Introduction to R/NewDCprecip.csv")
