#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab05.py
# Date created: 7/27/2017
# Last edited: 11:29 PM 7/27/2017
# Variables used: baseFileName, totalCounties, countyNum, inf, expr1, expr2, expr3,
#  countiesLayer, numCounties1, perCounties1, numCounties2, perCounties2,
#  numCounties3, perCounties3
# This program is used merge all of the .shp files of all the counties of Maryland into
#  one file. After that 3 selections will be done: one for the counties that have a
#  median age of less than 30, one the has a median age between 30 and 35, and one
#  that has a median age of above 35. When that is done it will print out the number
#  and percentage of all counties that fit the critera.
###################################

# Importing arcpy so this program can access its functions
import arcpy

print "Begin processing"

# Setting up the workspace
arcpy.env.workspace = r"C:\Users\akeller5\Desktop\Lab 5\Lab05_data\part2"
arcpy.env.overwriteOutput = True

# Defining the base file name and the list which they will al be stored in
baseFileName = "county"
totalCounties = []

# A for loop that goes through all 23 counties and adds them to the totalCounties list
for countyNum in xrange(0,23):

    inf = baseFileName + "_" + str(countyNum) + ".shp"
    totalCounties.append(inf)

# Merging the list of shapefiles into one file
arcpy.Merge_management(totalCounties, baseFileName + '_all.shp')

# Setting the layer name
countiesLayer = "Counties"

# Actually making the layer
arcpy.MakeFeatureLayer_management(baseFileName + '_all.shp', countiesLayer)

# Selecting all counties in the merged file that have a median age of below 30
expr1 = '"MED_AGE" < 30'
arcpy.SelectLayerByAttribute_management(countiesLayer, "", expr1)

# Getting the number of countries that was selected and getting it's percentage out
#  of all of the counties
numCounties1 = int(arcpy.GetCount_management(countiesLayer).getOutput(0))
perCounties1 = int(round((numCounties1 / 23) * 100))

# Selecting all counties in the merged file that have a median age of
#  above or equal to 30 but less than 35
expr2 = '"MED_AGE" >= 30 AND "MED_AGE" < 35'
arcpy.SelectLayerByAttribute_management(countiesLayer, "", expr2)

# Getting the number of countries that was selected and getting it's percentage out
#  of all of the counties
numCounties2 = int(arcpy.GetCount_management(countiesLayer).getOutput(0))
perCounties2 = int(round((numCounties2 / 23) * 100))

# Selecting all counties in the merged file that have a median age of equal to or
#  above 35
expr3 = '"MED_AGE" >= 35'
arcpy.SelectLayerByAttribute_management(countiesLayer, "", expr3)

# Getting the number of countries that was selected and getting it's percentage out
#  of all of the counties
numCounties3 = int(arcpy.GetCount_management(countiesLayer).getOutput(0))
perCounties3 = int(round((numCounties3 / 23) * 100))

# Printing output
print "# of total counties: 24"

print "# of counites class 1: {0}, about {1}% of all counties".format(numCounties1, perCounties1)
print "# of counites class 2: {0}, about {1}% of all counties".format(numCounties2, perCounties2)
print "# of counites class 3: {0}, about {1}% of all counties".format(numCounties3, perCounties3)

print "Process End"

