########################################
# Author: Alex Keller
# Date created: 11:56 AM 11/29/2017
# Date last edited: 12:45 PM 11/29/2017
# Semester: Fall2017
# Class: Geog306
# Assignment name: Lab08
########################################

ozone = read.csv("C:\\Users\\akeller5\\Desktop\\GEOG306 Lab Regression\\GEOG306 Lab Regression\\ozone.csv", header = TRUE)

x = ozone$Temp
y = ozone$Ozone

plot(x, y, xlab="Temperature", ylab="Ozone Concentration")

n = length(ozone$Temp)
productxy = sum(x) * sum(y)
sumxy = sum(x*y)
sumx = sum(x^2)
squarex = sum(x)^2

b = (n*sumxy-productxy)/(n*sumx-squarex); b # 2.428703
a = (sum(y) - b * sum(x)) / n; a # -146.9955

abline(a,b)

newx  =  0.8
yhat  =  a+b*newx; yhat

yhat = a+b*x
rss = sum((yhat-mean(y))^2)
tss  =  sum( (y-mean(y))^2)
rsq  =  rss/tss; rsq # 0.4877072

f  =  (rsq * (n-2)) / (1-rsq); f # 108.529

df1 = 1
df2 = n-2

pvalue  =  1 - pf(f, df1, df2); pvalue # 0


reg  =  lm(y ~ x)
summary(reg)
abline(reg)
predict(reg, data.frame(x=newx))




