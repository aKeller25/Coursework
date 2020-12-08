#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab08.py
# Date created: 8/12/2017
# Last edited: 7:33 PM 8/14/2017
# Variables used: tmp_name, temp_layer, temp_layer, out_name, out_shp
#   shp_file, year, month, days, d, expr, outfile, out_raster
#
# This program is used to take in a weather data txt file, convert it
#   to a shp file, and create raster data for
#   temperature and precipitation based off of the created shp file
###################################

print "Start"

# Setting up the environment 
import arcpy

arcpy.env.workspace = r"C:\Users\akeller5\Desktop\Lab09\lab09_data\section2"
arcpy.env.overwriteOutput = True

# Function for converting a txt file to a shp
def text_to_shapefile(txt_file, lat, lon):
    """txt -> shp"""
    
    tmp_name = "temp_weather"

    # Takes in a txt file, lat & long, a name, and a spatial referene (preferable a code)
    #   and makes a layer based on them
    temp_layer = arcpy.MakeXYEventLayer_management(txt_file, lon, lat,
                                                   tmp_name, arcpy.SpatialReference(4326))

    # Saves the layer we just made to a shp file
    out_name = "weather_shapes.shp"
    out_shp = arcpy.CopyFeatures_management(temp_layer, out_name)

    return out_name

# Using the function to take the provided txt file and turning it into a shp file
shp_file = text_to_shapefile('weather_data_all.txt', "lat", "lon")
print "Successfully converted the input txt file into a shp file"

# Checking to see if we have the License for the
#   ArcGIS Spatial Analyst extension(code: spatial)
if arcpy.CheckExtension("Spatial") == "Available":
    arcpy.CheckOutExtension("Spatial")

else:
    print "License check fail"
    exit()

# Dividing the shape file into separate raster data based on temp and precip
year = 2016

# Loops through the months and days in the shp file to process them
for month in xrange(6, 8):
    if month == 6:
        days = 30

    else:
        days = 31

    for d in xrange(1, days + 1):

        # Creating the query statement, layer selection, and the output file
        expr = '"Date" = date \'2016-{}-{} 00:00:00\''.format(str(month).zfill(2), str(d).zfill(2))
        outfile = "weather_2016{}{}.shp".format(str(month).zfill(2), str(d).zfill(2))
        arcpy.Select_analysis(shp_file, outfile, expr)

        # Creating the output raster data about Temperature(TAvgF) and Precipitation(PrSumIn)
        out_raster = arcpy.sa.Idw(outfile, 'TAvgF')
        out_raster.save("T_2016{}{}".format(str(month).zfill(2), str(d).zfill(2)))
        out_raster = arcpy.sa.Idw(outfile, 'PrSumIn')
        out_raster.save("Pr_2016{}{}".format(str(month).zfill(2), str(d).zfill(2)))

print "Processing has finished"







        
