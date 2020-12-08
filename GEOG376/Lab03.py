#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab01.py
# Date created: 07/20/2017
# Last edited: 11:41 PM 7/20/2017
# Variables used: MD_Cities, MD_Counties, desc_MD_Cities, sr_MD_Cities, desc_MD_Counties, sr_MD_Counties
# This program is used to find and change the projection for the MD_cities shapefile to either
#   NAD 1983 StatePlane Maryland FIPS 1900 (Meters) or WGS_1984_UTM_Zone_18N depending on the conditions
#   , change the the MD_Counties shapefile projection to match MD_cities's, and to print out the number of
#   counties in the shapefile since my UID is odd
###################################

# Importing arcpy so this program can access its functions
import arcpy

# Setting up the workspace
arcpy.env.workspace = r"C:\Users\akeller5\Desktop\Lab 3\Lab03_data\part2"
arcpy.env.overwriteOutput = True

# Setting the variables without .shp so filename manipulation can be done later
MD_Cities = "MD_cities"
MD_Counties = "MD_counties"

# Setting variable for the files' descriptions and spatial references
desc_MD_Cities = arcpy.Describe(MD_Cities + ".shp")
sr_MD_Cities = desc_MD_Cities.spatialReference
desc_MD_Counties = arcpy.Describe(MD_Counties + ".shp")
sr_MD_Counties = desc_MD_Counties.spatialReference

sr_nad = arcpy.SpatialReference("NAD 1983 StatePlane Maryland FIPS 1900 (Meters)")
sr_utm = arcpy.SpatialReference("WGS 1984 UTM Zone 18N")

# Checking if the MD_Cities shp file has a projection, doing difference actions based on it, and printing out its projection in the end

# If it doesn't have a projection, change it to NAD_1983_StatePlane_Maryland_FIPS_1900
if sr_MD_Cities.Name == "Unknown":
    arcpy.DefineProjection_management(MD_Cities + ".shp", sr_nad)
    print "{0} has the projection {1}".format(MD_Cities + ".shp", sr_MD_Cities.Name)
    
# If it has the projection NAD_1983_StatePlane_Maryland_FIPS_1900, switch it to WGS 1984 UTM Zone 18N; and vice versa
else:
    if sr_MD_Cities.Name == "NAD_1983_StatePlane_Maryland_FIPS_1900":
        arcpy.Project_management(MD_Cities + ".shp", MD_Cities + "_2.shp", sr_utm)

    elif sr_MD_Cities.Name == " WGS 1984 UTM Zone 18N":
        arcpy.Project_management(MD_Cities + ".shp", MD_Cities + "_2.shp", sr_nad)

    print "{0} has the projection {1}".format(MD_Cities + "_2.shp", sr_MD_Cities.Name)

# Checking if the MD_Counties shp file has a projection, doing difference actions based on it, and printing out its projection in the end

# If it doesn't have a projection, change it to the same one that the MD_cities shp file has
if sr_MD_Counties.Name == "Unknown":
    arcpy.DefineProjection_management(MD_Counties + ".shp", sr_MD_Cities)
    print "{0} has the projection {1}".format(MD_Counties + ".shp", sr_MD_Counties.Name)

# If it does have a projection, change it to the same one that the MD_cities shp file has
elif sr_MD_Counties.Name != sr_MD_Cities.Name:
    arcpy.Project_management(MD_Counties + ".shp", MD_Counties +"_2.shp", sr_MD_Cities.Name)
    print "{0} has the projection {1}".format(MD_Counties + "_2.shp", sr_MD_Counties.Name)
    

# My UID is odd so I am printing the number of counties in the counties shapefile
num_of_counties = arcpy.GetCount_management(MD_Counties + ".shp").getOutput(0)

print "The number of counties in the {0} shapefile is {1}".format(MD_Counties, num_of_counties)






