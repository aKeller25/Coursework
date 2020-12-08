#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab01.py
# Date created: 7/22/2017
# Last edited: 10:20 PM 7/24/2017
# Variables used: 
# This program is used 
###################################

# Importing arcpy so this program can access its functions
import arcpy

# Setting up the workspace
arcpy.env.workspace = r"C:\Users\akeller5\Desktop\Lab 4\Lab04_data\part2"
arcpy.env.overwriteOutput = True

# Setting the file to a variable
MD_counties = "MD_counties.shp"

# Adding fields to the file
arcpy.AddField_management(MD_counties,"AREA","DOUBLE")
arcpy.AddField_management(MD_counties,"Younger","DOUBLE")
arcpy.AddField_management(MD_counties,"Rate","FLOAT")

# An SQL expression and accompanying function that will populate the AREA column
expr1 = '!Shape.area!'
arcpy.CalculateField_management(MD_counties,"AREA",expr1,'PYTHON')

# An SQL expression and accompanying function that will populate the Younger column
#  Younger means everyone younger than 18
expr2 = '!AGE_UNDER5! + !AGE_5_17!'
arcpy.CalculateField_management(MD_counties,"Younger",expr2,'PYTHON')

# An SQL expression and accompanying function that will populate the Rate column
#  It does this by taking the 2007 population, subtracting the 2000 population from it
#  , dividing it by the 2007 population, and multiplying by 100 to get a percentage
expr3 = '((float(!POP2007!)- float(!POP2000!)) / float(!POP2007!)) * 100' 
arcpy.CalculateField_management(MD_counties,"Rate",expr3,'PYTHON')


