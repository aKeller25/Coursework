#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab05.py
# Date created: 8/3/2017
# Last edited: 8:53 PM 8/4/2017
# Variables used: 
# This program is used 
###################################

# Importing arcpy so this program can access its functions
import arcpy

# Setting up the workspace
arcpy.env.workspace = r"C:\Users\akeller5\Desktop\lab06_data\part2"
arcpy.env.overwriteOutput = True

MD_counties = "MD_counties.shp"
desc_md_counties = arcpy.Describe(MD_counties)
sr_md_counties = desc_md_counties.spatialReference
extent = desc_md_counties.extent
polygonList = []

x_step = extent.XMax / 3.0
y_step = extent.YMax / 3.0

x_intervals = [extent.XMin, x_step * 2, extent.XMax]
y_intervals = [extent.YMin, y_step * 2, extent.YMax]

for x in x_intervals:
    for y in y_intervals:

        cornersArray = arcpy.Array()
        # define and add the first point
        p1 = arcpy.Point()
        p1.X = x
        p1.Y = y
        cornersArray.add(p1)
        # define and add the second point
        p2 = arcpy.Point()
        p2.X = x + x_step
        p2.Y = y
        cornersArray.add(p2)
        # define and add the third point
        p3 = arcpy.Point()
        p3.X = x + x_step
        p3.Y = y - y_step
        cornersArray.add(p3)
        # define and add the fourth point
        p4 = arcpy.Point()
        p4.X = x
        p4.Y = y - y_step
        cornersArray.add(p4)
        poly = arcpy.Polygon(cornersArray)
        polygonList.append(poly)

feat = '3x3_md.shp'
arcpy.CopyFeatures_management(polygonList, feat)
arcpy.DefineProjection_management(feat, sr_md_counties.PCSname) 

print "Done"
