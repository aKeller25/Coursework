#!/usr/bin/env python
# coding: utf-8

# Class: GEOG476
# Semester: FALL2018
# Assignment: Lab03
# Author: Alex Keller
# Written: 12:23 PM 11/7/2018
# Last edited: 1:16 AM 11/28/2018
# 
# Note: Since my group worked on it, I am not submitting the class/group exercise

# In[7]:


get_ipython().run_line_magic('matplotlib', 'inline')

from pandas import DataFrame, read_csv
import matplotlib.pyplot as plt
import pandas as pd 
import csv


plt.style.use('seaborn-whitegrid')

file_BCA1_et = r'C:\Users\akeller5\Desktop\Lab07 data\1541620721\eve_data\1541620721_et.csv'

#BCA1_et = pd.read_csv(file_BCA1_et,delimiter = ',')

#print(BCA1_et) 

et_1 = []
date = []
et = []

 
with open(file_BCA1_et,'r') as BCA1:
    et_1 = csv.reader(BCA1, delimiter = ',')
    
    # Skips the comments before the data
    for skip in range(4):
        next(et_1)
       
    x_axis, y_axis = next(et_1)
    
    print("x_axis: ", x_axis, "\ny_axis: ", y_axis)
    
    for row in et_1:          
        date.append(row[0])
        et.append(row[1])
    
    
data = pd.Series(et, index = date)

BCA1 = pd.DataFrame({x_axis: date, y_axis: et})

#print(BCA1)

#plt.plot(date, et)

BCA1.plot(kind='scatter',x=x_axis,y=y_axis,color='red')
plt.show()

#     test = pd.Series(et_1)
    
#     print("Test ", test)
    
    #et_1 = pd.DataFrame({x_axis: population, 'area': area})


# In[ ]:




