########################################
# Author: Alex Keller
# Date created: 11:22 AM 9/5/2017
# Date last edited: 12:19 PM 9/5/2017
# Semester: Fall2017
# Class: Geog306
# Assignment name: Lab02
########################################

### Question 1
x = c(0.7, 0.9, 0.9, 0.7, 0.8, 0.7, 1.0, 0.8, 0.8, 0.9, 0.7, 1.2, 0.6, 0.5, 0.8, 1.0, 1.0, 0.8, 0.6, 0.8)

## Mean
xbar = mean(x)

## Mode

# This is a function taken from the lab instructions
new_data = x[order(x)]; new_data

myMode <- function(x) {
  z = table(as.vector(x))
  names(z)[z == max(z)]
}

mode = myMode(new_data) 

## Median
median = median(x)

## Range
range = range(x)

## Quantile (technically quartiles)
quantile(x, probs = seq(0.0, 1.0, 0.25))

## Boxplot, histograms, and CDF
boxplot(x)

hist(x, breaks = 5)

plot(ecdf(x)) # Plots the cumulaive distribution function


## Standard deviation
std_dev = sd(x)

## Variance 
variance = std_dev ^ 2

## CV
CV = std_dev / xbar


### Question 2
x_2 = c(0.91, 0.87, 0.97, 0.79,	1.09, 0.96, 0.98, 0.82,	0.84, 0.89, 1.01, 0.92,	0.70, 0.88, 0.91, 0.89, 0.79, 0.81, 0.99, 0.75)

## Mean
xbar_2 = mean(x_2)

## Mode

# This is a function taken from the lab instructions
new_data_2 = x_2[order(x_2)]; new_data_2

mode_2 = myMode(new_data_2) 

## Median
median_2 = median(x_2)

## Range
range_2 = range(x_2)

## Quantile (technically quartiles)
quantile(x_2, probs = seq(0.0, 1.0, 0.25))

## Boxplot, histograms, and CDF
boxplot(x_2)

hist(x_2, breaks = 5)

plot(ecdf(x_2)) # Plots the cumulaive distribution function


## Standard deviation
std_dev_2 = sd(x_2)

## Variance 
variance_2 = std_dev_2 ^ 2

## CV (Coefficient of Variation)
CV_2 = std_dev_2 / xbar_2


### Question 3
x_3 = c(5, 1, 1, 4, 3, 3, 4, 5,	2, 3, 5, 4, 3, 2, 1, 5,	1, 2, 2, 3)

## Mean
xbar_3 = mean(x_3)

## Mode

# This is a function taken from the lab instructions
new_data_3 = x_3[order(x_3)]; new_data_3

mode_3 = myMode(new_data_3) 

## Median
median_3 = median(x_3)

## Range
range_3 = range(x_3)

## Quantile (technically quartiles)
quantile(x_3, probs = seq(0.0, 1.0, 0.25))

## Boxplot, histograms, and CDF
boxplot(x_3)

hist(x_3, breaks = 5)

plot(ecdf(x_3)) # Plots the cumulaive distribution function


## Standard deviation
std_dev_3 = sd(x_3)

## Variance 
variance_3 = std_dev_3 ^ 2

## CV (Coefficient of Variation)
CV_3 = std_dev_3 / xbar_3





















