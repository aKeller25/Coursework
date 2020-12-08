########################################
# Author: Alex Keller
# Date created: 11:06 AM 11/14/2017
# Date last edited: 11:06 AM 11/14/2017
# Semester: Fall2017
# Class: Geog306
# Assignment name: Lab07
########################################

#### Example ####
#Is temperature related to ozone?

#Acquire the data
require(lattice)

#Make a scatterplot
plot(environmental$temperature,environmental$ozone)

# Find r to check the "go-togetherness" of x and y
r <- cor(environmental$temperature,environmental$ozone)

# use the ordinal data 
rs<-cor(rank(environmental$temperature),rank(environmental$ozone),m="s")

# Test rho to see whether or not there is a correlation between x and y.
cor.test(environmental$temperature,environmental$ozone)

#Use the Spearman Method -- nondirectional
cor.test(rank(environmental$temperature), rank(environmental$ozone), method = "spearm")

#Test for directional alternatives 
cor.test(rank(environmental$temperature), rank(environmental$ozone), method = "spearm", alternative = "greater")

cor.test(rank(environmental$temperature), rank(environmental$ozone), method = "spearm", alternative = "less")

### Lab 7 ###
data("faithful")

plot(faithful$eruptions, faithful$waiting)

r <- cor(faithful$eruptions, faithful$waiting)

# Ranks the data ordinally for spearmans
rs<-cor(rank(faithful$eruptions), rank(faithful$waiting),m="s")

cor.test(faithful$eruptions, faithful$waiting)

cor.test(rank(faithful$eruptions), rank(faithful$waiting), method = "spearm")
