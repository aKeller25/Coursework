#!/usr/bin/env python
# coding: utf-8

# Class: GEOG476
# Semester: FALL2018
# Assignment: Lab09
# Author: Alex Keller
# Written: 2:21 PM 11/14/2018
# Last edited: 3:29 PM 11/25/2018

# In[1]:


import geopandas as gpd
get_ipython().run_line_magic('matplotlib', 'inline')

# Creating the paths for all of the files
in_file = r"C:\Users\akeller5\Desktop\Lab09 data\states_48\states_48.shp"
out_file = r"C:\Users\akeller5\Desktop\Lab09 data\states_48\cali\cali.shp"
reproj_file = r"C:\Users\akeller5\Desktop\Lab09 data\states_48\cali_reproj\cali_reproj.shp"

# Reading in the file states_48 with the information on the continential US
cont_states = gpd.read_file(in_file)

# Plotting the states_48 file
cont_states.plot()

# Selecting California from the states_48 file
cali = cont_states[cont_states['STATE'] == 'California']

# Saves the California selection to the file cali.shp
cali.to_file(out_file)

# Plotting the cali file
cali.plot()

# Printing the Coordinate Reference System for cali.shp
print(cali.crs)

# Changing the Cali CRS to 3740 nad83(harn) / utm zone 10n
cali_NAD83_UTM10N = cali.to_crs({'init': 'epsg:3740'})

# Plotting the reprojection of cali.shp
cali_NAD83_UTM10N.plot()

# Saves the California reprojection to cali_reproj.shp
cali_NAD83_UTM10N.to_file(reproj_file)


# In[4]:


cont_states.head()

