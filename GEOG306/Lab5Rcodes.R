install.packages("plotrix")
require("plotrix")
help(plotCI)

#confidence interval of male height
male_sample<-c(72,71,70,72,73,65,75,73,72,68,67,74,68,67,73,75,64,72,66,70,70,69,72,75,73,70,78)
male_mean<-mean(male_sample)
male_sd<-sd(male_sample)
male_variance<-male_sd^2
male_n<-length(male_sample)
male_n
male_se<-male_sd/sqrt(male_n)
male_t<-qt(0.975, df=male_n-1) #1-(1-0.95)/2
male_me<-male_t*male_se
male_mean+male_me
male_mean-male_me
male_mean
hist(male_sample, breaks=5, main="Heights of Men in Our Class", xlab="Height in Inches")
plotCI(male_mean,y=2,uiw=male_me,add=T, err="x", col="red")

#confidence level of female height
female_sample<-c(67,64,63,65,59,65,75,66,63,63,68,59,66,67,63,68)
female_mean<-mean(female_sample)
female_sd<-sd(female_sample)
female_variance<-female_sd^2
female_n<-length(female_sample)
female_n
female_se<-female_sd/sqrt(female_n)
female_t<-qt(0.975, df=female_n-1)
female_me<-female_t*female_se
female_mean
hist(female_sample, breaks=5, main="Heights of Women in Our Class", xlab="Height in Inches")
plotCI(female_mean,y=2,uiw=female_me,add=T, err="x", col="red")


#two-sample t-test
nominator <- male_mean-female_mean
denominator1 <- male_variance/male_n
denominator2 <- female_variance/female_n
denominator3 <- sqrt(denominator1+denominator2)
tstat<- nominator/denominator3
tstat
nominator1 <- (denominator1+denominator2)^2
denominator4 <- (denominator1)^2/(male_n-1)
denominator5 <- (denominator2)^2/(female_n-1)
denominator6 <- denominator4 + denominator5
df <- nominator1/denominator6
df
pvalue_greater <- pt(tstat,df=df,lower.tail=F)
pvalue_greater
pvalue_less <- pt(-tstat,df=df)
pvalue_less
p<-pvalue_less+pvalue_greater
p
t.test(male_sample,female_sample)

#one-sample hypothesis test (male vs. whold)
mu = (female_mean*female_n+male_mean*male_n)/(male_n+female_n)
tstat <- (male_mean-mu)/(male_sd/sqrt(male_n))
tstat
pvalue_greater <- pt(tstat,df=male_n-1,lower.tail=F)
pvalue_greater
pvalue_less <- pt(-tstat,df=male_n-1)
pvalue_less
p<-pvalue_less+pvalue_greater
p
#built-in function
t.test(male_sample,mu=mu)
