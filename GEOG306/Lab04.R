########################################
# Author: Alex Keller
# Date created: 11:15 AM 10/10/2017
# Date last edited: 12:03 PM 10/10/2017
# Semester: Fall2017
# Class: Geog306
# Assignment name: Lab04
########################################

### Normal Distribution
## Question 1
pnorm(5, 5, 1)

## Question 2
pnorm(7, 5, 1, lower.tail = FALSE)

## Question 3
qnorm(.25, 5, 1)
qnorm(.25, 5, 1, lower.tail = FALSE)

## Question 4
qnorm(.90, 5, 1, lower.tail = FALSE)


### Confidence interval for mean
## Question 1
xbar = 0.885
std_dev = 0.096
conf_lvl = 0.90
n = 20

se = std_dev / sqrt(n)
tcrit = qt(0.95, df = 19)
me = tcrit * se
lower = xbar - me
upper = xbar + me

## Question 2
data("LakeHuron")
t.test(LakeHuron, conf.lvl = 0.95)

xbar = mean(LakeHuron)
std_dev = sd(LakeHuron)
conf_lvl = 0.90
n = length(LakeHuron)

se = std_dev / sqrt(n)
tcrit = qt(0.975, df = 97)
me = tcrit * se
lower = xbar - me
upper = xbar + me


### Sample size for mean estimate
## Question 1
z = qnorm(0.05 / 2)
s = 16.65
xbar = 74
E = 0.03 * xbar
n = ((z * s) / E)^2
