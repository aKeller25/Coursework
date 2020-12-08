#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab01.py
# Date created: 07/14/2017
# Last edited: 4:45 PM 7/14/2017
# Variables used: pg_industry, public_schools, in_feature_buff,  out_feature_buff
#   in_feature_clip, out_feature_clip, num_of_schools, num_of_schools_in_buffer, ratio_buffer_to_schools
# This program buffers the industrial zones in 2014 PG county by 0.5 miles and
#   clips the 2014 PG County schools with the buffer to get and print out the
#   number of schools within a 0.5 mile radius of any industrial area.
# It also prints out the number of schools that were in PG county in 2014 as well as the percentage
#   of schools within the buffer zone to the total number of schools
###################################

# Importing arcpy so this program can access its functions
import arcpy
 
# Setting the environment to easier access variables
arcpy.env.workspace = r"C:\Users\akeller5\Desktop\Lab 2\lab02_data\part2"
arcpy.env.overwriteOutput = True

# Setting the variables without .shp so filename manipulation can be done later
pg_industry = "PGIndustry"
public_schools = "PublicSchools"

# Setting the variable neames for the in and out features of the buffer
in_feature_buff = pg_industry + ".shp"
out_feature_buff = pg_industry + "_buff" + ".shp"

# Setting the variable neames for the in and out features of the clip
in_feature_clip = public_schools + ".shp"
out_feature_clip = public_schools + "_clip" + ".shp"

# A buffer of in_feature_buff for 0.5 miles, disolving the boundaries
arcpy.Buffer_analysis(in_feature_buff, out_feature_buff, "0.5 miles", "FULL", "ROUND", "ALL")

# A clip between in_feature_clip and out_feature_buff
arcpy.Clip_analysis(in_feature_clip, out_feature_buff, out_feature_clip)

# Calculations for number of schools
num_of_schools = arcpy.GetCount_management(in_feature_clip).getOutput(0)
num_of_schools_in_buffer = arcpy.GetCount_management(out_feature_clip).getOutput(0)
ratio_buffer_to_schools = (float(num_of_schools_in_buffer) / float(num_of_schools)) * 100

print "There are {0} public schools in the 0.5 mile bufferzone of land feature - {1} in 2014.\n".format(num_of_schools_in_buffer, pg_industry)
print "There are {} schools in the file {}\n".format(num_of_schools, in_feature_clip)
print "The percentage of public schools in the buffer zone to the total number of public schools is {}%\n".format(ratio_buffer_to_schools)
print "It is not good policy to set public schools so close to the industry area because kids might wander into it. " + "Also the traffic around there would be worse than if the schools were farther from the industry area.\n"
