#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab07.py
# Date created: 8/14/2017
# Last edited: 9:32 PM 8/14/2017
# Variables used: infile, infile_sr, points_list, fields,
#   cursor, row, point, county_centroid
#
# This program is used to: 1. Use a search cursor to make a shapefile
#   out of a multipoint geometry using the coordinates for the
#   centroid of the counties in Maryland. 2. Add a Rate (population rate)
#   and perimeter fields to each county in the shapefile and use an
#   update cursor to set the values for each county
###################################

# Setting up the environment 
import arcpy

arcpy.env.workspace = r"C:\Users\akeller5\Desktop\Lab 7\Lab07_data\part2"
arcpy.env.overwriteOutput = True

# Setting up variables such as the file we
#   going to use, its spatial reference,
#   and the lsit of points that wil be used
infile = "MD_counties.shp"
infile_sr = arcpy.Describe(infile).spatialReference
points_list = list()

# Setting up the fields to use with the cursor
fields = ['NAME', 'SHAPE@X', 'SHAPE@Y']

# Using the search cursor to find the coordinates for the
#   centroid of each county, creating a point geometry,
#   and saving it to its own file
with arcpy.da.SearchCursor(infile, fields) as cursor:
    for row in cursor:
        print "County: {}".format(row[0])
        point = arcpy.Point()
        point.X = row[1]
        point.Y = row[2]
        county_centroid = arcpy.PointGeometry(point, infile_sr)
        points_list.append(county_centroid)

# Saving the point geometry to a shpfile
arcpy.CopyFeatures_management(points_list, 'county_centroids.shp')

# Adding fields to the file we are using
arcpy.AddField_management(infile, 'Rate', 'DOUBLE')
arcpy.AddField_management(infile, 'Length', 'DOUBLE')

# Setting up the fields to use with the cursor
fields = ['Rate', 'Length', 'SHAPE@LENGTH', 'POP2000', 'POP2007']

# Using the update cursor to update the rate field to the
#   rate of population growth in each county from 2000 to 2007
#   and to update the legnth field to the length of the perimeter
#   of the county
with arcpy.da.UpdateCursor(infile, fields) as cursor:
    for row in cursor:
        row[0] = round((float(row[4]) - float(row[3])) / float(row[3]) * 100)
        print "The rate of population growth is {}".format(row[0])

        row[1] = round(row[2])
        print "The length of the county is {}".format(row[1])

        cursor.updateRow(row)
