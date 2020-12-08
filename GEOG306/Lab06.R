############################################################
####Two Sample Difference of Means

## change your directory
# read in a table of data
jobs=read.table(file="jobs2.txt",head=T)

# this selects the lower class and higher class
low1980 = jobs$Y1980[jobs$Class=="Lower"]
high1980 = jobs$Y1980[jobs$Class=="Higher"]

# look at the histogram to see the data distribution
par(mfcol=c(2,1))
hist(low1980,xlim=c(0,60),col="red")
hist(high1980,xlim=c(0,60),col="blue")

# do t test 
t.test(low1980,high1980) # two-sided
t.test(low1980,high1980,alternative="greater") # one-sided
t.test(low1980,high1980,alternative="less") # one-sided


# do wilcoxon test
wilcox.test(low1980,high1980)
wilcox.test(low1980,high1980,alternative="greater") # one-sided
wilcox.test(low1980,high1980,alternative="less") # one-sided



##############################################################
# MATCHED PAIRS TEST
# Table 9.7 in book

# read into data table
temps = read.table("AnnTemp.txt",head=T)	

# do it in R
t.test(temps$Y94,temps$Y74,paired=T,alternative="greater")
wilcox.test(temps$Y94,temps$Y74,paired=T,alternative="greater")


# do matched pairs t-test by hand

# average difference
di = temps$Y94 - temps$Y74
hist(di)
dbar = mean(di)

n = length(temps$Y94)

# standard deviation
sd = sqrt( (sum ((di-dbar)^2)) / (n -1) )
sigma = sd/sqrt(n)

# t-stat
t = dbar/sigma

# p-val (one-tailed)
pval = pt(t,df=n-1,low=F)



########################################################################
###Three of More Samples
# housing price data from Table 10.2 in book

nb1 = c(175,147,138,156,184,148)
nb2 = c(151,183,174,181,193,205,196)
nb3 = c(127,142,124,150,180)
nb4 = c(174,182,210,191)

# analysis of variance needs a column of data and a factor column
price = c(nb1,nb2,nb3,nb4) # this is the data
group = c(rep("1",6),rep("2",7),rep("3",5),rep("4",4)) # this is factor
#### IGNORE groupn = c(rep(1,6),rep(2,7),rep(3,5),rep(4,4)) # this is a numeric factor


# put the data into a data frame (like a data table)
df =data.frame(price,group)

# First ALWAYS make a boxplot
boxplot(nb1,nb2,nb3,nb4)
#or
boxplot(price ~ group)

# do this three different ways
oneway.test(price ~ group, data=df,var=T)
summary(aov(price ~ group,data=df))
anova(lm(price ~ group, data=df))

#how to get F-significance
pf(6.558,3,18,low=F)





